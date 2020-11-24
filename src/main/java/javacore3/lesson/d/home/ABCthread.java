package javacore3.lesson.d.home;

public class ABCthread {
    private char printCharacter = 'A'; //для гарантии очередности букв

    public synchronized void printA() {
        try {
            while (printCharacter != 'A') {
                wait();
            }
            System.out.print("A");
            printCharacter = 'B';
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void printB() {
        try {
            while (printCharacter != 'B') {
                wait();
            }
            System.out.print("B");
            printCharacter = 'C';
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void printC() {
        try {
            while (printCharacter != 'C') {
                wait();
            }
            System.out.print("C");
            printCharacter = 'A';
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
