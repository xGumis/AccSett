package com.mygdx.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold

class Contact : ContactListener {
    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
    }

    override fun beginContact(contact: Contact) {
        val classA = contact.fixtureA.body.userData
        val classB = contact.fixtureB.body.userData

        if(classB is Player && classA is HexActor){
            when {
                classA.color==myColors.rand1 -> classA.color=myColors.rand2
                classA.color==myColors.rand2 -> classA.color=myColors.rand3
                classA.color==myColors.rand3 -> classA.color=myColors.rand1
            }
            classA.jakakolwiekFunkcja()

        }

        if(classA is Player && classB is HexActor){
            when {
                classB.color==myColors.rand1 -> classB.color=myColors.rand2
                classB.color==myColors.rand2 -> classB.color=myColors.rand3
                classB.color==myColors.rand3 -> classB.color=myColors.rand1
            }
            classB.jakakolwiekFunkcja()

        }
    }

    override fun endContact(contact: Contact?) {
    }
}