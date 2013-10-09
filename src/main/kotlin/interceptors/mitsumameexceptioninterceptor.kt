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

public class MitsumameExceptionInterceptor(): Interceptor {
    override fun intercept(request: Request, response: Response): Boolean {

        response.setHttpStatus(HttpStatusCodes.InternalServerError)
        response.setResponseContentType(ContentType.TextPlain)
        response.send("Error happened")
        return false
    }
}


fun AppServer.useMitsumameException(path: String = "*") {
    intercept(MitsumameExceptionInterceptor(), path, InterceptOn.Error)
}