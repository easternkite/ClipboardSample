package com.blucky.clipboardsample

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.widget.doOnTextChanged
import com.blucky.clipboardsample.databinding.ActivityViewBinding
import com.blucky.clipboardsample.ui.theme.ClipboardSampleTheme
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE

class ViewActivity : AppCompatActivity() {
    private val clipboardManager by lazy {
        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    private lateinit var binding: ActivityViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCopyInput()
        initPasteInput()

    }

    private fun initCopyInput() {
        binding.inputCopy.setEndIconOnClickListener {
            val text = binding.etCopy.text.toString()

            if (text.isEmpty()) {
                Toast.makeText(this, "text를 입력해주세요.", Toast.LENGTH_LONG).show()
                return@setEndIconOnClickListener
            }

            val clip = ClipData.newPlainText("label", text)
            clipboardManager.setPrimaryClip(clip)
            Toast.makeText(this, "복사되었습니다.", Toast.LENGTH_LONG).show()
        }
    }

    private fun initPasteInput() {
        binding.inputPaste.setEndIconOnClickListener {
            clipboardManager
                .primaryClip
                ?.getItemAt(0)
                ?.text
                ?.toString()
                ?.also { binding.etPaste.setText(it) }
        }
    }
}