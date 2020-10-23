package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    enum class Player{X,O}
    enum class GameState{IN_PROGRESS, FINISHED}

    //2 players, 1 is playerX, 2 is playerO
    private lateinit var currentTurn : Player
    //mark which player is the winner
    private var winner : Player? = null
    //3 to 3 board having 9 cells
    private var cells = Array(3){Array<Cell>(3){Cell()} }
    //1 is onContinue the game, 2 is finish the game
    private lateinit var gameState : GameState

    inner class Cell {
        private var value : Player? = null
        fun setValue(player: Player){
            this.value = player
        }
        fun getValue(): Player? {
            return this.value
        }
    }

    /*** View */
    private lateinit var btnGrid : ViewGroup
    private lateinit var winPlayerViewGroup: View
    private lateinit var winPlayerLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        winPlayerLabel = winnerPlayerLabel
        winPlayerViewGroup = winnerPlayerViewGroup
        btnGrid = buttonGrid
        restart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_tictactoe,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_reset -> {
                restart()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun onCellClicked(v: View){
        val tag = v.tag.toString()
        val row = Integer.valueOf(tag.substring(0,1))
        val col = Integer.valueOf(tag.substring(1,2))
        Log.i("board", "Click Row: [$row,$col]")

        val playerThatMoved = mark(row, col)
        Log.d("player",playerThatMoved.toString())
        if (playerThatMoved != null){
            (v as Button).text = playerThatMoved.toString()
            if (getWinner() != null) {
                winPlayerLabel.text = playerThatMoved.toString()
                winPlayerViewGroup.visibility = View.VISIBLE
            }
        }
    }

    private fun restart(){
        clearCells()
        currentTurn = Player.X
        gameState = GameState.IN_PROGRESS

        /** Reset View */
        winPlayerViewGroup.visibility = View.GONE
        winPlayerLabel.text = ""

        for(i in 0 until buttonGrid.childCount){
            (buttonGrid.getChildAt(i) as Button).text = ""
        }
    }

    private fun mark(row: Int, col: Int): Player? {
        var playerThatMoved : Player? = null
        if (isValid(row, col)){
            cells[row][col]?.setValue(currentTurn)
            playerThatMoved = currentTurn

            if (isWinningMoveByPlayer(currentTurn, row, col)) {
                gameState = GameState.FINISHED
                winner = currentTurn
            }else{
                flipCurrentTurn()
            }
        }
        return playerThatMoved
    }

    private fun getWinner(): Player? {
        return winner
    }

    private fun clearCells(){
        for (i in 0..2){
            for (j in 0..2){
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