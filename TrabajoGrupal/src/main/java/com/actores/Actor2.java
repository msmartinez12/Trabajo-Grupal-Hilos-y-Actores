package com.actores;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Actor2 extends AbstractBehavior<Actor2.Comando> {

    interface Comando {}

    private Actor2(ActorContext<Comando> context){
        super(context);
    }

    private String mensajeInicial = "Hola mundo2";

    public enum Saludar implements Comando {
        EJEMPLO
    }

    public static class CambiarMensaje implements Comando {
        public final String newMessage;

        public CambiarMensaje(String newMessage) {
            this.newMessage = newMessage;
        }
    }

    public static Behavior<Comando> create(){
        return Behaviors.setup(context -> new Actor2(context));
    }


    @Override
    public Receive<Comando> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(Saludar.EJEMPLO, this::alSaludar)
                .onMessage(CambiarMensaje.class, this::alCambiarMensaje)
                .build();
    }

    private Behavior<Comando> alCambiarMensaje(CambiarMensaje command) {
        mensajeInicial = command.newMessage;
        return this;
    }

    private Behavior<Comando> alSaludar() {
        System.out.println(mensajeInicial);
        return this;
    }
}

