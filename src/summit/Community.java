package summit;

public class Community implements Comparable<Community> {

	private int totalWaterPoints;
	private int brokenWaterPoints;
	private String communityName;

	public Community(String name, int totalWaterPoints, int brokenWaterPoints) {
		this.communityName = name;
		this.totalWaterPoints = totalWaterPoints;
		this.setBrokenWaterPoints(brokenWaterPoints);
	}

	public double getBrokenPercentage() {
		return (double) this.getBrokenWaterPoints() * 100 / this.totalWaterPoints;
	}

	@Override
	public int compareTo(Community comp) {
		if (getBrokenPercentage() == comp.getBrokenPercentage()) {
			return 0;
		} else if (getBrokenPercentage() > comp.getBrokenPercentage()) {
			return 1;
		} else {
			return -1;
		}

	}

	/**
	 * @return the totalWaterPoints
	 */
	public int getTotalWaterPoints() {
		return totalWaterPoints;
	}

	/**
	 * @param totalWaterPoints
	 *            the totalWaterPoints to set
	 */
	public void setTotalWaterPoints(int totalWaterPoints) {
		this.totalWaterPoints = totalWaterPoints;
	}

	/**
	 * @return the communityName
	 */
	public String getCommunityName() {
		return communityName;
	}

	/**
	 * @param communityName
	 *            the communityName to set
	 */
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	/**
	 * @return the brokenWaterPoints
	 */
	public int getBrokenWaterPoints() {
		return brokenWaterPoints;
	}

	/**
	 * @param brokenWaterPoints the brokenWaterPoints to set
	 */
	public void setBrokenWaterPoints(int brokenWaterPoints) {
		this.brokenWaterPoints = brokenWaterPoints;
	}

}
