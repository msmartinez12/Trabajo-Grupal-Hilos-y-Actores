package com.actores;

import akka.actor.typed.ActorSystem;

public class HelloWorldMain {
    public static void main(String[] args) throws InterruptedException {
        ActorSystem<Actor1.Command> mySystem = ActorSystem.create(Actor1.create(), "MySystem");
        ActorSystem<Actor2.Command> mySystem2 = ActorSystem.create(Actor2.create(), "MySystem");

        for (int i = 0; i < 3; i++) {
            if (i == 2)
                mySystem.tell(new Actor1.ChangeMessage("Hola Pablo"));
            mySystem.tell(Actor1.SayHello.INSTANCE);
        }

        for (int i = 0; i < 3; i++) {
            if (i == 2)
                mySystem2.tell(new Actor2.ChangeMessage("Hola Mateo"));
            mySystem2.tell(Actor2.SayHello.INSTANCE);
        }
    }
}
