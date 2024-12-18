package com.example.ucp2room

import android.app.Application
import com.example.ucp2room.dependenciesinjection.ContainerApp

class TokoApp : Application() {
    // Fungsinya untuk menyimpan instance ContainerApp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        //Membuat instance ContainerApp
        containerApp =  ContainerApp(this)
        // instance adalah object yang dibuat dari class
    }
}