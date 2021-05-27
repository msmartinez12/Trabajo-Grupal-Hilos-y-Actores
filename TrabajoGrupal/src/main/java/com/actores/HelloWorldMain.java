package com.actores;

import akka.actor.typed.ActorSystem;

public class HelloWorldMain {
    public static void main(String[] args) {
        ActorSystem<Actor1.Comando> mySystem = ActorSystem.create(Actor1.create(), "MySystem");
        ActorSystem<Actor2.Comando> mySystem2 = ActorSystem.create(Actor2.create(), "MySystem2");

        for (int i = 0; i < 3; i++) {
            if (i == 1)
                mySystem.tell(new Actor1.CambiarMensaje("Hola Pablo"));
            mySystem.tell(Actor1.Saludar.EJEMPLO);
        }

        for (int i = 0; i < 3; i++) {
            if (i == 2)
                mySystem2.tell(new Actor2.CambiarMensaje("Hola Mateo"));
            mySystem2.tell(Actor2.Saludar.EJEMPLO);
        }
    }
}
