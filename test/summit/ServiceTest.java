package summit;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class ServiceTest {

	@Test
	public void testProcessWaterPoints() throws JSONException {
		Service processor = new Service();
		processor.calculate("https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json");
		JSONObject result = processor.generatedReport();
		assertEquals(result.get(Service.TOTAL_FUNCTIONAL), 623);
		
		JSONObject numWaterPoints = (JSONObject) result.get(Service.NUMBER_WATER_POINTS);
		assertEquals(numWaterPoints.get("Zundem"), 30);
		assertEquals(numWaterPoints.get("Kpatarigu"), 51);
		 
		JSONObject communityRanking = (JSONObject) result.get(Service.COMMUNITY_RANKING);
		assertEquals(communityRanking.get("Zundem"), 65);
		assertEquals(communityRanking.get("Kpatarigu"), 25);
	}

}
