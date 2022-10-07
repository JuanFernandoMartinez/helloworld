import java.util.Scanner;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class Client
{
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        java.util.List<String> extraArgs = new java.util.ArrayList<>();
        
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"config.client",extraArgs))
        {
            //com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx twoway = Demo.PrinterPrx.checkedCast(
                communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            //Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Demo.PrinterPrx printer = twoway;

            if(printer == null)
            {
                throw new Error("Invalid proxy");
            }

            String hn = "unknown host adress: ";
            try{
                InetAddress addr;
                addr = InetAddress.getLocalHost();
                String hostname = addr.getHostName();
                hn = hostname+": ";
            }catch(UnknownHostException e){
                System.out.print("unknown host adress: ");
            }
            
            String out = "";
            while (!out.equals("exit")){
                System.out.print(hn);
               if(args.length > 0){
                System.out.println(printer.printString(hn+args[0]));
               }else{
                out = sc.nextLine();
                System.out.println(printer.printString(hn+out));
               }
               
                
            }
            
        }
    }
}