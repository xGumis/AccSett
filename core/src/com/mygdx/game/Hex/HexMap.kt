package com.mygdx.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import ktx.actors.onChange
import ktx.actors.onClick
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.sqrt
import kotlin.system.exitProcess

class HexMap(world: World,x:Float,y:Float,height:Float,val num:Int) {
    lateinit var world : World


    var color = myColors.rand1
    var hexActors : ArrayList<ArrayList<HexActor>>
    init {
        val h = (height-((height/num)*(1f/4f)))/num*(4f/3f)
        val w = h*sqrt(3f)/2
        var X : Float
        var Y = y+h/4f
        hexActors = ArrayList()
        for(i in 1..num){
            val tmp = ArrayList<HexActor>()
            for(j in 1..num){
                X = (j-1)*w + if(i%2 == 0){w/2}else{0f}
                val hex = HexActor(world,Vector2(x+X,Y),h)
                hex.onClick {
                    finish() }

                tmp.add(hex)
            }
            Y += (3f/4f)*h
            hexActors.add(tmp)
        }
    }


    fun finish(){
        var red = 0
        var blue = 0
        var green = 0

        for(i in 0..8){
            for (j in 0..8){
                when{
                    hexActors[i][j].color==myColors.rand1 -> red++
                    hexActors[i][j].color==myColors.rand2 -> blue++
                    hexActors[i][j].color==myColors.rand3 -> green++
                }
            }
        }

        if (green>=50) {hexActors.forEach { it.forEach { it.color= Color.GOLD } }}
    }

    fun NewGame(){
        color = myColors.rand1
        hexActors.forEach{ it.forEach { it.Clear() }}
    }
}