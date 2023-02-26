package lk.ijse.dep10.se;

import java.math.BigDecimal;

public class BigDecimalDemo {
    public static void main(String[] args) {
        double d1 = 0.3;
        double d2 = 0.2;
        double result = d1- d2;
        System.out.println(result);

        BigDecimal bigDecimal2 = new BigDecimal(0.3);
        BigDecimal bigDecimal3 = new BigDecimal(0.1);
        System.out.println(bigDecimal2.subtract(bigDecimal3));

        BigDecimal bigDecimal = new BigDecimal("0.3");
        BigDecimal bigDecimal1 = new BigDecimal("0.2");
        BigDecimal resul = bigDecimal.subtract(bigDecimal1);
        System.out.println(resul);

        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        BigDecimal subtract = bd1.subtract(bd2);
        System.out.println(subtract);

    }
}
