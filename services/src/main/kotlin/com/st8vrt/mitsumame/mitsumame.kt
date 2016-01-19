package com.st8vrt.mitsumame

import com.st8vrt.mitsumame.authentication.MitsumameAuthentication
import com.st8vrt.mitsumame.configuration.mitsumameConfiguration
import com.st8vrt.mitsumame.library.utilities.RootDocument
import com.st8vrt.mitsumame.storage.interfaces.DeviceStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.SessionStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.UserStorageProvider
import com.st8vrt.mitsumame.storage.providers.InMemoryStorageProvider
import com.st8vrt.mitsumame.storage.types.User
import com.st8vrt.mitsumame.webservices.core.onetimeLoginTokenHandler
import com.st8vrt.mitsumame.webservices.core.rootDocumentHandler
import com.st8vrt.mitsumame.webservices.core.sessionCreationHandler
import com.st8vrt.mitsumame.webservices.core.sessionSetupHandler
import org.slf4j.LoggerFactory
import org.wasabi.app.AppServer
import org.wasabi.interceptors.useAuthentication
import uy.kohesive.injekt.InjektMain
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingleton
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

public open class MitsumameServer() : AppServer()
{
    companion object : InjektMain() {
        override fun InjektRegistrar.registerInjectables() {
            addSingleton<SessionStorageProvider>(InMemoryStorageProvider())
            addSingleton<DeviceStorageProvider>(InMemoryStorageProvider())
            addSingleton<UserStorageProvider>(InMemoryStorageProvider())
        }
    }

    private var log = LoggerFactory.getLogger(MitsumameServer::class.java);

    init {
        // Generate Mitsumame RootDocument
        var startup = Startup()
        startup.addRootDocumentEntries()

        // TODO remove initial setup crud implemented during development for testing
        var user = User("admin", "password")
        mitsumameConfiguration.userStorageProvider.storeUser(user)

        log!!.info("Admin Created: ${user}")

        // TODO investigate websockets for login negotiation
        // this.channel("/session", core.sessionChannelHandler)

        this.post("/session", sessionCreationHandler)
        this.get("/session/:id", sessionSetupHandler)

        // Assign Core Routes
        this.get("/mitsumame", rootDocumentHandler)
        this.post("/onetimetoken", onetimeLoginTokenHandler)
        this.get("/testendpoint", {
            var testObject = com.st8vrt.mitsumame.Test()
            log!!.info("Test Object Created: ${testObject}")
            response.send(testObject)
        })

        // Setup Mitsumame Authentication and Crypto
        this.useAuthentication(MitsumameAuthentication())
    }
}

