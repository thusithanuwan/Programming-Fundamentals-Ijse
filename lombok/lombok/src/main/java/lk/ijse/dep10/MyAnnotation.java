package lk.ijse.dep10;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // @Target is used to specify where this annotation can use

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

//    public abstract void    print();
    public abstract String name();
    public abstract int getNumber() default 10;

}
