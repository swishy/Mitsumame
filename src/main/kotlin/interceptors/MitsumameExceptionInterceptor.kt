package com.st8vrt.mitsumame.interceptors

import org.wasabi.http.Request
import org.wasabi.interceptors.Interceptor
import org.wasabi.http.Response
import org.wasabi.http.StatusCodes
import org.wasabi.http.ContentType

/**
 * Created by swishy on 30/10/13.
 */
public class MitsumameExceptionInterceptor :  Interceptor() {
    override fun intercept(request: Request, response: Response) {

        response.statusCode = StatusCodes.InternalServerError.code
        response.contentType = ContentType.Companion.Text.Plain.contentType
        response.send("Error happened")
    }
}