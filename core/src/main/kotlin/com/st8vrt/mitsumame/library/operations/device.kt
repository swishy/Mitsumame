package com.st8vrt.mitsumame.library.operations

import java.util.UUID
import com.st8vrt.mitsumame.configuration.mitsumameConfiguration

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/8/13
 * Time: 9:24 PM
 * To change this template use File | Settings | File Templates.
 */
public fun findOrCreateDevice(deviceType : String, deviceId : UUID)
{
      mitsumameConfiguration.deviceStorageProvider.getDevice(deviceId)
}