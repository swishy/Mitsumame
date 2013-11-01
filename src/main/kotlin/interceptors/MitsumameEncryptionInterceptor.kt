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

        var cryptoHelper = Encryption()

        try
        {
            // TODO check type should be either string or ByteArray at this point in the stack depending on
            // TODO whether its binary data or serialised object.
            response.send(cryptoHelper.encryptString(response.sendBuffer!! as String, cryptoHelper.generateKey("password", "&$&%DG")))
            return true
        }
        catch(exception: Exception)
                {
                    response.statusCode = HttpStatusCodes.InternalServerError.statusCode
                    response.contentType = ContentType.TextPlain.name()
                    response.send("Encryption processing failed.")
                    return false
                }

    }
}