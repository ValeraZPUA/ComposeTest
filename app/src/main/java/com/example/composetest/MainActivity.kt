package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    private val stringLst = arrayListOf("first",
                                "second",
                                "third",
                                "fourth")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val message = remember{ mutableStateOf("") }
            val stringList = remember { mutableStateListOf<String>().also {
                it.addAll(stringLst)
            } }

            Box(modifier = Modifier.fillMaxSize()
                .padding(5.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    InputTextWidget(message)
                    ButtonAdd(message = message, stringList)
                    StringList(stringList)
                }
            }

        }
    }
}

@Composable
fun InputTextWidget(message: MutableState<String>) {
    OutlinedTextField(
        value = message.value,
        placeholder = { Text("Enter text") },
        leadingIcon = { Icon(Icons.Filled.Send, contentDescription = "send") },
        shape = MaterialTheme.shapes.medium.copy(CornerSize(100.dp)),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Red,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Green
        ),
        onValueChange = { newText -> message.value = newText },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun ButtonAdd(message: MutableState<String>, stringList: SnapshotStateList<String>) {
    Button(
        modifier = Modifier.padding(5.dp),
        onClick = {
            stringList.add(message.value)
        }
    ) {
        Text(text = "Add To List")
    }
}

@Composable
fun StringList(stringList: SnapshotStateList<String>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(stringList) { index, item ->
            Text(text = item,
                 modifier = Modifier.clickable {
                    println("print____${stringList[index]}")
                 }
            )
        }
    }
}
