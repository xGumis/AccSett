package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.actors.onChange
import ktx.actors.onClick


class SensitivitySetting(val game : Start,val sensor: Sensor) : Screen {
    private val world = World(Vector2(0f,0f),true)
    private val stage = Stage(ScreenViewport())
    private val UI = Stage(ScreenViewport())
    private val slide : Slider
    private var ballNum = 3

    init{
        Gdx.input.inputProcessor = UI
        val ratio = Gdx.graphics.width.toFloat() / Gdx.graphics.height.toFloat()
        stage.camera.position.set(0f,0f,0f)
        stage.camera.lookAt(0f,0f,0f)
        stage.camera.viewportWidth = 10f
        stage.camera.viewportHeight = 10f/ratio
        slide = Slider(0.5f,10f,0.5f,false,Skin(Gdx.files.internal("skin/glassy-ui.json")))
        slide.value = sensor.scale
        slide.setPosition(Gdx.graphics.width/4f,Gdx.graphics.height/10f+10f)
        slide.setSize(Gdx.graphics.width/2f,Gdx.graphics.height/10f)
        slide.onChange { sensor.scale = slide.value }
        UI.addActor(slide)
        val back = TextButton("Menu",game.skin)
        back.setSize(Gdx.graphics.width/2f,Gdx.graphics.height/10f)
        back.setPosition(Gdx.graphics.width/4f,10f)
        back.onClick { game.screen = Menu(game,sensor) }
        UI.addActor(back)
        val frame = Frame(world,stage.camera.viewportWidth*0.9f,stage.camera.viewportHeight/2f)
        stage.addActor(frame)
    }
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        if(ballNum>0){
            val ball = Ball(world,0f,0f,0.3f)
            stage.addActor(ball)
            ballNum--
        }
        stage.act()
        stage.draw()
        UI.act()
        UI.draw()
        world.gravity = sensor.read()
        world.step(Gdx.graphics.deltaTime, 6, 2)
    }

    override fun hide() {
    }

    override fun show() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        stage.dispose()
        world.dispose()
        UI.dispose()
    }
}
