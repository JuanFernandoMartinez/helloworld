public class PrinterI implements Demo.Printer
{
    public long printString(String s, com.zeroc.Ice.Current current)
    {
        String prt[] = s.split(" ");
        String hn = prt[0];
        String num = prt[1];
        if (num.matches("[-]{0,1}[0-9]+")){
            long n = Long.parseLong(num);
            if (n>0) {
                System.out.print(hn+": ");
                return fibonacci(n);
            }
            else System.out.println(s);
        }else System.out.println(s);

        return 0;
        
    }

    public long fibonacci(long nthNumber) {
        //use loop


        int previouspreviousNumber, previousNumber = 0, currentNumber = 1;

        for (int i = 1; i < nthNumber ; i++) {

            previouspreviousNumber = previousNumber;

            previousNumber = currentNumber;

            currentNumber = previouspreviousNumber + previousNumber;
            System.out.print(currentNumber+" ");

        }
        return currentNumber;
    }

}