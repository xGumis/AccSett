package com.mygdx.game.Labyrinth

import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold
import com.mygdx.game.Ball

class CollListener : ContactListener {
    override fun endContact(contact: Contact?) {
    }

    override fun beginContact(contact: Contact?) {
        val classA = contact?.fixtureA?.body?.userData
        val classB = contact?.fixtureB?.body?.userData
        if(classA == "End" && classB is Ball){
            classB.eliminate()
        }else if(classB == "End" && classA is Ball){
            classA.eliminate()
        }
    }

    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
    }

}