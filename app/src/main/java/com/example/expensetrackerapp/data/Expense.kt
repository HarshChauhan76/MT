package com.example.expensetrackerapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense-table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "expense-date")
    val date: String = "",
    @ColumnInfo(name = "expense-name")
    val expense: String = "",
    @ColumnInfo(name = "expense-amount")
    val amount: Int = 0
)