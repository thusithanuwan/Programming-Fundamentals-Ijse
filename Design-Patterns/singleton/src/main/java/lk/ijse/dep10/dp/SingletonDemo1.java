package lk.ijse.dep10.dp;

public class SingletonDemo1 {
    public static void main(String[] args) {

        Mars mars = Mars.getInstance();
        Mars mars1 = Mars.getInstance();
        Mars mars2 = Mars.getInstance();
        System.out.println(mars1);
        System.out.println(mars2);

    }
}

class Mars {   // Eugar
    private static final Mars mars =  new Mars();  // step 1: make private final static reference to store memory location of the Instance

    private Mars(){}    // step 2: constructor must be private

    public static Mars getInstance(){     // step 3: there is a method to return the Instance
        return mars;
    }
}
