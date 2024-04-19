import java.io.IOException;

class Main {
    public static void main(String[] args) {
        try {
            // while (true) {
                print((new Calculator(System.in)).Expr());
            // }
        } catch (IOException | ParseException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void print(String text){
        System.out.println(text);
    }
    
    public static void print(int text){
        System.out.println(text);
    }
}


