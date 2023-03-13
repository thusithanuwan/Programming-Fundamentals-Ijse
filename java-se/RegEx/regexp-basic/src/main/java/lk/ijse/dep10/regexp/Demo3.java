package lk.ijse.dep10.regexp;

public class Demo3 {
    public static void main(String[] args) {
        String bod = "1995-07-18";
        System.out.println(isValidBod(bod));
    }

    private static boolean isValidBod(String bod) {
//        int j = bod.indexOf('-');
//        int i = bod.lastIndexOf('-');
//
//        if (!(j > 0 && i > 0)) return false;
//
//        String substring = bod.substring(0, j);
//        String substring1 = bod.substring(5, i);
//        String substring2 = bod.substring(8);
//
//        if (Integer.parseInt(substring) < 1900) return false;
//        if (Integer.parseInt(substring1) > 12 || substring1.length() < 2) return false;
//        if (Integer.parseInt(substring2) > 31 || substring1.length() < 2) return false;
//
//        return true;
        return bod.matches("(19\\d{2}|2\\d{3}) - (0[1-9]|1[0-2]) - (0[1-9]|[1-2]|\\d[3[0-1]])");

    }
}
