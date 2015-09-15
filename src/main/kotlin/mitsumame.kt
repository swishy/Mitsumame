package com.st8vrt.mitsumame

import com.st8vrt.mitsumame.configuration.mitsumameConfiguration
import com.st8vrt.mitsumame.interceptors.useMitsumameAuthentication
import com.st8vrt.mitsumame.library.utilities.RootDocument
import com.st8vrt.mitsumame.storage.types.User
import com.st8vrt.mitsumame.webservices.core
import org.slf4j.LoggerFactory
import org.wasabi.app.AppServer
import java.net.URI

/**
 * Created by swishy on 10/1/13.
 */

val rootDocument = RootDocument()

public class Startup
{
    public fun addRootDocumentEntries()
    {
        rootDocument.addRootDocumentUri("TestEndpointUri", URI("http://localhost/testendpoint"))
    }
}

public class Test
{
    var something = "TestValue"
    var somethingElse = "AnotherValue"
}

public class MitsumameServer() : AppServer()
{
    private var log = LoggerFactory.getLogger(MitsumameServer::class.java);

    init {
        // Generate Mitsumame RootDocument
        var startup = Startup()
        startup.addRootDocumentEntries()

        // TODO remove initial setup crud implemented during development for testing
        var user = User("admin", "password")
        mitsumameConfiguration.userStorageProvider.storeUser(user)

        log!!.info("Admin Created: ${user}")

        // Set login channel
        // this.channel("/session", core.sessionChannelHandler)

        this.post("/session", core.sessionCreationHandler)
        this.get("/session/:id", core.sessionSetupHandler)

        // Assign Core Routes
        this.get("/mitsumame", core.rootDocumentHandler)
        this.post("/onetimetoken", core.onetimeLoginTokenHandler)
        this.get("/testendpoint", {
            var testObject = com.st8vrt.mitsumame.Test()
            log!!.info("Test Object Created: ${testObject}")
            response.send(testObject)
        })

        // Setup Mitsumame Interceptors for Authentication and Crypto
        this.useMitsumameAuthentication()
        //this.useMitsumameEncryption()

        this.start(true)

    }
}

