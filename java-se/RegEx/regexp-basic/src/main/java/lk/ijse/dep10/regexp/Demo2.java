package lk.ijse.dep10.regexp;

public class Demo2 {
    public static void main(String[] args) {
        String contact = "+9477-5142484";
        System.out.println(isValidContact(contact));
    }

    private static boolean isValidContact(String contact) {


//        if (!(contact.length() == 11 || contact.length() ==13)) {
//            return false;
//        }
//        if (contact.length() == 11) {
//            String substring = contact.substring(0, 3);
//            String substring1 = contact.substring(3, 4);
//            String substring2 = contact.substring(4);
//
//            for (char c : substring.toCharArray()) {
//                if (!Character.isDigit(c)) {
//                    return false;
//                }
//
//            }
//            for (char c : substring2.toCharArray()) {
//                if (!Character.isDigit(c)) {
//                    return false;
//
//                }
//            }
//            if (substring1.equals('-')) {
//                return false;
//            }
//        }
//        return true;
        return contact.matches("([+](94)|0)\\d{2}[-]\\d{7}");
    }
}
