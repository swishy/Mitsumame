package storage.provider

import java.util.UUID
import storage.DeviceStorageProvider
import storage.types.Device
import java.util.HashMap

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/8/13
 * Time: 9:27 PM
 * To change this template use File | Settings | File Templates.
 */

private var currentDevices : HashMap<UUID, Device> =  HashMap<UUID, Device>()

public class InMemoryDeviceStorageProvider : DeviceStorageProvider
{
    override fun deleteDevice(deviceId: UUID) {
        throw UnsupportedOperationException()
    }

    override fun getDevice(deviceId: UUID): Device? {
        return currentDevices.get(deviceId)
    }

    override fun storeDevice(device : Device) {
        currentDevices.put(device.deviceId, device)
    }
}
