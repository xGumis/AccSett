package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.collision.BoundingBox
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.utils.viewport.ScreenViewport


class Game : ApplicationAdapter() {
    lateinit var world : World
    lateinit var sense : Sensor
    lateinit var stage : Stage
    lateinit var UI : Stage
    lateinit var slide : Slider

    override fun create() {
        stage = Stage(ScreenViewport())
        UI = Stage(ScreenViewport())
        world = World(Vector2(0f,-10f),true)
        Gdx.input.inputProcessor = UI
        val ratio = Gdx.graphics.width.toFloat() / Gdx.graphics.height.toFloat()
        stage.camera.position.set(0f,0f,0f)
        stage.camera.lookAt(0f,0f,0f)
        stage.camera.viewportWidth = 10f
        stage.camera.viewportHeight = 10f/ratio
        sense = Sensor()
        slide = Slider(0.5f,10f,0.5f,false,Skin(Gdx.files.internal("skin/glassy-ui.json")))
        slide.value = 1f
        slide.setPosition(Gdx.graphics.width/4f,0f)
        slide.setSize(Gdx.graphics.width/2f,Gdx.graphics.height/5f)
        UI.addActor(slide)
        val frame = Frame(world,stage.camera.viewportWidth*0.9f,stage.camera.viewportHeight/2f)
        val ball = Ball(world,0f,0f)
        stage.addActor(ball)
        stage.addActor(frame)

    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
        UI.act()
        UI.draw()
        sense.scale = slide.value
        world.gravity = sense.read()
        world.step(Gdx.graphics.deltaTime, 6, 2)
    }

    override fun dispose() {
    }
}
