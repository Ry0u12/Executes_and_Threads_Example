package com.example.executes_and_threads_example

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Ejecucion_Segundo_Plano : Service() {

    private lateinit var executor: ExecutorService

    override fun onCreate() {
        super.onCreate()
        executor = Executors.newSingleThreadExecutor()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        executor.submit {
            // Simula una tarea larga
            for (i in 1..100) {
                Thread.sleep(100) // Simula tiempo de trabajo
                val contador_send = Intent("Contadorsito")
                contador_send.putExtra("n_actual", i)
                LocalBroadcastManager.getInstance(this).sendBroadcast(contador_send)
                Thread.sleep(100)
            }
            stopSelf()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}