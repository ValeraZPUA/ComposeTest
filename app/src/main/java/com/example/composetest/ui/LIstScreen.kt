package com.example.composetest.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ListScreen(stringLst: ArrayList<String>, navController: NavHostController) {

    var message by remember{ mutableStateOf("") }
    val stringList = remember { mutableStateListOf<String>().also {
        it.addAll(stringLst)
    } }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = message,
                placeholder = { Text("Enter text") },
                leadingIcon = { Icon(Icons.Filled.Send, contentDescription = "send") },
                shape = MaterialTheme.shapes.medium.copy(CornerSize(100.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Red,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Green
                ),
                onValueChange = { newText -> message = newText },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    stringList.add(message)
                }
            ) {
                Text(text = "Add To List")
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(stringList) { index, item ->
                    Text(text = item,
                        modifier = Modifier.clickable {
                            navController.navigate("selectedItemScreen/${stringList[index]}")
                            println("print____${stringList[index]}")
                        }
                    )
                }
            }
        }
    }
}