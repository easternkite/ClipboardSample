package com.blucky.clipboardsample

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blucky.clipboardsample.ui.theme.ClipboardSampleTheme

class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                CopyAndPasteScreen(Modifier.fillMaxWidth())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CopyAndPasteScreen(modifier: Modifier = Modifier) {
    var copyText by rememberSaveable {
        mutableStateOf("")
    }
    var pasteText by rememberSaveable {
        mutableStateOf("")
    }

    val localContext = LocalContext.current
    val clipboardManager = localContext.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            modifier = modifier.padding(10.dp),
            value = copyText,
            onValueChange = { copyText = it },
            label = { Text("Copy") },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_content_copy_24),
                    contentDescription = "Copy",
                    modifier = Modifier.clickable {
                        if (copyText.isEmpty()) {
                            Toast.makeText(localContext, "text를 입력해주세요.", Toast.LENGTH_LONG).show()
                            return@clickable
                        }

                        val clip = ClipData.newPlainText("label", copyText)
                        clipboardManager.setPrimaryClip(clip)
                        Toast.makeText(localContext, "복사되었습니다.", Toast.LENGTH_LONG).show()
                    }
                )
            }
        )

        Divider(
            modifier = modifier,
            thickness = 10.dp,
            color = Color(0xE9E9E9E9)
        )

        OutlinedTextField(
            modifier = modifier.padding(10.dp),
            value = pasteText,
            onValueChange = {
                pasteText = it

                            },
            label = { Text("Paste") },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_content_paste_24),
                    contentDescription = "Paste",
                    modifier = Modifier.clickable {
                        clipboardManager
                            .primaryClip
                            ?.getItemAt(0)
                            ?.text
                            ?.toString()
                            ?.also { pasteText = it }
                    }
                )
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClipboardSampleTheme {
        CopyAndPasteScreen(Modifier.fillMaxWidth())
    }
}