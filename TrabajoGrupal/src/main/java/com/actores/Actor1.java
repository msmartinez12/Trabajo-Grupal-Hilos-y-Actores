package com.actores;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Actor1 extends AbstractBehavior<Actor1.Command> {

    interface Command {}

    public enum SayHello implements Command{
        INSTANCE
    }

    public static class ChangeMessage implements Command{
        public final String newMessage;

        public ChangeMessage(String newMessage) {
            this.newMessage = newMessage;
        }
    }

    public static Behavior<Command> create(){
        return Behaviors.setup(context -> new Actor1(context));
    }

    private String message = "Hola mundo";

    private Actor1(ActorContext<Command> context){
        super(context);
    }
    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(SayHello.INSTANCE, this::onSayHello)
                .onMessage(ChangeMessage.class, this::onChangeMessage)
                .build();
    }

    private Behavior<Command> onChangeMessage(ChangeMessage command) {
        message = command.newMessage;
        return this;
    }

    private Behavior<Command> onSayHello() {
        System.out.println(message);
        return this;
    }
}
