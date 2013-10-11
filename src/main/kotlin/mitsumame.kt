package com.st8vrt.mitsumame

import org.wasabi.app.AppServer
import com.st8vrt.mitsumame.utilities.RootDocument
import com.st8vrt.mitsumame.webservices.core
import com.st8vrt.mitsumame.configuration.mitsumameConfiguration
import java.net.URI
import interceptors.useMitsumameAuthentication
import interceptors.useMitsumameEncryption
import storage.types.Device
import java.util.UUID
import storage.types.User
import org.slf4j.LoggerFactory
import org.wasabi.interceptors.negotiateContent

/**
 * Created by swishy on 10/1/13.
 */
public class Startup
{
    public fun addRootDocumentEntries()
    {
        var rootDocument = RootDocument()
        rootDocument.addRootDocumentUri("TestEndpointUri", URI("http://localhost/testendpoint"))
    }
}

public class MitsumameServer : AppServer()
{
    private var log = LoggerFactory.getLogger(javaClass<MitsumameServer>())

    MitsumameServer()
    {
        // Generate Mitsumame RootDocument
        Startup().addRootDocumentEntries();

        var user = User(UUID.randomUUID(), "admin", "password")
        mitsumameConfiguration.userStorageProvider.storeUser(user)

        log!!.info("Admin Created: ${user}")

        this.negotiateContent()

        // Assign Routes
        this.get("/mitsumame", core.rootDocumentHandler)
        this.get("/testendpoint", {
            var testObject = Test()
            log!!.info("Test Object Created: ${testObject}")
            response.send(testObject)
        })

        // Setup Mitsumame Interceptors for Auth and Crypto
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