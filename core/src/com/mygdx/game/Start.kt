package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class Start : Game() {
    lateinit var skin : Skin
    lateinit var sensor : Sensor
    override fun create() {
        skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))
        sensor = Sensor()
        this.screen = Menu(this, sensor)
    }
}