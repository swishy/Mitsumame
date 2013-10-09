package com.st8vrt.mitsumame

import org.wasabi.app.AppServer
import com.st8vrt.mitsumame.utilities.RootDocument
import com.st8vrt.mitsumame.webservices.core
import java.net.URI
import interceptors.useMitsumameAuthentication
import interceptors.useMitsumameEncryption

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

public class MitsumameServer
{
    private var appServer : AppServer = AppServer()

    MitsumameServer()
    {
        // Generate Mitsumame RootDocument
        Startup().addRootDocumentEntries();

        // Assign Routes
        appServer.get("/mitsumame", core.rootDocumentHandler)
        appServer.get("/testendpoint", { response.send("TEST!") })

        // Setup Mitsumame Interceptors for Auth and Crypto
        //appServer.useMitsumameAuthentication()
        appServer.useMitsumameEncryption()

        appServer.start(true)
    }
}