package com.st8vrt.mitsumame.storage.interfaces

import java.util.UUID
import com.st8vrt.mitsumame.storage.types.Session

/**
 * Created by swishy on 30/10/13.
 */
public trait SessionStorageProvider {

    fun getSession(sessionId: UUID) : Session?

    fun deleteSession(sessionId: String)

    fun storeSession(session: Session)
}