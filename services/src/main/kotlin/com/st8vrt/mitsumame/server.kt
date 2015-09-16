package com.st8vrt.mitsumame

import org.wasabi.app.AppServer

fun main(args: Array<String>) {

    System.out.println("Welcome to the exciting world of Kotlin");
    var mitsumame = MitsumameServer()
    mitsumame.start(true)
}

