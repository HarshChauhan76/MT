package com.example.expensetrackerapp.data

import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao){

    suspend fun addAExpense(expense: Expense){
        expenseDao.addExpense(expense)
    }
    fun getExpense(): Flow<List<Expense>> = expenseDao.getAllExpense()

    fun getExpenseById(id : Long) : Flow<Expense>{
       return expenseDao.getExpenseById(id)
    }
    suspend fun updateAExpense(expense: Expense){
        expenseDao.updateExpense(expense)
    }
    suspend fun deleteAExpense(expense: Expense){
        expenseDao.deleteExpense(expense)
    }
}