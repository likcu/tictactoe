package com.example.tictactoe.model

import com.example.tictactoe.controller.MainActivity


class Cell {
    private var value: Player? = null
    fun setValue(player: Player) {
        this.value = player
    }

    fun getValue(): Player? {
        return this.value
    }
}
