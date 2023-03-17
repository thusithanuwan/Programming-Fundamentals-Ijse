package lk.ijse.dep10.dp;

import someone.Moon;

public class SingletonDemo2 {
    public static void main(String[] args) {
        Moon moon = MoonWrapper.getMoonWrapper().getMoon();
        Moon moon1 = MoonWrapper.getMoonWrapper().getMoon();
        System.out.println(moon);
        System.out.println(moon1);
    }

}

class MoonWrapper {
    private static final MoonWrapper moonWrapper = new MoonWrapper();
    private final Moon moon = new Moon();

    private MoonWrapper(){}

    public static MoonWrapper getMoonWrapper(){
        return moonWrapper;
    }
    public Moon getMoon(){
        return moon;
    }


}