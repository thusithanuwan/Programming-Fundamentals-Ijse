package lk.ijse.dep10.oop.types;

public class Demo {
    int x = 20; // Property,Field,State,Attribute

    public static void main(String[] args) {
        TopLevelClass topLevelClass = new TopLevelClass();
        AnotherClass anotherClass = new AnotherClass();
        new TopLevelClass.StaticNestedClass();

        new TopLevelClass().new RegularInnerClass();

        int x = 10; // Local Variable


        class LocalInnerClass{}

        new LocalInnerClass();

    }
}

class TopLevelClass {
    class RegularInnerClass {
        int x =10;
        class RegularInnerClass1{  // can make field,above the class so this is a RegularClass
            {
                int x =10;
                class LocalInnerClass{}      // can make local variable above the class so this is a LocalInnerClass
            }
        }
        class RegularInnerClass2{}
    }

    static class StaticNestedClass {
    }
}

class AnotherClass {   // Top level class.
}

