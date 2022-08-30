public class PrinterI implements Demo.Printer
{
    public void printString(String s, com.zeroc.Ice.Current current)
    {
        if (s.matches("[-]{0,1}[0-9]+")){
            Long n = Long.parseLong(s);
            if (n>0) System.out.println(fibonacci(n));
            else System.out.println(s);
        }else System.out.println(s);
        
    }

    public Long fibonacci(long n){
        if (n <= 1) return n;
        else{
            return fibonacci(n-1)+fibonacci(n-2);
        }
    }

}