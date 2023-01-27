package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BooleanTest {

    /**
     *  A dummy unit test to test if two booleans are true or false.
     */
    @Test
    public void testBooleanValues() {
        boolean b1 = false;
        boolean b2 = true;
        assertFalse(b1);
        assertTrue(b2);
    }
}