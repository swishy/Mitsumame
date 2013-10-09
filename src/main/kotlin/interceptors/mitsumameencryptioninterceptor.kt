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

        response.setHttpStatus(HttpStatusCodes.InternalServerError)
        response.setResponseContentType(ContentType.TextPlain)
        response.send("Encryption processing failed.")
        return false
    }
}


fun AppServer.useMitsumameEncryption(path: String = "*") {
    intercept(MitsumameEncryptionInterceptor(), path, InterceptOn.PreRequest)
}
