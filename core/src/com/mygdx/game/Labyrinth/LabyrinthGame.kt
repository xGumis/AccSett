package com.mygdx.game.Labyrinth

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.Ball
import com.mygdx.game.Menu
import com.mygdx.game.Sensor
import com.mygdx.game.Start
import ktx.actors.onClick

class LabyrinthGame(val game : Start, val sensor: Sensor) : Screen {

    private val world = World(Vector2(0f,0f),true)
    private val stage = Stage(ScreenViewport())
    private val UI = Stage(ScreenViewport())
    init{
        world.setContactListener(CollListener())
        Gdx.input.inputProcessor = UI
        //Preparing game stage
        val ratio = Gdx.graphics.width.toFloat() / Gdx.graphics.height.toFloat()
        stage.camera.position.set(0f,0f,0f)
        stage.camera.lookAt(0f,0f,0f)
        stage.camera.viewportWidth = 10f
        stage.camera.viewportHeight = 10f/ratio
        val labyrinth = Labyrinth(world, -4.5f, -4.5f, 9f)
        stage.addActor(labyrinth)
        val ball1 = Ball(world, -26 / 7f, 24 / 7f, 0.3f)
        val ball2 = Ball(world, -26 / 7f, -24 / 7f, 0.3f)
        val ball3 = Ball(world, 4.5f / 7f, 1 / 7f, 0.3f)
        stage.addActor(ball1)
        stage.addActor(ball2)
        stage.addActor(ball3)
        //Preparing UI
        val back = TextButton("Menu",game.skin)
        back.setSize(Gdx.graphics.width/4f,Gdx.graphics.height/10f)
        back.setPosition(10f,10f)
        back.onClick { game.screen = Menu(game,sensor) }
        UI.addActor(back)
        val reset = TextButton("Reset",game.skin)
        reset.setSize(Gdx.graphics.width/4f,Gdx.graphics.height/10f)
        reset.setPosition(10f,Gdx.graphics.height/10f+15f)
        reset.onClick {
            stage.actors.forEach { if(it is Ball)it.eliminate() }
            val ball1 = Ball(world, -26 / 7f, 24 / 7f, 0.3f); stage.addActor(ball1)
            val ball2 = Ball(world, -26 / 7f, -24 / 7f, 0.3f); stage.addActor(ball2)
            val ball3 = Ball(world, 4.5f / 7f, 1 / 7f, 0.3f); stage.addActor(ball3)
        }
        UI.addActor(reset)
    }
    override fun hide() {
    }
    override fun show() {
    }
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.6f, 0.4f, 0.3f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
        UI.act()
        UI.draw()
        world.gravity = sensor.read()
        world.step(Gdx.graphics.deltaTime, 6, 2)
    }
    override fun pause() {
    }
    override fun resume() {
    }
    override fun resize(width: Int, height: Int) {
    }
    override fun dispose() {
        stage.dispose()
        UI.dispose()
        world.dispose()
    }
}