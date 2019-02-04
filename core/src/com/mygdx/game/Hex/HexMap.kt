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
    var rowArray = MutableList(num){it}
    var occArr = Array(num){MutableList(num){it}}
    var chckArr = Array(num){Array(num){false}}
    var sidesOccupied = arrayOf(false,false)
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
                /*hex.onClick {
                    if(!hex.isOccupied()){
                        hex.color = color
                        clearArr()
                        occupy(i-1,j-1)
                        checkPath(i-1,j-1)
                        changePlayer()
                        if(mode==1 && color==myColors.player2) computr()
                    }
                }*/
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

        /*hexActors.forEach { it.forEach{
            when {
                it.color==myColors.rand1 -> red++
                it.color==myColors.rand2 -> blue++
                it.color==myColors.rand3 -> green++
            }
        } }*/
        if (green>=50) {hexActors.forEach { it.forEach { it.color= Color.GOLD } }}
    }
    //region Game functions
    /*private fun changePlayer(){
        if(color == myColors.player1) color = myColors.player2
        else if(color == myColors.player2) color = myColors.player1
    }
    private fun computr(){
        rowArray.shuffle()
        val i = rowArray.first()
        occArr[i].shuffle()
        val j = occArr[i].first()
        occupy(i,j)
        hexActors[i][j].color = color
        clearArr()
        checkPath(i,j)
        changePlayer()
    }
    private fun occupy(i: Int,j: Int){
        occArr[i].remove(j)
        if(occArr[i].isEmpty())rowArray.remove(i)
    }*/
    /*private fun clearArr(){
        chckArr = Array(num){Array(num){false}}
        sidesOccupied = arrayOf(false,false)
    }
    private fun askOther(i: Int,j: Int){
        if(j!=0)checkOther(i,j-1)
        if(j!=num-1)checkOther(i,j+1)
        if(i!=num-1)checkOther(i+1,j)
        if(i!=0)checkOther(i-1,j)
        when(i%2){
            0 -> {
                if(j!=0){
                    if(i!=num-1)checkOther(i+1,j-1)
                    if(i!=0)checkOther(i-1,j-1)
                }
            }
            1 ->{
                if(j!=num-1){
                    if(i!=num-1)checkOther(i+1,j+1)
                    if(i!=0)checkOther(i-1,j+1)
                }
            }
        }
    }
    private fun checkOther(i: Int, j: Int){
        if(hexActors[i][j].color==color)checkPath(i,j)
    }
    private fun checkPath(i: Int, j: Int){
        if(!chckArr[i][j]){
            chckArr[i][j] = true
            when(color){
                Color.BLUE -> {
                    if(j==0)sidesOccupied[0]=true
                    if(j==num-1)sidesOccupied[1]=true
                }
                Color.RED -> {
                    if(i==0)sidesOccupied[0]=true
                    if(i==num-1)sidesOccupied[1]=true
                }
            }
            if(sidesOccupied[0]&&sidesOccupied[1])color=myColors.finish
            askOther(i,j)
        }
    }
*/
    //endregion
    fun NewGame(){
        color = myColors.rand1
        hexActors.forEach{ it.forEach { it.Clear() }}
    }
}