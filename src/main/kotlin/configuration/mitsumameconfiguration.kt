package com.st8vrt.mitsumame.configuration

import storage.provider.InMemorySessionStorageProvider
import storage.SessionStorageProvider
import storage.DeviceStorageProvider
import storage.provider.InMemoryDeviceStorageProvider
import storage.provider.InMemoryUserStorageProvider
import storage.UserStorageProvider

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
    public var sessionStorageProvider : SessionStorageProvider = InMemorySessionStorageProvider()

    public var deviceStorageProvider : DeviceStorageProvider = InMemoryDeviceStorageProvider()

    public var userStorageProvider : UserStorageProvider = InMemoryUserStorageProvider()

    // Default to 2 hours.
    public var sessionDuration : Long = 7200000
}