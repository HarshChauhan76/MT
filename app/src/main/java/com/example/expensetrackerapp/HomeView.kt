package com.example.expensetrackerapp

import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.expensetrackerapp.data.Expense
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController : NavController,
    viewModel: ExpenseViewModel
){
    val id = remember{ mutableStateOf(0L) }
    val selectedDate = remember{ mutableStateOf("") }
    val expenseType = remember{ mutableStateOf("") }
    val amount = remember{ mutableIntStateOf(0) }
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDate.value =
                "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"  // Format the selected date
        }, year, month, day
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(1f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        OutlinedTextField(value = selectedDate.value, onValueChange = {selectedDate.value = it},
       label = { Text("Select Date")},
           textStyle = TextStyle(color = Color.Black),
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black),
                focusedLabelColor = colorResource(id = R.color.black),
                unfocusedLabelColor = colorResource(id = R.color.black)

            )

       )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = expenseType.value, onValueChange = {expenseType.value = it},
            label = { Text(text = "Type of Expense")},
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedTextColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black),
                focusedLabelColor = colorResource(id = R.color.black),
                unfocusedLabelColor = colorResource(id = R.color.black)

            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = amount.intValue.toString(), onValueChange = {it.toIntOrNull()?.let { newAmount -> amount.intValue = newAmount }},
            label = { Text(text = "Amount")},
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.black),
                unfocusedBorderColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black),
                focusedLabelColor = colorResource(id = R.color.black),
                unfocusedLabelColor = colorResource(id = R.color.black)

            )
            )

        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = {
           val expense = Expense(id.value,selectedDate.value,expenseType.value, amount.value)
            viewModel.addExpense(expense)
             navController.navigate("list_view")
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(text = "Save")

        }

    }
}