package app;

public class SystemOut {
    public static void print(Object o, boolean error) {
        if (!error) {
            System.out.print(o);
        } else {
            System.err.print(o);
        }
    }
}
