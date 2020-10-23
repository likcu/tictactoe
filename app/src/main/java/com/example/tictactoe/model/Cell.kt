package com.example.tictactoe.model


class Cell {
    private var value: Player? = null
    fun setValue(player: Player) {
        this.value = player
    }

    fun getValue(): Player? {
        return this.value
    }
}
