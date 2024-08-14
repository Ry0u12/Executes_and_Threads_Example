package com.example.executes_and_threads_example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    //Declarar las variables que contendran la barra de carga y demas items del activity
    private lateinit var pb_barrita: ProgressBar
    private lateinit var btn_inicio: Button
    private lateinit var tv_pruebita: TextView

    private val countReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val progress = intent?.getIntExtra("n_actual", 0) ?: 0
            pb_barrita.progress = progress
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //
        pb_barrita = findViewById(R.id.pb_barrita)
        btn_inicio = findViewById(R.id.btn_inicio)
        tv_pruebita = findViewById(R.id.tv_prueba)

        pb_barrita.max = 100

        LocalBroadcastManager.getInstance(this).registerReceiver(countReceiver, IntentFilter("Contadorsito"))

        btn_inicio.setOnClickListener{
            val intentsote = Intent(this, Ejecucion_Segundo_Plano::class.java)
            startService(intentsote)
        }
    }
}