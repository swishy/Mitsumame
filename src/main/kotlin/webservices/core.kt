package com.st8vrt.mitsumame.webservices.core

import org.wasabi.http.HttpStatusCodes
import org.wasabi.routing.routeHandler
import org.codehaus.jackson.map.ObjectMapper
import com.st8vrt.mitsumame.utilities.RootDocument
import org.slf4j.LoggerFactory

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 9/30/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */

private var log = LoggerFactory.getLogger(javaClass<RootDocument>())

val rootDocumentHandler = routeHandler {

    var rootDocument = RootDocument().getRootDocument()
    log!!.info("Root Document: ${rootDocument}")
    response.send(rootDocument)
}


