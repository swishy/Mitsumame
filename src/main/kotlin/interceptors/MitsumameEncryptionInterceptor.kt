package com.st8vrt.mitsumame.interceptors

import org.wasabi.interceptors.Interceptor
import org.slf4j.LoggerFactory
import org.wasabi.http.Request
import org.wasabi.http.Response
import org.wasabi.http.StatusCodes
import org.wasabi.http.ContentType
import com.st8vrt.mitsumame.library.utilities.Encryption

/**
 * Created by swishy on 30/10/13.
 */
public class MitsumameEncryptionInterceptor() : Interceptor() {
    private var log = LoggerFactory.getLogger(javaClass<MitsumameEncryptionInterceptor>())

    override fun intercept(request: Request, response: Response) {

        log!!.info("Encryption Interceptor invoked.")

        var cryptoHelper = Encryption()

        // TODO get users key.
        var password = "password"

        try
        {
            // Likely JSON serialised object so try such first.
            when {
                    response.sendBuffer is String -> {
                    response.send(cryptoHelper.encryptString((response.sendBuffer as String), cryptoHelper.generateKey(password, cryptoHelper.salt)))
                }
                else -> {
                    // Assume we have binary data and attempt to encrypt it.
                    response.send(cryptoHelper.encryptByteArray((response.sendBuffer as ByteArray), cryptoHelper.generateKey(password, cryptoHelper.salt)))
                }
            }

            next()
        }
        catch(exception: Exception)
                {
                    response.statusCode = StatusCodes.InternalServerError.code
                    response.contentType = ContentType.Text.Plain.contentType
                    response.send("Encryption processing failed.")
                }

    }
}