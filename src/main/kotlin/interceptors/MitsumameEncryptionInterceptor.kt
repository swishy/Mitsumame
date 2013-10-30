package com.st8vrt.mitsumame.interceptors

import org.wasabi.interceptors.Interceptor
import org.slf4j.LoggerFactory
import org.wasabi.http.Request
import org.wasabi.http.Response
import org.wasabi.http.HttpStatusCodes
import org.wasabi.http.ContentType
import com.st8vrt.mitsumame.library.utilities.Encryption

/**
 * Created by swishy on 30/10/13.
 */
public class MitsumameEncryptionInterceptor() : Interceptor {
    private var log = LoggerFactory.getLogger(javaClass<MitsumameEncryptionInterceptor>())

    override fun intercept(request: Request, response: Response): Boolean {

        log!!.info("Encryption Interceptor invoked.")

        // TODO implement encryption...
        var buffer = response.sendBuffer

        var cryptoHelper = Encryption()


        if (response != null)
            return true

        response.statusCode = HttpStatusCodes.InternalServerError.statusCode
        response.contentType = ContentType.TextPlain.name()
        response.send("Encryption processing failed.")
        return false
    }
}