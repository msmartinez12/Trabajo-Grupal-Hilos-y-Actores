package com.hilos;

public class HiloMain {
    public static void main(String[] args) throws InterruptedException {

        Hilo1 h1 = new Hilo1();
        Hilo2 h2 = new Hilo2();

        for (int i = 0; i < 3; i++) {
            Thread hilo = new Thread(h1, String.valueOf(i));
            hilo.start();
            if (i == 1)
                h1.CambiarMensaje("me cambie ;)");
            hilo.join();
        }

        for (int i = 0; i < 3; i++) {
            Thread hilo2 = new Thread(h2, String.valueOf(i));
            hilo2.start();
            if (i == 1) {
                h2.CambiarMensaje("me cambie ;)");
            }
            hilo2.join();
        }
    }
}
