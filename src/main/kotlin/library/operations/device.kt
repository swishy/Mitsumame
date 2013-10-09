package library.operations

import java.util.UUID

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/8/13
 * Time: 9:24 PM
 * To change this template use File | Settings | File Templates.
 */
public fun findOrCreateDevice(deviceType : String, deviceId : UUID)
{
      configuration.mitsumameConfiguration.deviceStorageProvider.getDevice(deviceId)
}