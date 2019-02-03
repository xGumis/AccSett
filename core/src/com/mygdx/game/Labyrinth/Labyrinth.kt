package com.mygdx.game.Labyrinth

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.codeandweb.physicseditor.PhysicsShapeCache

class Labyrinth(world: World, pos_x:Float, pos_y:Float, size:Float) : Image(Texture("mapa.png")) {
    init{
        width = size
        height = size
        val scale = size/800f
        this.setPosition(pos_x,pos_y)
        val physBod = PhysicsShapeCache("map.xml")
        val body = physBod.createBody("mapa",world,scale,scale)
        body.setTransform(x, y,rotation)
        val end = physBod.createBody("end",world,scale,scale)
        end.setTransform(x,y,rotation)
        end.userData = "End"
    }


}