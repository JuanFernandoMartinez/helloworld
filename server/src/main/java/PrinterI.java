
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PrinterI implements Demo.Printer {

    private final int MAX_THREADS = Runtime.getRuntime().availableProcessors();

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREADS);
    CompletableFuture<Object> completableFuture;

    public String printString(String s, com.zeroc.Ice.Current current) {
        completableFuture = new CompletableFuture<>();
        try {
            String prt[] = s.split(" ");
            String hn = prt[0];
            String num = prt[1];
            if (num.matches("[-]{0,1}[0-9]+")) {
                long n = Long.parseLong(num);
                if (n > 0) {
                    System.out.print(hn + ": ");
                    executor.submit(()->{
                            String result = fibonacci(n);
                            completableFuture.complete(result);
                    });
                } else System.out.println(s);
            } else System.out.println(s);
            System.out.println(completableFuture.get());
            return completableFuture.get().toString();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String fibonacci(long nthNumber) {
        BigInteger previouspreviousNumber, previousNumber = BigInteger.valueOf(0), currentNumber = BigInteger.valueOf(1);
        for (int i = 1;   i < nthNumber ; i++) {
            previouspreviousNumber =  previousNumber ;
            previousNumber =  currentNumber ;
            currentNumber = previouspreviousNumber.add( previousNumber);
            System.out.print(currentNumber.toString()+" ");
        }
        return currentNumber.toString();
    }

}