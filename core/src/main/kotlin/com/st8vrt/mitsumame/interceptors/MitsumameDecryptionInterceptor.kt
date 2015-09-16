package com.st8vrt.mitsumame.interceptors

import org.wasabi.interceptors.Interceptor
import org.slf4j.LoggerFactory
import org.wasabi.http.Request
import org.wasabi.http.Response

/**
 * Created by swishy on 30/10/13.
 */
public class MitsumameDecryptionInterceptor(): Interceptor() {

    private var log = LoggerFactory.getLogger(MitsumameDecryptionInterceptor::class.java)

    override fun intercept(request: Request, response: Response): Boolean {

        log!!.info("Decryption Interceptor invoked.")

        // TODO implement decryption...

        // TODO call com.st8vrt.mitsumame.authentication handler once done here and force auth again as something in credentials is bad.
        return false
    }
}