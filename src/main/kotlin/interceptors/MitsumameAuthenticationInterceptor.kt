package com.st8vrt.mitsumame.interceptors

import org.wasabi.interceptors.Interceptor
import org.wasabi.http.Response
import org.slf4j.LoggerFactory
import org.wasabi.http.Request
import java.net.URI
import com.st8vrt.mitsumame.library.utilities.Session
import org.wasabi.http.HttpStatusCodes
import org.wasabi.http.ContentType


/**
 * Created by swishy on 30/10/13.
 */
public class MitsumameAuthenticationInterceptor(): Interceptor {

    val SessionCookieId = "JoobMobileSessionId"

    private var log = LoggerFactory.getLogger(javaClass<MitsumameAuthenticationInterceptor>())

    override fun intercept(request: Request, response: Response): Boolean {

        log!!.info("Authentication Interceptor invoked.")

        var sessionCookie = request.cookies.get(SessionCookieId)

        log!!.info("Session Cookie: ${sessionCookie}")

        if(sessionCookie != null) {
            if (Session().isValid(sessionCookie!!.value))
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