import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao{
    private List<Family> families = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return families;
    }

    @Override
    public Family getFamilyByIndex(int index) {
        return families.get(index - 1);
    }

    @Override
    public boolean deleteFamily(int index) {
        try{
            families.remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteFamily(Family family) {
        if(families.contains(family)){
            families.remove(family);
            return true;
        }
        else return false;
    }

    @Override
    public void saveFamily(Family family) {
        if(families.contains(family)) families.set(families.indexOf(family), family);
        else families.add(family);
    }

    @Override
    public void loadData(List<Family> families) {
        families.forEach(family -> saveFamily(family));
    }
}
