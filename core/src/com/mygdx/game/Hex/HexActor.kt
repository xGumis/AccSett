package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Colors
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.EarClippingTriangulator
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ShortArray
import javax.swing.event.ChangeEvent
import kotlin.math.sqrt




class HexActor(world: World,originPoint: Vector2,h: Float): Actor() {

    val vertices : FloatArray
    private var body: Body

    /*
    val playerVertices : FloatArray
*/
    val indices : ShortArray
//    val playerIndices : ShortArray
    val sr = ShapeRenderer()

    init {
        var rand = random(1,3)
        if(rand == 1) color = myColors.rand1
        if(rand == 2) color = myColors.rand2
        if(rand == 3) color = myColors.rand3

        x = originPoint.x
        y = originPoint.y
        height = h
        vertices = floatArrayOf(
                x,y,
                x,y+(height/2),
                x+(height* sqrt(3f)/4),y+(height*(3f/4f)),
                x+(height* sqrt(3f)/2),y+(height/2),
                x+(height* sqrt(3f)/2),y,
                x+(height* sqrt(3f)/4),y-(height/4)
        )
        /*playerVertices = floatArrayOf(
                Gdx.graphics.width.toFloat()/2,Gdx.graphics.height.toFloat()/2,
                Gdx.graphics.width.toFloat()/2+(height/8),Gdx.graphics.height.toFloat()/2+(height/4),
                Gdx.graphics.width.toFloat()/2+(height/4),Gdx.graphics.height.toFloat()/2
        )*/
        val ear = EarClippingTriangulator()
        indices = ear.computeTriangles(vertices)
        /*playerIndices = ear.computeTriangles(playerVertices)*/

        val bd = BodyDef()
        bd.position.set(x, y)
        bd.type = BodyDef.BodyType.StaticBody
        body = world.createBody(bd)
        val hexagon = PolygonShape()
        val asd = arrayOf(
                Vector2(0f,0f),
                Vector2(0f,(height/2f)),
                Vector2((height* sqrt(3f)/4f),(height*(3f/4f))),
                Vector2((height* sqrt(3f)/2f),(height/2f)),
                Vector2((height* sqrt(3f)/2f),0f),
                Vector2((height* sqrt(3f)/4f),-(height/4f))
        )
        hexagon.set(asd)

        val fd = FixtureDef()
        fd.shape = hexagon
        fd.isSensor = true
        body.createFixture(fd)
        body.userData = this

    }
    fun jakakolwiekFunkcja(){
        val event = InputEvent()
        event.relatedActor = this
        event.type = InputEvent.Type.touchDown
        this.fire(event)
        event.type = InputEvent.Type.touchUp
        this.fire(event)
    }
    /*override fun hit(x: Float, y: Float, touchable: Boolean): Actor? {
        if(touchable&&getTouchable() == Touchable.enabled){
            val a = height/2
            if(
                    (
                            (a* sqrt(3f)/2>x && x>0)&&
                                    (y< sqrt(3f)*x/3+a && y>sqrt(3f)*x/(-3))
                            )||
                    (
                            ((x>a* sqrt(3f)/2 && x<a* sqrt(3f))&&
                                    (y<sqrt(3f)*x/(-3)+2*a && y>sqrt(3f)*x/3-a)
                            )
                    ))return this
        }
        return null
    }*/

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch?.end()
        sr.begin(ShapeRenderer.ShapeType.Filled)
        sr.color = color
        var i = 0
        while (i < indices.size - 2) {
            val x1 = vertices[indices.get(i) * 2]
            val y1 = vertices[indices.get(i) * 2 + 1]

            val x2 = vertices[indices.get(i + 1) * 2]
            val y2 = vertices[indices.get(i + 1) * 2 + 1]

            val x3 = vertices[indices.get(i + 2) * 2]
            val y3 = vertices[indices.get(i + 2) * 2 + 1]

            sr.triangle(x1, y1, x2, y2, x3, y3)
            i += 3
        }
        sr.end()
        sr.begin(ShapeRenderer.ShapeType.Line)
        sr.color = Color.GREEN
        sr.polygon(vertices)
        sr.end()
        /*sr.begin(ShapeRenderer.ShapeType.Filled)
        sr.color = Color.GOLD
        sr.triangle(playerVertices[0],playerVertices[1],playerVertices[2],playerVertices[3],playerVertices[4],playerVertices[5])
        sr.end()*/
        batch?.begin()
    }

    fun isOccupied() : Boolean{
        return color != myColors.empty
    }
    fun Clear(){
        color = myColors.empty
    }
}