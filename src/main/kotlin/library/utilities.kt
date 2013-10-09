/**
 * Created by swishy on 10/1/13.
 */

package com.st8vrt.mitsumame.utilities

import java.util.HashMap
import java.net.URI
import org.slf4j.LoggerFactory

public var rootDocumentEntries : HashMap<String, URI> = HashMap<String, URI>()

public class RootDocument
{
    private var log = LoggerFactory.getLogger(javaClass<RootDocument>())

    public fun addRootDocumentUri(key : String, uri : URI)
    {
        log!!.info("Adding root document entry: [${key}]")
        rootDocumentEntries.put(key, uri);
    }

    public fun getRootDocument() : HashMap<String, URI>
    {
        log!!.info("Root Document entries: [${rootDocumentEntries}]")
        return rootDocumentEntries;
    }
}

public class SessionUtilities
{
    private var log = LoggerFactory.getLogger(javaClass<SessionUtilities>())

    public fun isValid(sessionId : String) : Boolean
    {
        // TODO Check session is valid.
        log!!.info("Session Id: [${sessionId}]")
        return true;
    }
}
