package storage

import storage.types.Session
import java.util.UUID
import storage.types.Device

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/5/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */

trait SessionStorageProvider {

    fun createSession() : Session

    fun getSession(sessionId: UUID) : Session?

    fun deleteSession(sessionId: String)

    // TODO remove once auth is working.!!!!
    fun add(session: Session)
}

trait DeviceStorageProvider {

    fun storeDevice(device: Device)

    fun getDevice(deviceId: UUID) : Device?

    fun deleteDevice(deviceId: UUID)
}