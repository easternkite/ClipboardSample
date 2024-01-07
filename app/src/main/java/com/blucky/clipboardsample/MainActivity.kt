package com.blucky.clipboardsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.blucky.clipboardsample.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnView.setOnClickListener {
            startActivityTo(ViewActivity::class.java)
        }

        binding.btnCompose.setOnClickListener {
            startActivityTo(ComposeActivity::class.java)
        }
    }
    private fun Context.startActivityTo(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}