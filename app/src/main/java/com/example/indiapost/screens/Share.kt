package com.example.indiapost.screens

import android.content.Intent


fun shareLink(url: String ): Intent {

    val type = "text/plain"
    val subject = "share"

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = type
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, url)

    return intent
}