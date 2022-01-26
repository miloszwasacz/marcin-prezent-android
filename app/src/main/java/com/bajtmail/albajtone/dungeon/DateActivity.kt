package com.bajtmail.albajtone.dungeon

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.SimpleDateFormat
import java.util.*

class DateActivity : AppCompatActivity() {
    companion object {
        private const val REQUIRED_DATE = "11-03-2002"
        private const val DATE_FORMAT = "dd-MM-yyyy"
        private const val REQUIRED_DATE_NO_YEAR = "11-03"
        private const val DATE_FORMAT_NO_YEAR = "dd-MM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)
        val switch = findViewById<SwitchMaterial>(R.id.date_switch)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            switch.visibility = View.VISIBLE
            switch.setOnCheckedChangeListener { _, _ ->
                toggleTrophy(checkDate())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        toggleTrophy(checkDate())
    }

    private fun checkDate(): Boolean {
        val switch = findViewById<SwitchMaterial>(R.id.date_switch)
        val currentDate = SimpleDateFormat(
            if (switch.isChecked) DATE_FORMAT_NO_YEAR else DATE_FORMAT,
            Locale.US
        ).format(Date())
        return currentDate == if (switch.isChecked) REQUIRED_DATE_NO_YEAR else REQUIRED_DATE
    }

    private fun toggleTrophy(state: Boolean) {
        val imageView = findViewById<ImageView>(R.id.logo_imageview)
        imageView.setImageResource(if (state) R.drawable.ic_trophy_128dp else R.drawable.ic_archlinux_icon_crystal_64dp)
        imageView.contentDescription = if (state) "Kod do windy: 07734" else "arch_logo.svg"
    }
}