package org.budgie.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MainClass {

    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        mainClass.doGeekStuff();
        System.out.println("Execute before or after? thread");
        mainClass.completableFutureTaskSupplier();
        System.out.println("Execute before or after? taskSupplier");
        mainClass.completableFutureTaskRunnable();
        System.out.println("Execute before or after? taskRunnable");
    }

    private OnGeekListener mListener;

    public void registerOnGeekEventListener(OnGeekListener mListener) {
        this.mListener = mListener;
    }

    // My Asynchronous task
    public void doGeekStuff() {
        // An Async task always executes in new thread
        new Thread(() -> System.out.println("My asynchronous task with Thread")).start();
    }

    public void completableFutureTaskSupplier() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("My asynchronous task in completable future with supplier");
            return new Random().nextInt();
        });
        if(completableFuture.isDone()){
            try {
                completableFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void completableFutureTaskRunnable(){
        CompletableFuture.runAsync(() -> System.out.println("My asynchronous task in completable future with runnable"));
    }
}
