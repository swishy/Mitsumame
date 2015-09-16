/**
 * Created by swishy on 10/1/13.
 */

package com.st8vrt.mitsumame.library.utilities

import java.util.HashMap
import java.net.URI
import org.slf4j.LoggerFactory
import org.wasabi.http.Request

// Static helpers.
public fun ByteArray.toCharArray() : CharArray {
    var charArray = CharArray(this.size())

    var count = 0
    for(byteVal in this) {
        charArray[count] = byteVal.toChar();
        count++
    }

    return charArray
}

fun getQualifiedUrl(request: Request, relativePath: String) : URI {
    return URI(request.protocol + "://" + request.host + ":" + request.port + relativePath)
}