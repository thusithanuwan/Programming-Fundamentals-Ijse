package lk.ijse.dep10.regexp;

public class Demo1 {
    public static void main(String[] args) {
        String nic = "971234560V";
        System.out.println(isValidNic(nic));
        if (isValidNic(nic)) {
            System.out.println("Valid NIC");
        } else
            System.out.println("Invalid NIC");



    }
    private static boolean isValidNic(String nic){
//        String sub1 = nic.substring(0,9);
//        String sub2 = nic.substring(9);
//
//
//        boolean flag = true;
//
//        char[] charArray = sub1.toCharArray();
//        for (char c : charArray) {
//            if (!Character.isDigit(c)){
//                flag = false;
//            }
//        }
//
//        if (flag && (sub2.toUpperCase()).equals('V')) {
//            return true;
//        }
//        return false;
        return nic.matches("\\d{9}['V']");
    }
}
