package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image


class Player(val world: World, pos_x: Float, pos_y: Float) : Image(Texture("skin/lenny.png")) {

    private var body: Body
    private var delete: Boolean = false
    init{
        this.setSize(this.width, this.height)
        this.setPosition(Gdx.graphics.width/2f, Gdx.graphics.height/2f)
        val bd = BodyDef()
        bd.position.set(this.x, this.y)
        bd.type = BodyDef.BodyType.DynamicBody
        body = world.createBody(bd)
        val rect = PolygonShape()
        rect.setAsBox(this.width/2,this.height/2)


        val fd = FixtureDef()
        fd.density = 50f
        fd.friction = 0.5f
        fd.restitution = 0.3f
        fd.shape = rect
        body.createFixture(fd)
        body.userData = this

        this.setOrigin(this.width / 2, this.height / 2)
        rect.dispose()
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