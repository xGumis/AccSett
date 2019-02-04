package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.actors.onClick
import kotlin.math.sqrt

class HexGame(val game : Start,val sensor: Sensor, numOfHexes: Int) : Screen {

    private val world = World(Vector2(0f,-10f),true)


    private val batch = SpriteBatch()
    private val stage = Stage(ScreenViewport())
    private val sr = ShapeRenderer()
    private val sizex = Gdx.graphics.width-200f
    private val sizey = sizex* (2f/ sqrt(3f))
    private val map : HexMap
    private val debug = Box2DDebugRenderer()
    private val buttonsy = Stage(ScreenViewport())

    init{
        Gdx.input.inputProcessor = buttonsy
        val back = TextButton("Menu",game.skin)
        back.setSize(Gdx.graphics.width/2f, Gdx.graphics.height/10f)
        back.setPosition(Gdx.graphics.height*(1f/15f), Gdx.graphics.height*(1f/15f))
        back.onClick { game.screen = Menu(game,sensor) }
        buttonsy.addActor(back)
        val reset = TextButton("Restart",game.skin)
        reset.setSize(Gdx.graphics.width/2f, Gdx.graphics.height/10f)
        reset.setPosition(Gdx.graphics.height*(1f/15f), Gdx.graphics.height*(3f/15f))
        world.setContactListener(Contact())
        map = HexMap(world,(Gdx.graphics.width-sizey)/2f,(Gdx.graphics.height)/2f,sizex,numOfHexes)
        val player = Player(world,0f,0f)
        val frame = Box(world,sizex,sizey)
        reset.onClick {
            map.NewGame()
            stage.clear()
            map.hexActors.forEach { it.forEach { stage.addActor(it) } }
            stage.addActor(player)
            stage.addActor(frame)
        }
        buttonsy.addActor(reset)
        map.hexActors.forEach { it.forEach { stage.addActor(it) } }
//        val ratio = Gdx.graphics.width.toFloat() / Gdx.graphics.height.toFloat()
        /*stage.camera.position.set(0f,0f,0f)
        stage.camera.lookAt(0f,0f,0f)
        stage.camera.viewportWidth = 10f
        stage.camera.viewportHeight = 10f/ratio*/
        stage.addActor(player)
        stage.addActor(frame)

    }
    override fun hide() {
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        myColors.bckgrnd()
        sr.begin(ShapeRenderer.ShapeType.Filled)
        sr.color = Color.RED
        sr.rect((Gdx.graphics.width-sizey)/2f,(Gdx.graphics.height)/2f-20f,sizey,10f)
        sr.rect((Gdx.graphics.width-sizey)/2f,(Gdx.graphics.height)/2f+sizex+20f,sizey,10f)
        sr.end()
        sr.begin(ShapeRenderer.ShapeType.Filled)
        sr.color = Color.BLUE
        sr.rect((Gdx.graphics.width-sizey)/2f-20f,(Gdx.graphics.height)/2f,10f,sizex)
        sr.rect((Gdx.graphics.width+sizey)/2f+40f,(Gdx.graphics.height)/2f,10f,sizex)
        sr.end()
        /*sr.begin(ShapeRenderer.ShapeType.Filled)
        sr.color = map.color
        sr.circle(Gdx.graphics.height*(2f/15f),Gdx.graphics.height*(13f/15f),Gdx.graphics.height/15f)
        sr.end()*/
        stage.act()
        stage.draw()
        buttonsy.act()
        buttonsy.draw()
        world.gravity = sensor.read().scl(5f)
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
        sr.dispose()
    }
}