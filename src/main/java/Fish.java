import java.util.Set;

public class Fish extends Pet{
    public Fish(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
    }

    public void respond() {
        System.out.printf("Hello, owner. I am %s. I miss you!\n", super.getNickname());
    }
}
