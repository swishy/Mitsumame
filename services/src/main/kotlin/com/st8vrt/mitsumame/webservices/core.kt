package com.st8vrt.mitsumame.webservices.core

import com.st8vrt.mitsumame.MitsumameServer
import org.slf4j.LoggerFactory
import org.wasabi.routing.routeHandler

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 9/30/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */

private var log = LoggerFactory.getLogger(MitsumameServer::class.java)

val rootDocumentHandler = routeHandler {

    var rootDocument = com.st8vrt.mitsumame.rootDocument.getRootDocument()
    log?.info("Root Document: ${rootDocument}")
    response.send(rootDocument)
}

val onetimeLoginTokenHandler = routeHandler {

    response.send("Implement me!")
}

val sessionCreationHandler = routeHandler {

    response.send("Implement me!")
}

val sessionSetupHandler = routeHandler {

    // TODO grab session from com.st8vrt.mitsumame.storage.

    // val session = sessions.firstOrNull { it.id == request.routeParams["id"]?.toInt() }
}


