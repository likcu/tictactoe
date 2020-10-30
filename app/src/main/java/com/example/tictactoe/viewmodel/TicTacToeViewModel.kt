package com.example.tictactoe.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoe.model.Board

class TicTacToeViewModel : ViewModel(){

    private val model: Board = Board()
    private val boardLiveData : MutableLiveData<Board> by lazy {
        MutableLiveData<Board>()
    }
    val cells = ObservableArrayMap<String, String>()
    val winner = ObservableField<String>()

    fun getBoard() : MutableLiveData<Board>{
        return boardLiveData
    }

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