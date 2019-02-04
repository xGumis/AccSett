package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20

class myColors {
    companion object {
        val empty = Color(0f,0f,0f,0f)
        val rand1 = Color.RED
        val rand2 = Color.BLUE
        val rand3 = Color.GREEN

        fun bckgrnd(){
            Gdx.gl.glClearColor(0f,0f,0f,1f)
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        }
    }
}