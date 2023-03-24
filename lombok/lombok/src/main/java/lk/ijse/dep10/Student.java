package lk.ijse.dep10;

import lombok.*;

import java.io.Serializable;

@ToString(exclude = "id") // TO avoid id from the ToString method

@Data /* = Getter + Setter + NoArgConstructor + EqualsAndHashcode + ToString*/
//@Getter
//@Setter
//@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode
//@ToString




public class Student implements Serializable  {
    private int id;
    private String name;
//    @Setter(AccessLevel.NONE)           // To avoid setter for Contact
    private String contact;



}
