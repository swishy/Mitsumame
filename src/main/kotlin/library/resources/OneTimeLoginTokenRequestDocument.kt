package com.st8vrt.mitsumame.library.resources

/**
 * Created by swishy on 30/10/13.
 */
public class OneTimeLoginTokenRequestDocument(

        // User the token is for
        val Username : String,

        // Unique Id for the device logging in.
        val DeviceId : String,

        // The type of device, i.e. iPhone, Android etc.
        val DeviceType : String
)
{}