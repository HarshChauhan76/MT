package com.example.expensetrackerapp

import android.content.Context
import androidx.room.Room
import com.example.expensetrackerapp.data.ExpenseDataBase
import com.example.expensetrackerapp.data.ExpenseRepository

object Graph {
    private lateinit var database : ExpenseDataBase
    val expenseRepository by lazy {
        ExpenseRepository(expenseDao = database.expenseDao())
    }
    fun provide(context : Context){
        database = Room.databaseBuilder(context,ExpenseDataBase::class.java,"expensetracker.db").build()
    }
}