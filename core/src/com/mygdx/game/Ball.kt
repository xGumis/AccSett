package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image


class Ball(val world: World, pos_x: Float, pos_y: Float,size:Float) : Image(Texture("greenball.png")) {

    private var body: Body
    var delete: Boolean = false
    private val startingPosition = Vector2(pos_x,pos_y)
    private val sr = ShapeRenderer()
    private val size = size
    init{
        this.setSize(size, size)
        this.setPosition(pos_x, pos_y)
        val bd = BodyDef()
        bd.position.set(this.x, this.y)
        bd.type = BodyDef.BodyType.DynamicBody
        body = world.createBody(bd)
        val circle = CircleShape()
        circle.radius = this.width / 2

        val fd = FixtureDef()
        fd.density = 50f
        fd.friction = 0.5f
        fd.restitution = 0.3f
        fd.shape = circle
        body.createFixture(fd)
        body.userData = this

        this.setOrigin(this.width / 2, this.height / 2)
        circle.dispose()
    }

    override fun act(delta: Float) {
        super.act(delta)
        if (delete) {
            world.destroyBody(body)
            this.remove()
        }
        this.rotation = body.angle * MathUtils.radiansToDegrees
        this.setPosition(body.position.x - this.width / 2, body.position.y - this.height / 2)
    }

    fun reset(){
        this.setPosition(startingPosition.x,startingPosition.y)
    }

    fun eliminate() {
        delete = true
    }
}