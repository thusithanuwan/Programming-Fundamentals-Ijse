package lk.ijse.dep10.oop;

import java.util.ArrayList;

public class CompositionDemo {
    public static void main(String[] args) {
        Institute1 ijse = new Institute1();
        Programme1 programme1 = ijse.programList.get(0);
        System.out.println(programme1.name);

        ijse = null;
    }
}

class Institute {
    ArrayList<Programme1> programList = new ArrayList<>();
    public  Institute(){
        programList.add(new Programme1("DEP", "5 Months"));
        programList.add(new Programme1("CMJD", "6 Months"));
        programList.add(new Programme1("GDSE", "2 Years"));
    }
}
class Programme {
    String name;
    String duration;

    public Programme(String name, String duration) {
        this.name = name;
        this.duration = duration;
    }
}
