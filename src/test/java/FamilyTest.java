import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class FamilyTest {
    Human mother = new Human("Arzu","Ismayilova","21/04/1975");
    Human father = new Human("Senan","Ismayilov","12/12/1975");
    Family family = new Family(mother, father);
    Dog dog = new Dog("Danny",6,55,new LinkedHashSet<>(Arrays.asList("eat", "run", "bite")));
    Human child = new Human("Elnur","Ismayilov","12/02/2000",94,null);
    Human child1 = new Human("Emil","Ismayilov","11/12/1996",92,null);
    Human child2 = new Human("Elvin","Ismayilov","06/06/1995",92, null);

    @Test
    void bornChildTest(){
        Human child3 = family.bornChild();
        Assertions.assertTrue(family.getChildren().contains(child3));
    }

    @Test
    void bornChildRelationshipTest(){
        Human child3 = family.bornChild();
        Assertions.assertEquals(child3.getFamily().getFather() , family.getFather());
    }

    @Test
    void testToString() {
        family.setPets(new HashSet<>(Collections.singletonList(dog)));
        family.addChild(child);

        Assertions.assertEquals("Family{mother=Human{name='Arzu', surname='Ismayilova', birth date='21/04/1975', iq=0, schedule=null}," +
                " father=Human{name='Senan', surname='Ismayilov', birth date='12/12/1975', iq=0, schedule=null}," +
                " pet=[DOG{nickname='Danny', age=6, trickLevel=55, habits=[eat, run, bite], canFly=false, numberOfLegs=4, hasFur=true}]," +
                " children=[Human{name='Elnur', surname='Ismayilov', birth date='12/02/2000', iq=94, schedule=null}]}", family.toString());
    }

    @Test
    void addChildLengthTest() {
        family.addChild(child);
        family.addChild(child1);

        Assertions.assertEquals(2, family.getChildren().size());
    }

    @Test
    void addChildRelationshipTest(){
        family.addChild(child);
        family.addChild(child1);

        Assertions.assertEquals(mother, child1.getFamily().getMother());
    }

    @Test
    void deleteChildWithIndexPositiveCase() {
        family.addChild(child);
        family.addChild(child1);
        family.addChild(child2);
        Human deletedOne = family.getChildren().get(1);

        boolean condition = family.deleteChild(1);
        boolean condition2 = family.getChildren().contains(deletedOne);

        Assertions.assertTrue(condition);
        Assertions.assertFalse(condition2);
    }

    @Test
    void deleteChildWithIndexNegativeCase(){
        family.addChild(child);
        family.addChild(child1);
        family.addChild(child2);
        List<Human> test = family.getChildren();

        boolean condition = family.deleteChild(100);

        Assertions.assertEquals(test, family.getChildren());
        Assertions.assertFalse(condition);
    }

    @Test
    void DeleteChildPositiveCase() {
        family.addChild(child);
        family.addChild(child1);
        family.addChild(child2);
        family.deleteChild(child1);

        boolean condition = family.getChildren().contains(child1);

        Assertions.assertFalse(condition);
    }

    @Test
    void DeleteChildNegativeCase(){
        family.addChild(child);
        family.addChild(child1);
        family.addChild(child2);
        List<Human> test = family.getChildren();
        family.deleteChild(mother);

        Assertions.assertEquals(test, family.getChildren());
    }

    @Test
    void countFamily() {
        family.addChild(child);
        family.addChild(child1);
        Assertions.assertEquals(4, family.countFamily());
    }

}