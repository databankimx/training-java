import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ClampTest {
    @Test
    void insideTheRangeStaysTheSame() {
        assertEquals(5, Main.clamp(5, 0, 10));
    }

    @Test
    void belowTheRangeBecomesTheLowEnd() {
        assertEquals(0, Main.clamp(-3, 0, 10));
    }

    @Test
    void aboveTheRangeBecomesTheHighEnd() {
        assertEquals(10, Main.clamp(15, 0, 10));
    }

    @Test
    void theLowEndStaysTheSame() {
        assertEquals(0, Main.clamp(0, 0, 10));
    }

    @Test
    void theHighEndStaysTheSame() {
        assertEquals(10, Main.clamp(10, 0, 10));
    }
}
