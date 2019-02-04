package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Actor
import kotlin.math.sqrt

class Box(val world:World, width:Float, height:Float) : Actor()  {
    private val bodyUP : Body
    private val bodyDOWN : Body
    private val bodyLEFT : Body
    private val bodyRIGHT : Body
    private val sizex = width
    private val sizey = height
    private val sr = ShapeRenderer()
    init{
        this.width = width
        this.height = height
        val bdUP = BodyDef()
        val bdDOWN = BodyDef()
        val bdLEFT = BodyDef()
        val bdRIGHT = BodyDef()
        bdUP.position.set((Gdx.graphics.width)/2f,(Gdx.graphics.height)/2f-10f)
        bdDOWN.position.set((Gdx.graphics.width)/2f,(Gdx.graphics.height)/2f+sizex+20f)
        bdLEFT.position.set((Gdx.graphics.width-sizey)/2f-10f,(Gdx.graphics.height)/2f)
        bdRIGHT.position.set((Gdx.graphics.width+sizey)/2f+40f,(Gdx.graphics.height)/2f)
        bdUP.type = BodyDef.BodyType.StaticBody
        bdDOWN.type = BodyDef.BodyType.StaticBody
        bdLEFT.type = BodyDef.BodyType.StaticBody
        bdRIGHT.type = BodyDef.BodyType.StaticBody
        bodyUP = world.createBody(bdUP)
        bodyDOWN = world.createBody(bdDOWN)
        bodyLEFT = world.createBody(bdLEFT)
        bodyRIGHT = world.createBody(bdRIGHT)
        val groundBox = PolygonShape()
        val groundBoxSide = PolygonShape()
        groundBox.setAsBox(width, 0.0001f)
        groundBoxSide.setAsBox(0.0001f,height)
        bodyUP.createFixture(groundBox, 1.0f)
        bodyDOWN.createFixture(groundBox, 1.0f)
        bodyLEFT.createFixture(groundBoxSide, 1.0f)
        bodyRIGHT.createFixture(groundBoxSide, 1.0f)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.end()
        sr.projectionMatrix = batch?.projectionMatrix
        sr.transformMatrix = batch?.transformMatrix
        sr.translate(x,y,0f)
        batch?.begin()
    }
}