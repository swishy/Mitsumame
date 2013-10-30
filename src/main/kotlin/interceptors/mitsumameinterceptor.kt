package com.st8vrt.mitsumame.interceptors

/**
 * Created by swishy on 10/3/13.
 */

import org.wasabi.app.AppServer
import org.wasabi.interceptors.Interceptor
import org.wasabi.routing.InterceptOn
import com.st8vrt.mitsumame.interceptors.MitsumameAuthenticationInterceptor
import com.st8vrt.mitsumame.interceptors.MitsumameExceptionInterceptor


fun AppServer.useMitsumameAuthentication(path: String = "*") {
    intercept(MitsumameAuthenticationInterceptor(), path, InterceptOn.PreExecution)
}

fun AppServer.useMitsumameException(path: String = "*") {
    intercept(MitsumameExceptionInterceptor(), path, InterceptOn.Error)
}

fun AppServer.useMitsumameEncryption(path: String = "*") {
    intercept(MitsumameDecryptionInterceptor(), path, InterceptOn.PreRequest)
    intercept(MitsumameEncryptionInterceptor(), path, InterceptOn.PostRequest)
}
