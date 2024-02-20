import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ReviewTest {

    @Test
    public void testGetRating() {
        Review review = new Review("Alice", 5, "Great instructor!");
        assertEquals(5, review.getRating());
    }
}
