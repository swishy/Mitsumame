package storage.provider

import storage.SessionStorageProvider
import java.util.HashMap
import java.util.UUID
import storage.types.Session

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/5/13
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */

// Current Session Pool, obviously only going to work on a single node.
private var currentSessions : HashMap<UUID, Session> =  HashMap<UUID, Session>()

public class InMemorySessionStorageProvider : SessionStorageProvider
{


    override fun getSession(sessionId: UUID): Session? {
        return currentSessions.get(sessionId)
    }
    override fun deleteSession(sessionId: String) {
        throw UnsupportedOperationException()
    }
    override fun createSession() : Session {
        throw UnsupportedOperationException()
    }

    override fun add(session: Session)
    {
        currentSessions.put(session.sessionId, session)
    }

}