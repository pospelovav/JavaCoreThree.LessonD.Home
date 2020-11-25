package javacore3.lesson.d.home;
/*
1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 */

public class ABCthread {
    private static char printCharacter = 'A'; //для гарантии очередности букв

    public static void main (String[] args) {
//Задание 1
        Object monitor = new Object();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while (printCharacter != 'A') {
                               monitor.wait();
                            }
                            System.out.print("A");
                            printCharacter = 'B';
                            monitor.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while (printCharacter != 'B') {
                                 monitor.wait();
                            }
                            System.out.print("B");
                            printCharacter = 'C';
                            monitor.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while (printCharacter != 'C') {
                                monitor.wait();
                            }
                            System.out.print("C");
                            printCharacter = 'A';
                            monitor.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
