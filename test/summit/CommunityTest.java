package summit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CommunityTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testCommunity() {
		Community community = new Community("cisauk", 10, 5);
        assertEquals(community.getCommunityName(), "cisauk");
        assertEquals(community.getTotalWaterPoints(), 10);
        assertEquals(community.getBrokenWaterPoints(), 5);
	}

	
	@Test
    public void testBrokenPercentage() {
        Community comm1 = new Community("cisauk", 10, 5);
        Community comm2 = new Community("serpong", 5, 2);
        int brokePer1 = (int) (comm1.getBrokenPercentage() * 100);
        int brokePer2 = (int) (comm2.getBrokenPercentage() * 100);
        assertNotEquals(brokePer1, brokePer2); 
        assertTrue(comm2.getBrokenPercentage() < comm1.getBrokenPercentage());
    } 

}
