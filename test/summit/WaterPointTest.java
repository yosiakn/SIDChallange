package summit;

import static org.junit.Assert.*;

import org.junit.Test;

public class WaterPointTest {

	@Test
	public void testWaterPoint() {
		WaterPoint wp = new WaterPoint(true, "cisauk");
        wp.setCommunity("serpong");
        assertEquals(wp.getCommunity(), "serpong");
        wp.setFunctional(false);
        assertTrue(!wp.isFunctional());
        
	}

}
