package com.st8vrt.mitsumame

import org.wasabi.app.AppServer
import com.st8vrt.mitsumame.library.utilities.RootDocument
import com.st8vrt.mitsumame.webservices.core
import com.st8vrt.mitsumame.configuration.mitsumameConfiguration
import java.net.URI
import com.st8vrt.mitsumame.interceptors.useMitsumameAuthentication
import com.st8vrt.mitsumame.interceptors.useMitsumameEncryption
import com.st8vrt.mitsumame.storage.types.Device
import java.util.UUID
import com.st8vrt.mitsumame.storage.types.User
import org.slf4j.LoggerFactory
import com.st8vrt.mitsumame.storage.types.User

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

public class MitsumameServer : AppServer()
{
    private var log = LoggerFactory.getLogger(javaClass<MitsumameServer>());

    {
        // Generate Mitsumame RootDocument
        Startup().addRootDocumentEntries();

        // TODO remove initial setup crud implemented during development for testing
        var user = User("admin", "password")
        mitsumameConfiguration.userStorageProvider.storeUser(user)

        log!!.info("Admin Created: ${user}")

        // Set login channel
        this.channel("/session", core.sessionChannelHandler)

        // Assign Core Routes
        this.get("/mitsumame", core.rootDocumentHandler)
        this.post("/onetimetoken", core.onetimeLoginTokenHandler)
        this.get("/testendpoint", {
            var testObject = Test()
            log!!.info("Test Object Created: ${testObject}")
            response.send(testObject)
        })

        // Setup Mitsumame Interceptors for Authentication and Crypto
        //this.useMitsumameAuthentication()
        this.useMitsumameEncryption()

        this.start(true)
    }

    public class Test
    {
        var something = "TestValue"
        var somethingElse = "AnotherValue"
    }
}