package Prototype;

public abstract class Robotok {
	protected Tarolo t; //Asszociáció miatt
	
	/**
	 * 
	 * @param v
	 */
	public abstract int[] lep(Vektor v);

	/**
	 * 
	 * @param v
	 */
	public abstract int[] vektorAtvalt(Vektor v);

	/**
	 * 
	 * @param r
	 */
	public abstract int utkozes(Robot r);

	/**
	 * 
	 * @param r
	 */
	public abstract int utkozes(KisRobot r);

}