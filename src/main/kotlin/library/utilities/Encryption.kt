package com.st8vrt.mitsumame.library.utilities

import org.slf4j.LoggerFactory
import com.st8vrt.mitsumame.library.utilities.Session
import com.st8vrt.mitsumame.library.utilities
import org.apache.commons.codec.binary.Base64
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.KeyGenerator
import java.security.SecureRandom

/**
 * Created by swishy on 30/10/13.
 */
public class Encryption
{
    private val salt = "Adfas" + 24576 + "6&#N%^BW" + ",.|%^&*"

    private val aesBlockSize = 32;

    private var log = LoggerFactory.getLogger(javaClass<Session>())

    public fun encryptByteArray(array : ByteArray, key : ByteArray) : String  {
        var iv = getRandomIv();

        return Base64.encodeBase64String(iv) + encryptByteArray(array, key, salt.getBytes(), iv as ByteArray)
    }

    public fun encryptString(message : String, key : ByteArray) : String  {
        var iv = getRandomIv();

        return Base64.encodeBase64String(iv) + encryptString(message, key, salt.getBytes(), iv as ByteArray)
    }

    public fun encryptByteArray(array:ByteArray, encryptionKey:ByteArray, salt:ByteArray, iv:ByteArray) : String {

        var ivToUse = iv ?: getRandomIv()

        try{
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")

            var charEncryptionKey = encryptionKey.toCharArray()

            /*
             TODO: encryption strength should come from settings
             */
            var keyspec = PBEKeySpec(charEncryptionKey, salt, 1024, aesBlockSize)
            var secretKey = factory?.generateSecret(keyspec)

            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher?.init(Cipher.ENCRYPT_MODE, SecretKeySpec(secretKey?.getEncoded(), "AES")
                    , IvParameterSpec(iv));

            return Base64.encodeBase64String(cipher?.doFinal(array)) as String
        } catch(e:Exception) {
            log?.error("Unable to encrypt message", e)
            throw Exception("Unable to encrypt message")
        }
    }

    public fun encryptString(plainText:String, encryptionKey:ByteArray, salt:ByteArray, iv:ByteArray) : String {

        var ivToUse = iv ?: getRandomIv()

        try{
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")

            var charEncryptionKey = encryptionKey.toCharArray()

            /*
             TODO: encryption strength should come from settings
             */
            var keyspec = PBEKeySpec(charEncryptionKey, salt, 1024, aesBlockSize)
            var secretKey = factory?.generateSecret(keyspec)

            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher?.init(Cipher.ENCRYPT_MODE, SecretKeySpec(secretKey?.getEncoded(), "AES")
                    , IvParameterSpec(iv));

            return Base64.encodeBase64String(cipher?.doFinal(plainText.getBytes())) as String
        } catch(e:Exception) {
            log?.error("Unable to encrypt message", e)
            throw Exception("Unable to encrypt message")
        }
    }

    public fun decryptString(encryptedPayload:String) : String {
        var iv = getIvFromPayload(encryptedPayload) as ByteArray
        var message = getEncryptedMessageFromPayload(encryptedPayload)?: ""
        var key = Base64.decodeBase64("") as ByteArray
        return decryptString(message, key, salt.getBytes(), iv)
    }

    public fun decryptString(cipherText:String, key:ByteArray, salt:ByteArray, iv:ByteArray) : String {
        try{
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            var keyspec = PBEKeySpec(key.toCharArray(), salt,1024, aesBlockSize);
            var secretKey = factory?.generateSecret(keyspec);

            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher?.init(Cipher.DECRYPT_MODE, SecretKeySpec(secretKey?.getEncoded(), "AES"), IvParameterSpec(iv))
            var ciphertext = Base64.decodeBase64(cipherText);
            return cipher?.doFinal(ciphertext)?.toString("UTF-8") as String
        } catch(e:Exception) {
            log?.error("Unable to decrypt message")
            throw Exception("Unable to decrypt message");
        }
    }

    public fun getRandomIv() : ByteArray? {
        try {
            var keyGen = KeyGenerator.getInstance("AES")
            var sr = SecureRandom.getInstance("SHA1PRNG")
            keyGen?.init(128, sr)
            var secretKey = keyGen?.generateKey()
            return secretKey?.getEncoded()
        } catch(e : Exception) {
            log?.error("Unable to create random IV", e)
            throw Exception("Unable to create random IV")
        }
    }

    /**
     * Retrieves the first 16bytes from the given payload
     * @param payload Base64 encoded string
     * @return the IV as as a ByteArray
     */
    public fun getIvFromPayload(payload:String) : ByteArray? {
        var iv = payload.substring(0, 24)
        return Base64.decodeBase64(iv)
    }

    /**
     * Retrieves the encrypted message from the payload
     * (ie. everything after the IV)
     * @param payload Base64 encoded string
     * @return the encrypted message as a Base64 string
     */
    public fun getEncryptedMessageFromPayload(payload:String) : String {
        return payload.substring(24, payload.length())
    }
}