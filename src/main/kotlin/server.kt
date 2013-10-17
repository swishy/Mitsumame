package com.st8vrt.mitsumame

import org.wasabi.app.AppServer
import interceptors.useMitsumameAuthentication
import interceptors.useMitsumameEncryption
import com.st8vrt.mitsumame.webservices.core

fun main(args: Array<String>) {

    System.out.println("Welcome to the exciting world of Kotlin");
    MitsumameServer()
}

