package com.st8vrt.mitsumame.library.utilities

import java.net.URI
import org.slf4j.LoggerFactory
import java.util.HashMap

/**
 * Created by swishy on 30/10/13.
 */
public class RootDocument
{
    public var rootDocumentEntries : HashMap<String, URI> = HashMap<String, URI>()

    private var log = LoggerFactory.getLogger(javaClass<RootDocument>())

    public fun addRootDocumentUri(key : String, uri : URI)
    {
        log?.info("Adding root document entry: [${key}]")
        rootDocumentEntries.put(key, uri);
    }

    public fun getRootDocument() : HashMap<String, URI>
    {
        log?.info("Root Document entries: [${rootDocumentEntries}]")
        return rootDocumentEntries;
    }
}