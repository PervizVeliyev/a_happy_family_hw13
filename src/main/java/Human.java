import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Human implements Serializable{
    private String name;
    private String surname;
    private long birthDate;                    // Unix Millis Timestamp
    private int iq;
    private Map<String, String> schedule;
    private Family family;

    static {
        System.out.println("A new human is being loaded");
    }

    {
        System.out.println("A new human object is created");
    }

    public Human(String name, String surname, String date) {
        this.name = name;
        this.surname = surname;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTemp = LocalDate.parse(date, formatter);
        this.birthDate = TimeUnit.DAYS.toMillis(dateTemp.toEpochDay());
    }

    public Human(String name, String surname, String date, int iq, Map<String, String> schedule) {
        this.name = name;
        this.surname = surname;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTemp = LocalDate.parse(date, formatter);
        this.birthDate = TimeUnit.DAYS.toMillis(dateTemp.toEpochDay());
        this.iq = iq;
        this.schedule = schedule;
    }

    public Human(String name, String surname, String date, int iq) {
        this.name = name;
        this.surname = surname;
        this.iq = iq;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTemp = LocalDate.parse(date, formatter);
        this.birthDate = TimeUnit.DAYS.toMillis(dateTemp.toEpochDay());
    }

    public Human() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public Map<String, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, String> schedule) {
        this.schedule = schedule;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public String toString() {
        LocalDate ld = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate();
        String formattedDate = ld.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth date='" + formattedDate + '\'' +
                ", iq=" + iq +
                ", schedule=" + this.getSchedule() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return birthDate == human.birthDate && iq == human.iq && name.equals(human.name) && surname.equals(human.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, iq, family);
    }

    @Override
    protected void finalize() {
        System.out.println("Human object is destroyed");
    }

    public void greetPets(){
        Iterator<Pet> iterator = family.getPets().iterator();
        while(iterator.hasNext()) {
            Pet element = iterator.next();
            System.out.printf("Hello, %s! I'm your owner.\n", element.getNickname());
        }
    }
    public void describePets(){
        Iterator<Pet> iterator = family.getPets().iterator();
        while (iterator.hasNext()) {
            String message;
            Pet element = iterator.next();
            if (element.getTrickLevel() > 50) message = "very sly";
            else message = "almost not sly";
            System.out.printf("I have a %s, he is %d years old, he is %s.\n", element.getSpecies().toString().toLowerCase(), element.getAge(), message);
        }
    }
    public boolean feedPets(boolean isItTimeForFeeding){
        Iterator<Pet> iterator = family.getPets().iterator();
        boolean result = true;
        while(iterator.hasNext()) {
            Random random = new Random();
            Pet element = iterator.next();
            int randomNumber = random.nextInt(1, 101);
            if (isItTimeForFeeding) {
                System.out.printf("Hm... I will feed %s's %s.%n", this.name, element.getSpecies().toString().toLowerCase());
            } else {
                if (element.getTrickLevel() > randomNumber) {
                    System.out.printf("Hm... I will feed %s's %s.%n", this.name, element.getSpecies().toString().toLowerCase());
                } else {
                    System.out.printf("I think %s's %s is not hungry.%n", this.name, element.getSpecies().toString().toLowerCase());
                    result = false;
                }
            }
        }
        return result;
    }

    public String describeAge(){
        LocalDate dateStart = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateEnd = LocalDate.now();
        return String.format("%d years, %d months, %d days", Period.between(dateStart, dateEnd).getYears(),
                Period.between(dateStart, dateEnd).getMonths(), Period.between(dateStart, dateEnd).getDays());
    }

    public String prettyFormat(){
        LocalDate ld = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate();
        String formattedDate = ld.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "{name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + formattedDate + '\'' +
                ", iq=" + iq +
                ", schedule=" + this.getSchedule() +
                '}';
    }
}
