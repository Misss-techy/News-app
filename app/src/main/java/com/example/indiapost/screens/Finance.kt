package com.example.indiapost.screens

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.indiapost.ui.theme.IndiaPostTheme

@Composable
fun FinanceScreen(modifier: Modifier = Modifier){
    Surface{
        Text("Finance")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    IndiaPostTheme {
        FinanceScreen()
    }
}