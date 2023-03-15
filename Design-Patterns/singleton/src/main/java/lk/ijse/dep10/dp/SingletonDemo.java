package lk.ijse.dep10.dp;

public class SingletonDemo {
    public static void main(String[] args) {

        Mars earth1 = Mars.getInstance();
        Mars earth2 = Mars.getInstance();

    }
}

class Earth { // Lazy
    private static Earth earth;  // step 1: make private static reference to store memory location of the Instance

    private Earth() {
    }    // step 2: constructor must be private

    public static Earth getInstance() {     // step 3: there is a method to return the Instance
//        if(earth == null){                    // In industry we use ternary operator
//            earth = new Earth();
//        }
//        return earth;
        return (earth == null) ? new Earth() : earth;
    }
}
