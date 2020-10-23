package com.example.tictactoe.presenter

import android.view.View
import android.widget.Button
import com.example.tictactoe.model.Board
import com.example.tictactoe.view.TicTacToeView

class TicTacToePresenter(view: TicTacToeView) {

    private val view = view
    private val model = Board()

    fun onButtonSelected(row: Int, col: Int){
        val playerThatMoved = model.mark(row,col)
        if (playerThatMoved != null){
            view.setButtonText(row, col, playerThatMoved.toString())
            if (model?.getWinner() != null) {
                view.showWinner(playerThatMoved.toString())
            }
        }
    }

    fun onResetSelected(){
        model.restart()
        view.clearWinnerDisplay()
        view.clearButtons()
    }
}