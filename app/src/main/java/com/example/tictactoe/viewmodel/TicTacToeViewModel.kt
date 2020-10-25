package com.example.tictactoe.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import com.example.tictactoe.model.Board

class TicTacToeViewModel {

    private val model: Board = Board()
    val cells = ObservableArrayMap<String, String>()
    val winner = ObservableField<String>()

    fun onResetSelected(){
        model.restart()
        winner.set(null)
        cells.clear()
    }

    fun onClickedCellAt(row: Int, col: Int){
        val playerThatMoved = model.mark(row,col)
        if (playerThatMoved != null){
            cells["" + row + col] = playerThatMoved.toString()
            winner.set(model.getWinner().toString())
        }
    }
}