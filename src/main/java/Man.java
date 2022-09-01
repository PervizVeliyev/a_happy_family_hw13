import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public final class Man extends Human implements Serializable {
    public Man(String name, String surname, String date, int iq, Map<String,String> schedule) {
        super(name, surname, date, iq, schedule);
    }

    public Man(String name, String surname, String date, int iq) {
        super(name, surname, date, iq);
    }

    public Man() {
    }

    public Man(String name, String surname, String date) {
        super(name, surname, date);
    }

    @Override
    public void greetPets() {
        Iterator<Pet> iterator = this.getFamily().getPets().iterator();
        while(iterator.hasNext()) {
            Pet element = iterator.next();
            System.out.printf("Hello, %s! I'm your male owner.\n", element.getNickname());
        }
    }

    public void repairCar() {
        System.out.println("I repaired my car");
    }
}
