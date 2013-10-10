package storage

import storage.types.Session
import java.util.UUID
import storage.types.Device
import storage.types.User

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/5/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */

trait SessionStorageProvider {

    fun getSession(sessionId: UUID) : Session?

    fun deleteSession(sessionId: String)

    fun storeSession(session: Session)
}


trait DeviceStorageProvider {

    fun storeDevice(device: Device)

    fun getDevice(deviceId: UUID) : Device?

    fun deleteDevice(deviceId: UUID)
}


trait UserStorageProvider {

    fun storeUser(user: User)

    fun getUser(userId: UUID) : User?

    fun deleteUser(userId: UUID)
}