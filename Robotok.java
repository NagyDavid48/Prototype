package Prototype;

public abstract class Robotok {
	protected Tarolo t; //Asszociáció miatt
	
	/**
	 * A robotokat lepteti.
	 * Minden fajta robot mashogy lep.
	 * @param v - Uj sebesseg vektor.
	 */
	public abstract void lep(Vektor v);

	/**
	 * Vektorbol csinal mezo indexet.
	 * @param v - Uj seb. v.
	 */
	public abstract int[] vektorAtvalt(Vektor v);

	/**
	 * Utkozes kezelesere.
	 * @param r - Ugro robot.
	 */
	public abstract int utkozes(Robot r);

	/**
	 * Kisrobotok mashogy viselkednek.
	 * Muszaj egy maik fv. erre.
	 * @param r - Ugro kis robot.
	 */
	public abstract void utkozes(KisRobot r);

}