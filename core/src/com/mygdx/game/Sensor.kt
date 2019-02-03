package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2

class Sensor {
    var scale = 1f
    val available =  Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)
    fun read() : Vector2 {
        return Vector2(-Gdx.input.accelerometerX,-Gdx.input.accelerometerY).scl(scale)
    }
}