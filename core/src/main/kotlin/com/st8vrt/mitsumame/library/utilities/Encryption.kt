package com.st8vrt.mitsumame.library.utilities

import com.st8vrt.mitsumame.configuration.mitsumameConfiguration
import org.apache.commons.codec.binary.Base64
import org.slf4j.LoggerFactory
import java.security.InvalidParameterException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created by swishy on 30/10/13.
 */
public class Encryption
{
    internal val salt = "Adfas" + 24576 + "6&#N%^BW" + ",.|%^&*"

    private val aesBlockSize = 256;

    private var log = LoggerFactory.getLogger(Session::class.java)

    public fun encryptByteArray(array : ByteArray, key : ByteArray) : String  {
        var iv = getRandomIv();

        return Base64.encodeBase64String(iv) + encryptByteArray(array, key, salt.toByteArray(), iv as ByteArray)
    }

    public fun encryptString(message : String, key : ByteArray) : String  {
        var iv = getRandomIv();

        return Base64.encodeBase64String(iv) + encryptString(message, key, salt.toByteArray(), iv as ByteArray)
    }

    public fun encryptByteArray(array:ByteArray, encryptionKey:ByteArray, salt:ByteArray, iv:ByteArray) : String {

        try{
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")

            var charEncryptionKey = encryptionKey.toCharArray()

            var keyspec = PBEKeySpec(charEncryptionKey, salt, mitsumameConfiguration.keyIterations, aesBlockSize)
            var secretKey = factory?.generateSecret(keyspec)

            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher?.init(Cipher.ENCRYPT_MODE, SecretKeySpec(secretKey?.getEncoded(), "AES")
                    , IvParameterSpec(iv));

            return Base64.encodeBase64String(cipher?.doFinal(array))
        } catch(e:Exception) {
            log?.error("Unable to encrypt message", e)
            throw Exception("Unable to encrypt message")
        }
    }

    public fun encryptString(plainText:String, encryptionKey:ByteArray, salt:ByteArray, iv:ByteArray) : String {

        try{
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            var charEncryptionKey = encryptionKey.toCharArray()
            var keyspec = PBEKeySpec(charEncryptionKey, salt, mitsumameConfiguration.keyIterations, aesBlockSize)
            var secretKey = factory?.generateSecret(keyspec)

            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher?.init(Cipher.ENCRYPT_MODE, SecretKeySpec(secretKey?.getEncoded(), "AES")
                    , IvParameterSpec(iv));

            return Base64.encodeBase64String(cipher?.doFinal(plainText.toByteArray()))
        } catch(e:Exception) {
            log?.error("Unable to encrypt message", e)
            throw Exception("Unable to encrypt message")
        }
    }

    public fun decryptString(encryptedPayload:String) : String {
        var iv = getIvFromPayload(encryptedPayload) as ByteArray
        var message = getEncryptedMessageFromPayload(encryptedPayload)
        var key = Base64.decodeBase64("")
        return decryptString(message, key, salt.toByteArray(), iv)
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

    public fun generateKey(password : String, salt : String) : ByteArray
    {
        var array = ByteArray(0)

        try
        {

            var digest = MessageDigest.getInstance("SHA-256")
            array = digest?.digest((salt + password).toByteArray()) as ByteArray

        }
        catch(exception: NoSuchAlgorithmException)
                {
                    log?.error("Unable to generate an encryption key: ${exception}")
                    InvalidParameterException("Unable to generate an encryption key")
                }

        return  array
    }

}