import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

class FamilyServiceTest {
    Human mother1 = new Human("Arzu","Ismayilova","21/04/1975");
    Human father1 = new Human("Senan","Ismayilov","12/12/1975");
    Dog dog = new Dog("Danny",6,55,new LinkedHashSet<>(Arrays.asList("eat", "run", "bite")));
    DomesticCat cat = new DomesticCat("Garfield",10,95,new LinkedHashSet<>(Arrays.asList("eat", "watch Tv", "speak")));
    Human child1 = new Human("Elnur","Ismayilov","12/02/2010",94,null);
    Human child2 = new Human("Emil","Ismayilov","11/12/2006",92,null);
    Human child3 = new Human("Elvin","Ismayilov","06/06/2005",92, null);
    Human mother2 = new Human("Rima", "Quliyeva", "11/12/1980");
    Human father2 = new Human("Rasim", "Quliyev", "10/02/1980");
    FamilyService familyService = new FamilyService();


    @Test
    void getAllFamilies() {
        familyService.createNewFamily(mother1, father1);
        familyService.createNewFamily(mother2, father2);

        Assertions.assertEquals(new ArrayList<>(Arrays.asList(new Family(mother1, father1), new Family(mother2, father2))), familyService.getAllFamilies());
    }

    @Test
    void getFamiliesBiggerThan() {
        familyService.createNewFamily(mother1, father1);
        familyService.createNewFamily(mother2, father2);
        familyService.getFamilyById(1).addChild(child1);
        familyService.getFamilyById(1).addChild(child2);
        familyService.getFamilyById(2).addChild(child3);

        Assertions.assertEquals(new ArrayList<>(Arrays.asList(familyService.getFamilyById(1))),familyService.getFamiliesBiggerThan(3));
    }

    @Test
    void getFamiliesLessThan() {
        familyService.createNewFamily(mother1, father1);
        familyService.createNewFamily(mother2, father2);
        familyService.getFamilyById(1).addChild(child1);
        familyService.getFamilyById(1).addChild(child2);
        familyService.getFamilyById(2).addChild(child3);

        Assertions.assertEquals(new ArrayList<>(Arrays.asList(familyService.getFamilyById(2))),familyService.getFamiliesLessThan(4));
    }

    @Test
    void countFamiliesWithMemberNumber(){
        familyService.createNewFamily(mother1, father1);
        familyService.createNewFamily(mother2, father2);
        familyService.getFamilyById(1).addChild(child1);
        familyService.getFamilyById(1).addChild(child2);
        familyService.getFamilyById(2).addChild(child3);

        Assertions.assertEquals(1, familyService.countFamiliesWithMemberNumber(3));
    }


    @Test
    void createNewFamily() {
        familyService.createNewFamily(mother1, father1);

        Assertions.assertEquals(new Family(mother1, father1), familyService.getFamilyById(1));
    }

    @Test
    void deleteFamilyByIndexPositiveCase() {
        familyService.createNewFamily(mother1, father1);
        familyService.createNewFamily(mother2, father2);

        Assertions.assertTrue(familyService.deleteFamilyByIndex(2));
    }

    @Test
    void deleteFamilyByIndexNegativeCase() {
        familyService.createNewFamily(mother1, father1);
        familyService.createNewFamily(mother2, father2);

        Assertions.assertFalse(familyService.deleteFamilyByIndex(3));
    }

    @Test
    void bornChild() {
        familyService.createNewFamily(mother1, father1);
        Family family = familyService.bornChild(familyService.getFamilyById(1), "Eldar", "Sebine");

        Assertions.assertEquals(family, familyService.getFamilyById(1));
    }

    @Test
    void adoptChild() {
        familyService.createNewFamily(mother1, father1);
        Family family = familyService.adoptChild(familyService.getFamilyById(1), child1);

        Assertions.assertEquals(family, familyService.getFamilyById(1));
    }

    @Test
    void deleteAllChildrenOlderThan() {
        familyService.createNewFamily(mother1, father1);
        familyService.getFamilyById(1).addChild(child1);
        familyService.getFamilyById(1).addChild(child2);
        familyService.getFamilyById(1).addChild(child3);
        familyService.deleteAllChildrenOlderThan(13);

        Assertions.assertEquals(3, familyService.getFamilyById(1).countFamily());
    }

    @Test
    void count() {
        familyService.createNewFamily(mother1, father1);
        familyService.createNewFamily(mother2, father2);

        Assertions.assertEquals(2, familyService.count());
    }

    @Test
    void getFamilyById() {
        familyService.createNewFamily(mother1, father1);
        familyService.createNewFamily(mother2, father2);

        Assertions.assertEquals(new Family(mother2, father2), familyService.getFamilyById(2));
    }

    @Test
    void getPets() {
        familyService.createNewFamily(mother1, father1);
        familyService.getFamilyById(1).setPets(new HashSet<>(Arrays.asList(dog, cat)));

        Assertions.assertEquals(new HashSet<>(Arrays.asList(dog, cat)), familyService.getPets(1));
    }

    @Test
    void addPet() {
        familyService.createNewFamily(mother1, father1);
        familyService.getFamilyById(1).setPets(new HashSet<>(Arrays.asList(dog)));
        familyService.addPet(cat, 1);

        Assertions.assertEquals(new HashSet<>(Arrays.asList(dog, cat)), familyService.getPets(1));
    }

}