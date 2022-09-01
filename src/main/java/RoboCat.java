import java.util.Set;

public class RoboCat extends Pet{
    public RoboCat(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
    }

    @Override
    public void respond() {
        System.out.printf("Hello, owner. I am %s. I miss you!\n", super.getNickname());
    }
}
