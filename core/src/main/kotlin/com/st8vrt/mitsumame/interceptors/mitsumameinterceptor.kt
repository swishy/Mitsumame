package com.st8vrt.mitsumame.interceptors

/**
 * Created by swishy on 10/3/13.
 */

import org.wasabi.app.AppServer
import org.wasabi.routing.InterceptOn

fun AppServer.useMitsumameException(path: String = "*") {
    intercept(MitsumameExceptionInterceptor(), path, InterceptOn.Error)
}

fun AppServer.useMitsumameEncryption(path: String = "*") {
    intercept(MitsumameDecryptionInterceptor(), path, InterceptOn.PreRequest)
    intercept(MitsumameEncryptionInterceptor(), path, InterceptOn.PostExecution)
}
