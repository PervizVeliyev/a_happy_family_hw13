import java.util.Objects;
import java.util.Set;

public abstract class Pet {
    private Species species;
    private String nickname;
    private int age;
    private int trickLevel;
    private Set<String> habits;

    static {
        System.out.println("A new pet is being loaded");
    }

    {
        System.out.println("A new pet object is created");
    }

    public Pet(String nickname, int age, int trickLevel, Set<String> habits) {
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
        this.species = Species.UNKNOWN;

        for(Species species: Species.values()){
            if(this.getClass().getName().equalsIgnoreCase(species.name())) {
                this.species = species;
                break;
            }
        }
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTrickLevel() {
        return trickLevel;
    }

    public void setTrickLevel(int trickLevel) {
        this.trickLevel = trickLevel;
    }

    public Set<String> getHabits() {
        return habits;
    }

    public void setHabits(Set<String> habits) {
        this.habits = habits;
    }

    @Override
    public String toString() {
        return  this.species.name() + '{' +
                "nickname='" + nickname + '\'' +
                ", age=" + age +
                ", trickLevel=" + trickLevel +
                ", habits=" + habits +
                ", canFly=" + species.canFly +
                ", numberOfLegs=" + species.numberOfLegs +
                ", hasFur=" + species.hasFur + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return trickLevel == pet.trickLevel && nickname.equals(pet.nickname) && Objects.equals(habits, pet.habits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, trickLevel, habits);
    }

    @Override
    protected void finalize() {
        System.out.println("Pet object is destroyed");
    }

    public final void eat() {
        System.out.println("I am eating.");
    }

    public abstract void respond();

    public String prettyFormat(){
        return  '{' +
                "species=" + this.species.name() +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", trickLevel=" + trickLevel +
                ", habits=" + habits + '}';
    }
}
