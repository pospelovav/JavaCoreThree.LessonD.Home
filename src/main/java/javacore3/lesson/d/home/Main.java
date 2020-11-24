package javacore3.lesson.d.home;
/*
1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
2. Создать модель MFU (c возможность сканирования, печати и ксерокопии)
 */


public class Main {
    public static void main (String[] args) {
//Задание 1
        ABCthread abcThread = new ABCthread();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    abcThread.printA();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    abcThread.printB();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    abcThread.printC();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();

//Задание 2
    }
}
