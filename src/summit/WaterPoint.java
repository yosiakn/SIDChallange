/**
 * 
 */
package summit;

/**
 * @author yosiakn
 *
 */
public class WaterPoint {
	
	
	private boolean functional;
	private String community;

	public WaterPoint(boolean functional, String community) {
		super();
		this.functional = functional;
		this.community = community;
	}
	 

	/**
	 * @return the functional
	 */
	public boolean isFunctional() {
		return functional;
	}

	/**
	 * @param functional the functional to set
	 */
	public void setFunctional(boolean functional) {
		this.functional = functional;
	}

	/**
	 * @return the community
	 */
	public String getCommunity() {
		return community;
	}

	/**
	 * @param community the community to set
	 */
	public void setCommunity(String community) {
		this.community = community;
	}
	
	
}
