package com.st8vrt.mitsumame.storage.interfaces

import java.util.UUID
import com.st8vrt.mitsumame.storage.types.Device

/**
 * Created by swishy on 30/10/13.
 */
public interface DeviceStorageProvider {

    fun storeDevice(device: Device)

    fun getDevice(deviceId: UUID) : Device?

    fun deleteDevice(deviceId: UUID)
}