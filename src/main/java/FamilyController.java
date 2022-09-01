import java.util.List;
import java.util.Set;

public class FamilyController {
    private static FamilyService familyService = new FamilyService();

    List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    void displayAllFamilies() {
        familyService.displayAllFamilies();
    }

    void getFamiliesBiggerThan(int number) {
        familyService.getFamiliesBiggerThan(number)
                .forEach(family -> System.out.printf("%d) %s\n",familyService.getAllFamilies().indexOf(family) + 1, family.prettyFormat()));
    }

    void getFamiliesLessThan(int number) {
        familyService.getFamiliesLessThan(number)
                .forEach(family -> System.out.printf("%d) %s\n",familyService.getAllFamilies().indexOf(family) + 1, family.prettyFormat()));
    }

    int countFamiliesWithMemberNumber(int number){return familyService.countFamiliesWithMemberNumber(number);}

    void createNewFamily(Human father, Human mother) {
        familyService.createNewFamily(father, mother);
    }

    boolean deleteFamilyByIndex(int index) {
        return familyService.deleteFamilyByIndex(index);
    }

    Family bornChild(Family family, String masculine, String feminine) {
        return familyService.bornChild(family, masculine, feminine);
    }

    Family adoptChild(Family family, Human child) {
        return familyService.adoptChild(family, child);
    }

    void deleteAllChildrenOlderThan(int age) {
        familyService.deleteAllChildrenOlderThan(age);
    }

    int count() {
        return familyService.count();
    }

    Family getFamilyById(int index) {
        return familyService.getFamilyById(index);
    }

    Set<Pet> getPets(int index) {
        return familyService.getPets(index);
    }

    void addPet(Pet pet, int index) {
        familyService.addPet(pet, index);
    }

    void loadData(List<Family> families){
        familyService.loadData(families);
    }
}
