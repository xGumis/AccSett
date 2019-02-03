package com.mygdx.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Actor

class Frame(world:World, width:Float, height:Float) : Actor() {
    private val sr = ShapeRenderer()
    init{
        this.width = width
        this.height = height
        val bdUP = BodyDef()
        val bdDOWN = BodyDef()
        val bdLEFT = BodyDef()
        val bdRIGHT = BodyDef()
        bdUP.position.set(0f, height/2f)
        bdDOWN.position.set(0f, -height/2f)
        bdLEFT.position.set(-width/2f, 0f)
        bdRIGHT.position.set(width/2f, 0f)
        bdUP.type = BodyDef.BodyType.StaticBody
        bdDOWN.type = BodyDef.BodyType.StaticBody
        bdLEFT.type = BodyDef.BodyType.StaticBody
        bdRIGHT.type = BodyDef.BodyType.StaticBody
        val bodyUP = world.createBody(bdUP)
        val bodyDOWN = world.createBody(bdDOWN)
        val bodyLEFT = world.createBody(bdLEFT)
        val bodyRIGHT = world.createBody(bdRIGHT)
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
        sr.begin(ShapeRenderer.ShapeType.Line)
        sr.color = Color.BLUE
        sr.rect(-width/2,-height/2,width,height)
        sr.end()
        batch?.begin()
    }
}