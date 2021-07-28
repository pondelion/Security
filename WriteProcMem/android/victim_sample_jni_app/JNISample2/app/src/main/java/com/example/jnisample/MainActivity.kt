package com.example.jnisample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val vm = NativeCounter()
    final val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateCountText();
        val job = object : Runnable {
            override fun run() {
                updateCountText()
                handler.postDelayed(this, 100)
            }
        }
        handler.post(job)
    }

    fun updateCountText() {
        val text = "0x" + java.lang.Long.toHexString(vm.getValuePtr()) + " : " + vm.getValue().toString() + "\n"
        Log.d("counter", vm.getValue().toString())
        findViewById<TextView>(R.id.sample_text).text = text
    }

    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}