package library.resources

import java.util.UUID

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/8/13
 * Time: 8:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class OneTimeTokenDocument(
        // A unique token
        val Token : UUID,

        // Always true currently
        val JoobMobileEncryption : Boolean = true,

        // Specifies the number of iterations to perform when strengthening keys so client/server are synced
        val KeyStrengtheningIterations : Int
)
{}

public class OneTimeLoginTokenRequestDocument(

        // User the token is for
        val Username : String,

        // Unique Id for the device logging in.
        val DeviceId : String,

        // The type of device, i.e. iPhone, Android etc.
        val DeviceType : String
)
{}

