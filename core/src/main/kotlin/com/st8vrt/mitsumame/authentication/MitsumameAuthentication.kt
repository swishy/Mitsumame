package com.st8vrt.mitsumame.authentication

import com.st8vrt.mitsumame.library.utilities.Session
import com.st8vrt.mitsumame.library.utilities.getQualifiedUrl
import org.slf4j.LoggerFactory
import org.wasabi.authentication.Authentication
import org.wasabi.http.Request
import org.wasabi.http.Response

/**
 * Created by cnwdaa1 on 16/09/2015.
 */
public class MitsumameAuthentication : Authentication {

    val SessionCookieId = "MitsumameSessionId"

    private var log = LoggerFactory.getLogger(MitsumameAuthentication::class.java)

    override fun authenticate(request: Request, response: Response): Boolean {
        log!!.info("Mitsumame Authentication Invoked.")

        var sessionCookie = request.cookies.get(SessionCookieId)

        log!!.info("Session Cookie: ${sessionCookie}")

        if(sessionCookie != null) {
            if (Session().isValid(sessionCookie.value))
                return true;
        }

        var onetimeTokenUrl = getQualifiedUrl(request, "/onetimetoken")

        // Setup headers for client to intercept.
        // Wasabi handles setting correct code and status.
        response.location = getQualifiedUrl(request, "/session").toString()
        response.addRawHeader("UserTokenUri", onetimeTokenUrl.toString())
        return false
    }
}