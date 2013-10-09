package configuration

import storage.provider.InMemorySessionStorageProvider
import storage.SessionStorageProvider
import storage.DeviceStorageProvider
import storage.provider.InMemoryDeviceStorageProvider

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/5/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */

// Static instance to reference.
public var mitsumameConfiguration : MitsumameConfiguration = MitsumameConfiguration()

public class MitsumameConfiguration
{
    // Can be assigned to custom provider.
    public val sessionStorageProvider : SessionStorageProvider = InMemorySessionStorageProvider()

    public val deviceStorageProvider : DeviceStorageProvider = InMemoryDeviceStorageProvider()

    // Default to 2 hours.
    public var sessionDuration : Long = 7200000
}