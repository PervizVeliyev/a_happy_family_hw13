import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

public class FamilyService {
    private static FamilyDao familyDao = new CollectionFamilyDao();

    List<Family> getAllFamilies(){
        return familyDao.getAllFamilies();
    }

    void displayAllFamilies(){
        familyDao.getAllFamilies()
                .forEach(family -> System.out.printf("%d) %s\n",familyDao.getAllFamilies().indexOf(family) + 1, family.prettyFormat()));
    }

    List<Family> getFamiliesBiggerThan(int number){
        return familyDao.getAllFamilies()
                .stream()
                .filter(family -> family.countFamily() > number)
                .collect(Collectors.toList());
    }

    List<Family> getFamiliesLessThan(int number){
        return familyDao.getAllFamilies()
                .stream()
                .filter(family -> family.countFamily() < number)
                .collect(Collectors.toList());
    }

    int countFamiliesWithMemberNumber(int number){
        return familyDao.getAllFamilies()
                .stream()
                .filter(family -> family.countFamily() == number)
                .toList()
                .size();
    }

    void createNewFamily(Human mother, Human father){
        Family familyCreated = new Family(mother, father);
        familyDao.saveFamily(familyCreated);
    }

    boolean deleteFamilyByIndex(int index){
        return familyDao.deleteFamily(index - 1);
    }

    Family bornChild(Family family, String masculine, String feminine){
        Human bornedChild = family.bornChild();
        if(bornedChild instanceof Man) bornedChild.setName(masculine);
        else bornedChild.setName(feminine);
        familyDao.saveFamily(family);
        return family;
    }

    Family adoptChild(Family family, Human child){
        family.addChild(child);
        if(child instanceof Man) family.getChildren().get(family.getChildren().size() - 1).setSurname(family.getFather().getSurname());
        else if(child instanceof Woman) family.getChildren().get(family.getChildren().size() - 1).setSurname(family.getMother().getSurname());
        familyDao.saveFamily(family);
        return family;
    }

    void deleteAllChildrenOlderThan(int age){
        familyDao.getAllFamilies()
                .forEach(family -> family.getChildren()
                        .forEach(child -> { if((now().getYear() - Instant.ofEpochMilli(child.getBirthDate())
                                .atZone(ZoneId.systemDefault()).
                                toLocalDate().getYear()) > age){
                            family.deleteChild(child);
                            familyDao.saveFamily(family);}}));
    }

    int count(){
        return familyDao.getAllFamilies().size();
    }

    Family getFamilyById(int index){
        return familyDao.getFamilyByIndex(index);
    }

    Set<Pet> getPets(int index){
        return familyDao.getFamilyByIndex(index).getPets();
    }

    void addPet(Pet pet, int index){
        familyDao.getFamilyByIndex(index).getPets().add(pet);
        familyDao.saveFamily(familyDao.getFamilyByIndex(index));
    }

    void loadData(List<Family> families){
        familyDao.loadData(families);
    }
}
