package com.st8vrt.mitsumame.storage.providers

import com.st8vrt.mitsumame.storage.interfaces.UserStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.DeviceStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.SessionStorageProvider
import com.st8vrt.mitsumame.storage.types.User
import java.util.UUID
import com.st8vrt.mitsumame.storage.types.Device
import com.st8vrt.mitsumame.storage.types.Session
import java.util.HashMap

/**
 * Created by swishy on 30/10/13.
 */
public class InMemoryStorageProvider : UserStorageProvider, DeviceStorageProvider, SessionStorageProvider{

    // Current in-memory list of users for testing.
    private var currentUsers : HashMap<UUID, User> =  HashMap()

    // Current in-memory list of devices for testing.
    private var currentDevices : HashMap<UUID, Device> =  HashMap()

    // Current in-memort list of session for testing.
    private var currentSessions : HashMap<UUID, Session> =  HashMap()

    // Current in-memort list of session for testing.
    private var currentTokens : HashMap<String, String> =  HashMap()

    override fun storeUser(user: User) {
        currentUsers.put(user.userId, user)
    }
    override fun getUser(userId: UUID): User? {
        return currentUsers[userId]
    }
    override fun deleteUser(userId: UUID) {
        throw UnsupportedOperationException()
    }
    override fun storeDevice(device: Device) {
        currentDevices.put(device.deviceId, device)
    }
    override fun getDevice(deviceId: UUID): Device? {
        return currentDevices[deviceId]
    }
    override fun deleteDevice(deviceId: UUID) {
        throw UnsupportedOperationException()
    }
    override fun getSession(sessionId: UUID): Session? {
        return currentSessions[sessionId]
    }
    override fun deleteSession(sessionId: UUID) {
        currentSessions.remove(sessionId)
    }
    override fun storeSession(session: Session) {
        currentSessions.put(session.sessionId, session)
    }

    override fun getToken(key: String): String {
        return currentTokens[key] as String
    }

    override fun setToken(key: String, token: String) {
        currentTokens.put(key, token)
    }

    override fun deleteToken(key: String) {
        currentTokens.remove(key)
    }
}