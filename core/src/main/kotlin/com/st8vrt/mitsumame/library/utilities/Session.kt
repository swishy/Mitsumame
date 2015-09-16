package com.st8vrt.mitsumame.library.utilities

import org.slf4j.LoggerFactory

/**
 * Created by swishy on 30/10/13.
 */
public class Session
{
    private var log = LoggerFactory.getLogger(Session::class.java)

    public fun isValid(sessionId : String) : Boolean
    {
        // TODO Check session is valid.
        log?.info("Session Id: [${sessionId}]")
        return true;
    }
}