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
import java.net.URI

var SessionCookieId = "JoobMobileSessionId"


/**
 * MitsumameAuthenticationInterceptor checks for a valid session on the request and returns a
 * 401 with the url to log into set in the location header for the client to post to.
 */
public class MitsumameAuthenticationInterceptor(): Interceptor {

    private var log = LoggerFactory.getLogger(javaClass<MitsumameAuthenticationInterceptor>())

    override fun intercept(request: Request, response: Response): Boolean {

        log!!.info("Authentication Interceptor invoked.")

        var sessionCookie = request.cookies.get(SessionCookieId)

        log!!.info("Session Cookie: ${sessionCookie}")

        if(sessionCookie != null) {
            if (SessionUtilities().isValid(sessionCookie!!.value))
            return true;
        }

        var onetimeTokenUrl = URI(request.host + "/onetimetoken")

        response.statusCode = HttpStatusCodes.Unauthorized.statusCode
        response.contentType = ContentType.TextPlain.name()
        response.send("You are not authenticated against the requested application.")
        response.location = URI(request.host + "/sessions").toString() as String
        response.addExtraHeader("UserTokenUri", onetimeTokenUrl.toString() as String)
        return false
    }
}


fun AppServer.useMitsumameAuthentication(path: String = "*") {
    intercept(MitsumameAuthenticationInterceptor(), path, InterceptOn.PreExecution)
}
