package summit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Service {
	public static final String COMMUNITIES_VILLAGES = "communities_villages";
	public static final String COMMUNITY_RANKING = "community_ranking";
	public static final String TOTAL_FUNCTIONAL = "number_functional";
	public static final String NUMBER_WATER_POINTS = "number_water_points";
	public static final String WATER_FUNCTIONING = "water_functioning";
	public static final String WATER_FUNCTIONING_YES = "yes";
	public static final String PERCENTAGE_WATER_POINT = "percentage_water_point";

	Map<String, Community> communitiesWaterPoints;

	private int functionalWaterPoints = 0;

	public Service() {
		// TODO Auto-generated constructor stub
		communitiesWaterPoints = new HashMap<String, Community>();
	}

	// first called, calculate as request
	public JSONObject calculate(String url) {
		try {
			String waterPoints = readJSONUrl(url);
			processWaterPoints(waterPoints);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generatedReport();

	}

	public void processWaterPoints(String waterPoints) throws JSONException {
		boolean isFunctional;
		String commName;
		String waterFunctioning;
		WaterPoint waterPoint;
		JSONArray json = new JSONArray(waterPoints);
		for (int i = 0; i < json.length(); i++) {
			JSONObject waterPointObject = (JSONObject) json.getJSONObject(i);

			commName = (String) waterPointObject.get(COMMUNITIES_VILLAGES);
			waterFunctioning = (String) waterPointObject.get(WATER_FUNCTIONING);
			isFunctional = waterFunctioning.trim().equals(WATER_FUNCTIONING_YES) ? true : false;
			waterPoint = new WaterPoint(isFunctional, commName);
			
			addWaterPoint(waterPoint);
		}
	}

	public JSONObject generatedReport() {
		// The object to return.
		JSONObject report = new JSONObject();

		try {
			//retrieve total functional water
			report.put(TOTAL_FUNCTIONAL, getFunctionalWaterPoints());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Retrieve total number of water point for each community.
		// Retrieve total percentage of broken water point for each community.
		List<Community> commWaterPoints = new ArrayList<Community>(communitiesWaterPoints.values());
		JSONObject totPerCommunity = new JSONObject();
		JSONObject totBrokenPerCommunity = new JSONObject();
		for (Community comm : commWaterPoints) {
			try {
				totPerCommunity.put(comm.getCommunityName(), comm.getTotalWaterPoints());
				totBrokenPerCommunity.put(comm.getCommunityName(), Float.valueOf(String.format("%.2f", comm.getBrokenPercentage())) + "%");
			} catch (JSONException ex) {
				Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		try {
			report.put(NUMBER_WATER_POINTS, totPerCommunity);
			report.put(PERCENTAGE_WATER_POINT, totBrokenPerCommunity);
		} catch (JSONException ex) {
			Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
		}

		// rank the waterpoint communities
		Collections.sort(commWaterPoints);
		Collections.reverse(commWaterPoints);
		JSONObject cpsRank = new JSONObject();
		int rank = 1;
		for (Community comm : commWaterPoints) {
			try {
				cpsRank.put(comm.getCommunityName(), rank);
			} catch (JSONException ex) {
				Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
			}
			rank++;
		}
		try {
			// Collections.sort(cwps);
			report.put(COMMUNITY_RANKING, cpsRank);

		} catch (JSONException ex) {
			Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
		}

		return report;
	}

	private void addWaterPoint(WaterPoint waterPoint) {
		String community = waterPoint.getCommunity();
		Community cwp;
		if (communitiesWaterPoints.containsKey(community)) {
			cwp = communitiesWaterPoints.get(community);

			// Increment the total number of water points for the community.
			int totalWP = cwp.getTotalWaterPoints();
			cwp.setTotalWaterPoints(totalWP + 1);
		} else {
			cwp = new Community(community, 1, 0);
		}

		if (waterPoint.isFunctional()) {// increment functional water points.
			functionalWaterPoints++;
		} else {// increment broken water points for community.

			int brokenWP = cwp.getBrokenWaterPoints();
			cwp.setBrokenWaterPoints(brokenWP + 1);
		}
		communitiesWaterPoints.put(community, cwp);
	}

	private String readJSONUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder buffer = new StringBuilder();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1) {
				buffer.append(chars, 0, read);
			}
			return buffer.toString();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	/**
	 * @return the functionalWaterPoints
	 */
	public int getFunctionalWaterPoints() {
		return functionalWaterPoints;
	}

	/**
	 * @param functionalWaterPoints
	 *            the functionalWaterPoints to set
	 */
	public void setFunctionalWaterPoints(int functionalWaterPoints) {
		this.functionalWaterPoints = functionalWaterPoints;
	}

}
