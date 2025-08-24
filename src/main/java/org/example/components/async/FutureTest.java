package org.example.components.async;

import java.util.Set;
import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<Void> task1 = () -> {
            System.out.println("start task 1 from executor.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("done task 1 from executor.");
            return null;
        };

        Callable<Void> task2 = () -> {
            System.out.println("start task 2 from executor.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("done task 2 from executor.");
            return null;
        };

//        Future<?> future1 = executorService.submit(task1);
//        Future<?> future2 = executorService.submit(task2);

        System.out.println("start run await");
        try {
            var result = executorService.invokeAny(Set.of(task1, task2));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("done run await");

        executorService.shutdown();

        System.out.println("done.");
    }
}
