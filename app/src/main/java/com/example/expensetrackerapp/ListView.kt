package com.example.expensetrackerapp

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.expensetrackerapp.data.Expense


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListView(id : Long,
             viewModel: ExpenseViewModel,
             navController: NavController
) {
    val expenses = viewModel.getAllExpense.collectAsState().value
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(title =
          if (id!=0L){
              stringResource(id = R.string.update_expense)
          }
            else{
               stringResource(id = R.string.add_expense)
          })
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            this.items(expenses){ expense ->
                ExpenseTextField(expense = expense, viewModel = viewModel)
            }
        }
    }
}
@Composable
fun ExpenseTextField(expense: Expense,
                     viewModel: ExpenseViewModel){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { viewModel.updateExpense(expense) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
      Column(
          modifier = Modifier.padding(16.dp),
          verticalArrangement = Arrangement.Center
      ) {
          Text(text = "ID :${expense.id}", color = Color.Black)
          Text(text = "Date :${expense.date}", color = Color.Black)
          Text(text = "Type :${expense.expense}",color = Color.Black)
          Text(text = "Amount :${expense.amount}",color = Color.Black)
      }
    }
}