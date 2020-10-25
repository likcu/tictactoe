package com.example.tictactoe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.tictactoe.R
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.viewmodel.TicTacToeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class TicTacToeActivity : AppCompatActivity(){

    private val viewModel = TicTacToeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_tictactoe,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_reset -> {
                viewModel.onResetSelected()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}