package src;
import src.listas.*;
import src.baseLista.*;
public class Main {
    public static void main(String[] args) {
        try {
            Runner.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}