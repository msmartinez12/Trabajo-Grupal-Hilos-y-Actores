package com.actores;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Actor1 extends AbstractBehavior<Actor1.Comando> {

    interface Comando {}

    private Actor1(ActorContext<Comando> context){
        super(context);
    }

    private String mensajeInicial = "Hola mundo";


    public enum Saludar implements Comando {
        EJEMPLO
    }

    public static class CambiarMensaje implements Comando {
        public final String nuevoMensaje;

        public CambiarMensaje(String nuevoMensaje) {
            this.nuevoMensaje = nuevoMensaje;
        }
    }


    public static Behavior<Comando> create(){
        return Behaviors.setup(context -> new Actor1(context));
    }


    @Override
    public Receive<Comando> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(Saludar.EJEMPLO, this::alSaludar)
                .onMessage(CambiarMensaje.class, this::alCambiarMensaje)
                .build();
    }

    private Behavior<Comando> alSaludar() {
        System.out.println(mensajeInicial);
        return this;
    }

    private Behavior<Comando> alCambiarMensaje(CambiarMensaje command) {
        mensajeInicial = command.nuevoMensaje;
        return this;
    }
}