package interceptors

/**
 * Created by swishy on 10/3/13.
 */

import org.wasabi.http.Request
import org.wasabi.http.Response
import org.wasabi.http.ContentType
import org.apache.commons.codec.binary;
import io.netty.handler.codec.base64.Base64Decoder
import org.wasabi.encoding.decodeBase64
import org.wasabi.routing.RouteHandler
import org.wasabi.app.AppServer
import org.wasabi.http.HttpStatusCodes
import org.wasabi.interceptors.Interceptor
import com.st8vrt.mitsumame.utilities.SessionUtilities
import org.wasabi.routing.InterceptOn
import org.slf4j.LoggerFactory

public class MitsumameEncryptionInterceptor(): Interceptor {

    private var log = LoggerFactory.getLogger(javaClass<MitsumameEncryptionInterceptor>())

    override fun intercept(request: Request, response: Response): Boolean {

        log!!.info("Encryption Interceptor invoked.")

        // TODO implement encryption...
        if (request != null)
            return true

        response.statusCode = HttpStatusCodes.InternalServerError.statusCode
        response.contentType = ContentType.TextPlain.name()
        response.send("Encryption processing failed.")
        return false
    }
}

public class MitsumameDecryptionInterceptor(): Interceptor {

    private var log = LoggerFactory.getLogger(javaClass<MitsumameEncryptionInterceptor>())

    override fun intercept(request: Request, response: Response): Boolean {

        log!!.info("Decryption Interceptor invoked.")

        // TODO implement decryption...
        if (request != null)
            return true

        // TODO call authentication handler once done here and force auth again as something in credentials is bad.
        return false
    }
}


fun AppServer.useMitsumameEncryption(path: String = "*") {
    intercept(MitsumameDecryptionInterceptor(), path, InterceptOn.PreRequest)
    intercept(MitsumameEncryptionInterceptor(), path, InterceptOn.PostRequest)
}
