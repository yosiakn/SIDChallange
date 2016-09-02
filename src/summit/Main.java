package summit;

import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	private static final String URL = "https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json";
	//private static final String URL = "file:///C:/Users/pualayuka/Desktop/water_points.json.txt";

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject report;
		Service services = new Service();

		// There should be a top level object or function
		// calculate(“http://...”)
		report = services.calculate(URL);
		try {
			System.out.println(
					"Total number water points that are funtional : " + report.get(Service.TOTAL_FUNCTIONAL) + "\n");
			System.out.println("The number of water points per community (Community : Total water point ) : \n "
					+ report.get(Service.NUMBER_WATER_POINTS) + "\n");
			System.out.println("The rank for each community (Community : Rangking of broken ) : \n"
					+ report.get(Service.COMMUNITY_RANKING) + "\n");
			System.out.println("The percentage of broken water point  (Community : Percentage of broken ) : \n"
					+ report.get(Service.PERCENTAGE_WATER_POINT) + "\n");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
