import java_cup.runtime.*;
import java.io.*;

class Main {
    public static void main(String[] argv) throws Exception{
        System.out.println("Input please!");
        Parser p = new Parser(new Scanner(new InputStreamReader(System.in)));
        p.parse();
    }

}

