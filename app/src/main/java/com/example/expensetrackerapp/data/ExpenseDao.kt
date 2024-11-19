package com.example.expensetrackerapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addExpense(expenseEntity : Expense)

    @Query("Select * from `expense-table`")
    abstract fun getAllExpense():Flow<List<Expense>>

    @Update
    abstract suspend fun updateExpense(expenseEntity : Expense)

    @Delete
    abstract suspend fun deleteExpense(expenseEntity: Expense)

    @Query("Select * from `expense-table` where id=:id")
    abstract fun getExpenseById(id:Long): Flow<Expense>

}