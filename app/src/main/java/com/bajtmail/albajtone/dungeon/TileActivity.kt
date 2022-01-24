package com.bajtmail.albajtone.dungeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout

class TileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tile)

        var result = ""
        val gridLayout = findViewById<GridLayout>(R.id.gridlayout)

        val tile11 = findViewById<Button>(R.id.button11)
        val tile12 = findViewById<Button>(R.id.button12)
        val tile13 = findViewById<Button>(R.id.button13)
        val tile14 = findViewById<Button>(R.id.button14)

        val tile21 = findViewById<Button>(R.id.button21)
        val tile22 = findViewById<Button>(R.id.button22)
        val tile23 = findViewById<Button>(R.id.button23)
        val tile24 = findViewById<Button>(R.id.button24)

        val tile31 = findViewById<Button>(R.id.button31)
        val tile32 = findViewById<Button>(R.id.button32)
        val tile33 = findViewById<Button>(R.id.button33)
        val tile34 = findViewById<Button>(R.id.button34)

        val tile41 = findViewById<Button>(R.id.button41)
        val tile42 = findViewById<Button>(R.id.button42)
        val tile43 = findViewById<Button>(R.id.button43)
        val tile44 = findViewById<Button>(R.id.button44)

        tile11.setOnClickListener {
            throw NullPointerException()
        }
        tile12.setOnClickListener {
            throw NullPointerException()
        }
        tile13.setOnClickListener {
            result += tile13.text.toString()
        }
        tile14.setOnClickListener {
            throw NullPointerException()
        }

        tile21.setOnClickListener {
            throw NullPointerException()
        }
        tile22.setOnClickListener {
            if(result != (tile13.text.toString() + tile23.text.toString()))
                throw NullPointerException()
            result += tile22.text.toString()
        }
        tile23.setOnClickListener {
            if(result != tile13.text.toString())
                throw NullPointerException()
            result += tile23.text.toString()
        }
        tile24.setOnClickListener {
            throw NullPointerException()
        }

        tile31.setOnClickListener {
            throw NullPointerException()
        }
        tile32.setOnClickListener {
            if(result != (tile13.text.toString() + tile23.text.toString() + tile22.text.toString()))
                throw NullPointerException()
            result += tile32.text.toString()
        }
        tile33.setOnClickListener {
            if(result != (tile13.text.toString() + tile23.text.toString() + tile22.text.toString() + tile32.text.toString()))
                throw NullPointerException()
            result += tile33.text.toString()
        }
        tile34.setOnClickListener {
            throw NullPointerException()
        }

        tile41.setOnClickListener {
            throw NullPointerException()
        }
        tile42.setOnClickListener {
            throw NullPointerException()
        }
        tile43.setOnClickListener {
            if(result != (tile13.text.toString() + tile23.text.toString() + tile22.text.toString() + tile32.text.toString() + tile33.text.toString()))
                throw NullPointerException()
            val intent = Intent(this, LightActivity::class.java)
            startActivity(intent)
        }
        tile44.setOnClickListener {
            throw NullPointerException()
        }
    }
}