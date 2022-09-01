import java.util.Set;

public class Dog extends Pet implements IDog{
    public Dog(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
    }

    public void respond() {
        System.out.printf("Hello, owner. I am %s. I miss you!\n", super.getNickname());
    }

    @Override
    public void foul() {
        System.out.println("I'm fouling :)");
    }
}
