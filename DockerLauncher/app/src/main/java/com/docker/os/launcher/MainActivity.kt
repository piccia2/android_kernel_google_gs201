package com.docker.os.launcher

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val statusText = findViewById<TextView>(R.id.statusText)
        
        // Try to verify if Docker daemon is running locally
        val isDockerRunning = checkDockerDaemon()
        if (isDockerRunning) {
            statusText.text = "● RUNNING"
            statusText.setTextColor(resources.getColor(android.R.color.holo_green_light, null))
        } else {
            statusText.text = "● OFFLINE (NO ROOT / NO DAEMON)"
            statusText.setTextColor(resources.getColor(android.R.color.holo_red_light, null))
            Toast.makeText(this, "Docker Daemon not detected. Ensure ROM is rooted and running dockerd.", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Checks if the docker daemon is active on the local Android kernel via command line shell.
     * Note: Docker commands require root permissions (su) on the device.
     */
    private fun checkDockerDaemon(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("su", "-c", "docker info"))
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val line = reader.readLine()
            process.destroy()
            line != null && !line.contains("error", ignoreCase = true)
        } catch (e: Exception) {
            // Fallback: If root or Docker CLI is not found, return false.
            false
        }
    }

    override fun onBackPressed() {
        // Since this is a Launcher/Home screen, we disable standard back button action
        // so that the user cannot exit the dashboard to an empty screen.
        // Do nothing or minimize/refresh the page.
    }
}
