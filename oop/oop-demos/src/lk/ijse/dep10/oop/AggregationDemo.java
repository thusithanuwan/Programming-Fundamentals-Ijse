package lk.ijse.dep10.oop;

import java.util.ArrayList;

public class AggregationDemo {
    public static void main(String[] args) {
        ArrayList<Programme1> programList = new ArrayList<>();
        programList.add(new Programme1("DEP", "5 Months"));
        programList.add(new Programme1("CMJD", "6 Months"));
        programList.add(new Programme1("GDSE", "2 Years"));

        Institute1 ijse = new Institute1(programList);
        System.out.println(ijse.programmeList.get(0).name);

        ijse = null;

        //Still can access to programme, because has the reference to the programmeList
        System.out.println(programList.get(0).name);
    }
}

class Institute1 {
    ArrayList<Programme1> programmeList ;
    public Institute1(ArrayList<Programme1> programmeList){
    }
}
class Programme1 {
    String name;
    String duration;

    public Programme1(String name, String duration) {
        this.name = name;
        this.duration = duration;
    }
}
