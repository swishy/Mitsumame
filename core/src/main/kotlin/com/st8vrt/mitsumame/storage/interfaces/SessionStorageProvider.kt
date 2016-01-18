package com.st8vrt.mitsumame.storage.interfaces

import java.util.UUID
import com.st8vrt.mitsumame.storage.types.Session

/**
 * Created by swishy on 30/10/13.
 */
public interface SessionStorageProvider {

    fun getSession(sessionId: UUID) : Session?

    fun deleteSession(sessionId: UUID)

    fun storeSession(session: Session)

    fun getToken(key: String) : String

    fun setToken(key: String, token: String)

    fun deleteToken(key: String)
}