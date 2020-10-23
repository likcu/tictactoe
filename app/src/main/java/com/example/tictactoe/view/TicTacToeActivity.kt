package com.example.tictactoe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.tictactoe.R
import com.example.tictactoe.model.Board
import com.example.tictactoe.presenter.TicTacToePresenter
import kotlinx.android.synthetic.main.activity_main.*

class TicTacToeActivity : AppCompatActivity(), TicTacToeView{
    private var model: Board? = null

    /*** View */
    private lateinit var btnGrid : ViewGroup
    private lateinit var winPlayerViewGroup: View
    private lateinit var winPlayerLabel: TextView

    private val presenter = TicTacToePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        winPlayerLabel = winnerPlayerLabel
        winPlayerViewGroup = winnerPlayerViewGroup
        btnGrid = buttonGrid
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_tictactoe,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_reset -> {
                model?.restart()
                presenter.onResetSelected()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /** 拿到view，然后跟model进行通讯，完了再刷新view*/
    fun onCellClicked(v: View){
        val tag = v.tag.toString()
        val row = Integer.valueOf(tag.substring(0,1))
        val col = Integer.valueOf(tag.substring(1,2))
        Log.i("board", "Click Row: [$row,$col]")

        presenter.onButtonSelected(row,col)
    }

    override fun showWinner(winningPlayerLabel: String) {
        winPlayerLabel.text = winningPlayerLabel
        winPlayerViewGroup.visibility = View.VISIBLE
    }

    override fun clearWinnerDisplay() {
        winPlayerLabel.text = ""
        winPlayerViewGroup.visibility = View.GONE
    }

    override fun clearButtons() {
        for (i in 0 until buttonGrid.childCount){
            (buttonGrid.getChildAt(i) as Button).text = ""
        }
    }

    override fun setButtonText(row: Int, col: Int, text: String) {
        val btn = buttonGrid.findViewWithTag<Button>(""+row+col)
        if (btn != null){
            btn.text = text
        }
    }


}