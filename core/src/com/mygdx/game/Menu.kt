package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.Labyrinth.LabyrinthGame
import ktx.actors.onClick

class Menu(val game : Start, val sensor: Sensor) : Screen {
    private val UI = Stage(ScreenViewport())
    init{
        Gdx.input.inputProcessor = UI
        val label = Label(sensor.scale.toString(),game.skin,"black")
        label.setSize(Gdx.graphics.width/2f,Gdx.graphics.height/10f)
        label.setPosition(Gdx.graphics.width/4f,Gdx.graphics.height*(8/10f))
        UI.addActor(label)
        val settings = TextButton("Sesitivity",game.skin)
        settings.setSize(Gdx.graphics.width/2f,Gdx.graphics.height/10f)
        settings.setPosition(Gdx.graphics.width/4f,Gdx.graphics.height*(2/10f))
        settings.onClick { game.screen = SensitivitySetting(game,sensor) }
        UI.addActor(settings)
        val labyrinth = TextButton("Labyrinth",game.skin)
        labyrinth.setSize(Gdx.graphics.width/2f,Gdx.graphics.height/10f)
        labyrinth.setPosition(Gdx.graphics.width/4f,Gdx.graphics.height*(4/10f))
        labyrinth.onClick { game.screen = LabyrinthGame(game,sensor) }
        UI.addActor(labyrinth)
        val hex = TextButton("Hex",game.skin)
        hex.setSize(Gdx.graphics.width/2f,Gdx.graphics.height/10f)
        hex.setPosition(Gdx.graphics.width/4f,Gdx.graphics.height*(6/10f))
        hex.onClick { game.screen = Menu(game,sensor) }
        UI.addActor(hex)
    }
    override fun hide() {
    }
    override fun show() {
    }
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        UI.act()
        UI.draw()
    }
    override fun pause() {
    }
    override fun resume() {
    }
    override fun resize(width: Int, height: Int) {
    }
    override fun dispose() {

    }
}