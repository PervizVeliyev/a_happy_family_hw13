import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HumanEqualsHashCodeTest {
    Human child = new Human("Elnur","Ismayilov","10/12/2000",94, null);
    Human child1 = new Human("Emil","Ismayilov","23/06/1996",92, null);
    Human child2 = new Human("Elvin","Ismayilov","04/04/1996",92, null);
    Human child3 = new Human("Elvin","Ismayilov","04/04/1996",92, null);

    @Test
    void testEqualsFalse() {
        Assertions.assertNotEquals(child, child1);
    }

    @Test
    void testEqualsTrue() {
        Assertions.assertEquals(child2, child3);
    }

    @Test
    void testHashCodeTrue() {
        Assertions.assertEquals(child2.hashCode(), child3.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        Assertions.assertNotEquals(child.hashCode(), child1.hashCode());
    }
}