package com.bajtmail.albajtone.dungeon

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity: AppCompatActivity() {
    private val permissions = listOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
        android.Manifest.permission.ACCESS_NETWORK_STATE,
        android.Manifest.permission.ACCESS_WIFI_STATE,
        android.Manifest.permission.BLUETOOTH,
        android.Manifest.permission.BLUETOOTH_ADMIN,
        android.Manifest.permission.BROADCAST_STICKY,
        android.Manifest.permission.CALL_PHONE,
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.CHANGE_NETWORK_STATE,
        android.Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
        android.Manifest.permission.CHANGE_WIFI_STATE,
        android.Manifest.permission.DISABLE_KEYGUARD,
        android.Manifest.permission.EXPAND_STATUS_BAR,
        android.Manifest.permission.GET_ACCOUNTS,
        android.Manifest.permission.GET_PACKAGE_SIZE,
        android.Manifest.permission.INTERNET,
        android.Manifest.permission.KILL_BACKGROUND_PROCESSES,
        android.Manifest.permission.MODIFY_AUDIO_SETTINGS,
        android.Manifest.permission.NFC,
        android.Manifest.permission.READ_CALENDAR,
        android.Manifest.permission.READ_CALL_LOG,
        android.Manifest.permission.READ_CONTACTS,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_PHONE_STATE,
        android.Manifest.permission.READ_SMS,
        android.Manifest.permission.READ_SYNC_SETTINGS,
        android.Manifest.permission.READ_SYNC_STATS,
        android.Manifest.permission.RECEIVE_BOOT_COMPLETED,
        android.Manifest.permission.RECEIVE_MMS,
        android.Manifest.permission.RECEIVE_SMS,
        android.Manifest.permission.RECEIVE_WAP_PUSH,
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.REORDER_TASKS,
        android.Manifest.permission.SEND_SMS,
        android.Manifest.permission.SET_WALLPAPER,
        android.Manifest.permission.SET_WALLPAPER_HINTS,
        android.Manifest.permission.TRANSMIT_IR,
        android.Manifest.permission.USE_SIP,
        android.Manifest.permission.VIBRATE,
        android.Manifest.permission.WAKE_LOCK,
        android.Manifest.permission.WRITE_CALENDAR,
        android.Manifest.permission.WRITE_CALL_LOG,
        android.Manifest.permission.WRITE_CONTACTS,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_SYNC_SETTINGS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val welcomeTextView = findViewById<TextView>(R.id.welcome_textview)
        val enterDungeonButton = findViewById<Button>(R.id.enter_button)

        enterDungeonButton.setOnClickListener {
            if(checkPermissions()) enterDungeon()
            else requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            1 -> {
                if(grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED })
                    enterDungeon()
                else
                    Toast.makeText(this, "Tylko ci, którzy wyzbędą się wszelkich praw do prywatności, mogą wejść do Kryjówki Al Bajtone", Toast.LENGTH_LONG).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun enterDungeon() {
        val intent = Intent(this, TileActivity::class.java)
        startActivity(intent)
    }

    private fun checkPermissions(): Boolean {
        return permissions.all {
            checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestPermissions() {
        requestPermissions(permissions.toTypedArray(), 1)
    }
}