package com.example.user.bluetoothcaptureclienttp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.io.IOException
import java.io.OutputStream
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG : String = "BluetoothCaptureClientTP"

    private val MAC_ADDR : String = "00:AA:BB:CC:DD:EE"
    private val UUID_ : String = "00001801-0000-1000-8000-00805f9b34fb"

    private var mBluetoothAdapter : BluetoothAdapter? = null
    private var mBluetoothDevice : BluetoothDevice? = null
    private var mBluetoothSocket : BluetoothSocket? = null
    private var mBluetoothServerSocket : BluetoothServerSocket? = null
    private var mOutputStream : OutputStream? = null

    private var mButton1 : Button? = null
    private var mButton2 : Button? = null
    private var mButton3 : Button? = null

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButton1 = findViewById(R.id.button) as Button
        mButton2 = findViewById(R.id.button2) as Button
        mButton3 = findViewById(R.id.button3) as Button

        mButton1!!.setOnClickListener(this)
        mButton2!!.setOnClickListener(this)
        mButton3!!.setOnClickListener(this)

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        mBluetoothDevice = mBluetoothAdapter!!.getRemoteDevice(MAC_ADDR)

        val runnable = object : Runnable {
            override fun run() {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                mBluetoothDevice = mBluetoothAdapter!!.getRemoteDevice(MAC_ADDR)

                Log.d(TAG, "" + mBluetoothDevice)

                try {
                    mBluetoothSocket = mBluetoothDevice!!.createInsecureRfcommSocketToServiceRecord(
                        UUID.fromString(UUID_)
                    )
                    Log.d(TAG, "" + mBluetoothSocket)
                    mBluetoothSocket!!.connect()
                    mOutputStream = mBluetoothSocket!!.getOutputStream()
                } catch (e : IOException) {
                    e.printStackTrace()
                }
            }
        }
        handler.post(runnable)

    }

    private fun sendCommand(command: String) {
        try {
            mOutputStream!!.write(command.toByteArray())
            Toast.makeText(this, "sent command [" + command + "]", Toast.LENGTH_SHORT)
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            mButton1 -> sendCommand("command 1")
            mButton2 -> sendCommand("command 2")
            mButton3 -> sendCommand("command 3")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            mBluetoothSocket!!.close()
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
}
