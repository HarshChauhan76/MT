package com.example.expensetrackerapp


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expensetrackerapp.data.Expense
import com.example.expensetrackerapp.data.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class ExpenseViewModel(
    private val expenseRepository : ExpenseRepository = Graph.expenseRepository
):ViewModel() {

    private var expanseDateState by mutableStateOf("")
    private var expenseTypeState by mutableStateOf("")
    private var expenseAmountState by mutableStateOf(0)

    private val _getAllExpense = MutableStateFlow<List<Expense>>(emptyList())
//    val getAllExpensee: StateFlow<List<Expense>> = _getAllExpense


    fun onExpenseDateChange(newDate: String) {
        expanseDateState = newDate
    }

    fun onExpenseTypeChange(newType: String) {
        expenseTypeState = newType
    }

    fun onExpenseAmountChange(newAmount: Double) {
        expenseAmountState = newAmount.toInt()
    }

    val getAllExpense: StateFlow<List<Expense>> = _getAllExpense

    init {
        viewModelScope.launch {
            expenseRepository.getExpense().collect { expenses ->
                _getAllExpense.value = expenses
            }
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.addAExpense(expense = expense)
        }
    }

    fun getExpenseById(id: Long): Flow<Expense> {
        return expenseRepository.getExpenseById(id = id)
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.updateAExpense(expense = expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.deleteAExpense(expense = expense)
        }
    }
}