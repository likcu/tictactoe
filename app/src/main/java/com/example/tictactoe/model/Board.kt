package com.example.tictactoe.model

import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class Board {

    enum class GameState { IN_PROGRESS, FINISHED }

    private lateinit var currentTurn: Player

    //mark which player is the winner
    private var winner: Player? = null

    //3 to 3 board having 9 cells
    private var cells = Array(3) { Array<Cell>(3) { Cell() } }

    private lateinit var gameState: GameState

    init {
        restart()
    }

    fun restart() {
        clearCells()
        currentTurn = Player.X
        gameState = GameState.IN_PROGRESS

    }

    fun mark(row: Int, col: Int): Player? {
        var playerThatMoved: Player? = null
        if (isValid(row, col)) {
            cells[row][col]?.setValue(currentTurn)
            playerThatMoved = currentTurn

            if (isWinningMoveByPlayer(currentTurn, row, col)) {
                gameState = GameState.FINISHED
                winner = currentTurn
            } else {
                flipCurrentTurn()
            }
        }
        return playerThatMoved
    }

    fun getWinner(): Player? {
        return winner
    }

    private fun clearCells() {
        for (i in 0..2) {
            for (j in 0..2) {
                cells[i][j] = Cell()
            }
        }
    }

    private fun isValid(row : Int, col : Int) : Boolean{
        if (gameState == GameState.FINISHED){
            return false
        } else if (isOutOfBounds(row) || isOutOfBounds(col)){
            return false
        }else return !isCellValueAlreadySet(row,col)
    }

    private fun isOutOfBounds(index: Int) : Boolean{
        return index < 0 || index > 2
    }

    private fun isCellValueAlreadySet(row : Int, col : Int): Boolean {
        return cells[row][col].getValue() != null
    }

    private fun isWinningMoveByPlayer(p: Player, currentRow : Int, currentCol : Int): Boolean {
        return(cells[currentRow][0]?.getValue() == p && cells[currentRow][1]?.getValue() == p && cells[currentRow][2]?.getValue() == p
                ||cells[0][currentCol]?.getValue() == p && cells[1][currentCol]?.getValue() == p && cells[2][currentCol]?.getValue() == p
                ||currentRow == currentCol && cells[0][0]?.getValue() == p && cells[1][1]?.getValue() == p && cells[2][2]?.getValue() == p
                ||currentRow + currentCol == 2 && cells[0][2]?.getValue() == p && cells[1][1]?.getValue() == p && cells[2][0]?.getValue() == p)
    }

    private fun flipCurrentTurn(){
        if (currentTurn == Player.X){
            currentTurn = Player.O
        } else {
            currentTurn = Player.X
        }
    }
}