package Prototype;

public class KisRobot extends Robotok {

	private Vektor sebessegvektor;
	private Mezo mezo;
	private boolean foltonvan;
	private boolean kiesett;

	public KisRobot() {
		// TODO - implement KisRobot.KisRobot
		throw new UnsupportedOperationException();
	}

	public boolean getFoltonvan() {
		return this.foltonvan;
	}

	/**
	 * 
	 * @param foltonvan
	 */
	public void setFoltonvan(boolean foltonvan) {
		this.foltonvan = foltonvan;
	}

	public boolean getKiesett() {
		return this.kiesett;
	}

	/**
	 * 
	 * @param kiesett
	 */
	public void setKiesett(boolean kiesett) {
		this.kiesett = kiesett;
	}

	/**
	 * 
	 * @param v
	 */
	public int[] lep(Vektor v) {
		// TODO - implement KisRobot.lep
		throw new UnsupportedOperationException();
	}

	public Mezo getMezo() {
		return this.mezo;
	}

	/**
	 * 
	 * @param mezo
	 */
	public void setMezo(Mezo mezo) {
		this.mezo = mezo;
	}

	public Vektor getSebessegvektor() {
		return this.sebessegvektor;
	}

	/**
	 * 
	 * @param sebessegvektor
	 */
	public void setSebessegvektor(Vektor sebessegvektor) {
		this.sebessegvektor = sebessegvektor;
	}

	/**
	 * 
	 * @param v
	 */
	public int[] vektorAtvalt(Vektor v) {
		// TODO - implement KisRobot.vektorAtvalt
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param r
	 */
	public int utkozes(Robot r) {
		// TODO - implement KisRobot.utkozes
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param r
	 */
	public int utkozes(KisRobot r) {
		// TODO - implement KisRobot.utkozes
		throw new UnsupportedOperationException();
	}

	public void takarit() {
		// TODO - implement KisRobot.takarit
		throw new UnsupportedOperationException();
	}

}