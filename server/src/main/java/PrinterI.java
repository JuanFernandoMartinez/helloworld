
import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PrinterI implements Demo.Printer
{
    public String printString(String s, com.zeroc.Ice.Current current)
    {
        String prt[] = s.split(" ");
        String hn = prt[0];
        String num = prt[1];
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
        if (num.matches("[-]{0,1}[0-9]+")){
            long n = Long.parseLong(num);
            if (n>0) {
                executor.submit(()->{
                System.out.print(hn+": ");
                return fibonacci(n);
            });
            }else System.out.println(s);
        }else System.out.println(s);
        return "0";
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