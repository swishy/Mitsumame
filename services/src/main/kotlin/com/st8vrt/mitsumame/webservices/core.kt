package com.st8vrt.mitsumame.webservices.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.st8vrt.mitsumame.MitsumameServer
import com.st8vrt.mitsumame.authentication.IMitsumamePlugin
import com.st8vrt.mitsumame.configuration.mitsumameConfiguration
import com.st8vrt.mitsumame.documents.SessionRequestDocument
import com.st8vrt.mitsumame.library.utilities.Encryption
import org.slf4j.LoggerFactory
import org.wasabi.routing.routeHandler
import java.util.*

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 9/30/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */

private var log = LoggerFactory.getLogger(MitsumameServer::class.java)

private val tokenHeader = "logintoken"

val rootDocumentHandler = routeHandler {

    var rootDocument = com.st8vrt.mitsumame.rootDocument.getRootDocument()
    log?.info("Root Document: ${rootDocument}")
    response.send(rootDocument)
}

val onetimeLoginTokenHandler = routeHandler {

    response.send("Implement me!")
}

val sessionCreationHandler = routeHandler {

    var mapper = ObjectMapper()

    var requestDocument = mapper.readValue(request.document, SessionRequestDocument::class.java)

    var plugin = mitsumameConfiguration.authHandler

    var token = UUID.randomUUID().toString()

    mitsumameConfiguration.sessionStorageProvider.setToken(requestDocument.uuid, token)

    // Calls current auth handler to return an appropriate key ( passwd hash, oauth token )
    var tokenResponse = Encryption().encryptString(token, plugin.getKey().toByteArray())

    response.addRawHeader(tokenHeader, tokenResponse)

    response.send("login response")
}

val sessionSetupHandler = routeHandler {

    // TODO grab session from com.st8vrt.mitsumame.storage.

    // val session = sessions.firstOrNull { it.id == request.routeParams["id"]?.toInt() }
}


