package com.bajtmail.albajtone.dungeon

import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.hardware.camera2.CameraAccessException
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*

class LightActivity : AppCompatActivity() {
    companion object {
        private const val DOT: Long = 150
        private const val DASH: Long = 500
        private const val NEXT_SIGN: Long = 500
        private const val NEXT_LETTER: Long = 1000
    }
    private val errors = listOf(
        "Nie",
        "Nope",
        "Błędna odpowiedź",
        "Próbuj dalej"
    )
    private var sequenceStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light)

        val bigRedButton = findViewById<Button>(R.id.big_red_button)
        val linearLayout = findViewById<LinearLayout>(R.id.linear_layout)
        val inputField = findViewById<TextInputLayout>(R.id.input_field)
        val editText = findViewById<TextInputEditText>(R.id.edit_text)
        val checkButton = findViewById<Button>(R.id.button_check)

        val camManager = getSystemService(Context.CAMERA_SERVICE)
        bigRedButton.setOnClickListener {
            if (!sequenceStarted) {
                if (camManager == null || camManager !is CameraManager) startSequence()
                else {
                    try {
                        val cameraId = camManager.cameraIdList.first()
                        startSequence(camManager, cameraId)
                    } catch (e: Exception) {
                        when (e) {
                            is CameraAccessException,
                            is NoSuchElementException -> startSequence()
                            else -> throw e
                        }
                    }
                }
            }
        }
        var clickCount = 0
        checkButton.setOnClickListener {
            val text = editText.text.toString().trim().uppercase()
            if (text == "NIRCEK") {
                val intent = Intent(this, DateActivity::class.java)
                startActivity(intent)
            } else {
                clickCount++
                val message = when (clickCount) {
                    6 -> throw NullPointerException()
                    5 -> "Wkurzasz mnie już"
                    else -> errors.shuffled().take(1)[0]
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startSequence(camManager: CameraManager? = null, cameraId: String? = null) = lifecycleScope.launch {
        sequenceStarted = true
        val sequence: Array<Array<Long>> = arrayOf(
            arrayOf(DASH, DOT), //N
            arrayOf(DOT, DOT), //I
            arrayOf(DOT, DASH, DOT), //R
            arrayOf(DASH, DOT, DASH, DOT), //C
            arrayOf(DOT), //E
            arrayOf(DASH, DOT, DASH) //K
        )
        for (letter in sequence) {
            for (time in letter) {
                flashOn(camManager, cameraId)
                delay(time)
                flashOff(camManager, cameraId)
                delay(NEXT_SIGN)
            }
            delay(NEXT_LETTER)
        }
        sequenceStarted = false
    }

    private fun flashOn(camManager: CameraManager?, cameraId: String?) {
        if (camManager == null || !camManager.setFlashOn(cameraId)) {
            val backupLight = findViewById<View>(R.id.backup_light)
            backupLight.visibility = View.VISIBLE
        }
    }

    private fun flashOff(camManager: CameraManager?, cameraId: String?) {
        if (camManager == null || !camManager.setFlashOff(cameraId)) {
            val backupLight = findViewById<View>(R.id.backup_light)
            backupLight.visibility = View.INVISIBLE
        }
    }

    private fun CameraManager.setFlashOn(cameraId: String?): Boolean {
        cameraId ?: return false
        return try {
            setTorchMode(cameraId, true)
            true
        } catch (e: CameraAccessException) {
            false
        }
    }

    private fun CameraManager.setFlashOff(cameraId: String?): Boolean {
        cameraId ?: return false
        setTorchMode(cameraId, false)
        return true
    }
}