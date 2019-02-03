package com.mygdx.game

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.*


class Ball(val world: World, pos_x: Float, pos_y: Float) : Image(Texture("bubble.png")) {

    private var body: Body
    private var delete: Boolean = false
    init{
        this.setSize(1f, 1f)
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
        body.setUserData(this)

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

    fun eliminate() {
        delete = true
    }
}