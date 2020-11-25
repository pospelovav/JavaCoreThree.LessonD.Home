package javacore3.lesson.d.home;
/*
2. Создать модель MFU (c возможность сканирования, печати и ксерокопии)
 */
public class MFU {
    public static boolean printStatus = true;       //костыли для корректной работы с при копировании
    public static boolean scanStatus = true;

    public static void main (String[] args){
//Задание 2
        final Object printMon = new Object();
        final Object scanMon = new Object();

        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (printMon) {
                    try {
                        if (!printStatus) {
                            printMon.wait();
                        }
                        printStatus = false;
                        System.out.println("Print");
                        Thread.sleep(1000);
                        printStatus = true;
                        printMon.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread scanThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (scanMon) {
                    try {
                        if (!scanStatus){
                            scanMon.wait();
                        }
                        scanStatus = false;
                        System.out.println("Scan");
                        Thread.sleep(1000);
                        scanStatus = true;
                        scanMon.notify();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread copyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (scanMon) {

                    synchronized (printMon) {
                        try {
                            if (!scanStatus) {
                                scanMon.wait();
                            }
                            scanStatus = false;
                            if (!printStatus) {
                                printMon.wait();
                            }
                            printStatus = false;
                            System.out.println("CopyScan");
                            Thread.sleep(1000);
                            System.out.println("CopyPrint");
                            Thread.sleep(1000);

                            printStatus = true;
                            scanStatus = true;
                            printMon.notify();
                            scanMon.notify();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        //Если идет копирование - сканировать или печатать нельзя
        //Сканирование и печать могут выполняться одновременно
        //Здесь пробывал запускать потоки с разной очередностью
        copyThread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printThread.start();

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        scanThread.start();
    }

}
