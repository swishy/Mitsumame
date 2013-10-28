/**
 * Created by swishy on 10/1/13.
 */

package com.st8vrt.mitsumame.utilities

import java.util.HashMap
import java.net.URI
import org.slf4j.LoggerFactory
import org.apache.commons.codec.binary.Base64
import javax.crypto.KeyGenerator
import java.security.SecureRandom
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

public var rootDocumentEntries : HashMap<String, URI> = HashMap<String, URI>()

public class RootDocument
{
    private var log = LoggerFactory.getLogger(javaClass<RootDocument>())

    public fun addRootDocumentUri(key : String, uri : URI)
    {
        log!!.info("Adding root document entry: [${key}]")
        rootDocumentEntries.put(key, uri);
    }

    public fun getRootDocument() : HashMap<String, URI>
    {
        log!!.info("Root Document entries: [${rootDocumentEntries}]")
        return rootDocumentEntries;
    }
}

public class SessionUtilities
{
    private var log = LoggerFactory.getLogger(javaClass<SessionUtilities>())

    public fun isValid(sessionId : String) : Boolean
    {
        // TODO Check session is valid.
        log!!.info("Session Id: [${sessionId}]")
        return true;
    }
}

public class EncryptionUtilities
{
    private var salt = "Adfas" + 24576 + "6&#N%^BW" + ",.|%^&*"

    private var log = LoggerFactory.getLogger(javaClass<SessionUtilities>())

    public fun encryptString(message : String, key : ByteArray) : String  {
        var iv = getRandomIv();

        return Base64.encodeBase64String(iv) + encryptString(message, key, salt.getBytes(), iv as ByteArray);
    }

    public fun encryptString(plainText:String, encryptionKey:ByteArray, salt:ByteArray, iv:ByteArray) : String {

        var ivToUse = iv ?: getRandomIv()

        try{
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")

            var charEncryptionKey = encryptionKey.toCharArray();

            var keyspec = PBEKeySpec(charEncryptionKey, salt, 1024, 16);
            var secretKey = factory?.generateSecret(keyspec);

            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher?.init(Cipher.ENCRYPT_MODE, SecretKeySpec(secretKey?.getEncoded(), "AES")
                    , IvParameterSpec(iv));

            return Base64.encodeBase64String(cipher?.doFinal(plainText.getBytes())) as String
        } catch(e:Exception) {
            log?.error("Unable to encrypt message", e)
            throw Exception("Unable to encrypt message")
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
}

public fun ByteArray.toCharArray() : CharArray? {
    var charArray = CharArray(this.size)

    var count = 0
    for(byteVal in this) {
        charArray[0] = byteVal.toChar();
        count++
    }

    return charArray
}