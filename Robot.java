package Class_Diagram;

public class Robot extends Robotok {

	private Vektor sebessegvektor;
	private int olaj;
	private int ragacs;
	private int checkpoint;
	private Mezo mezo;
	private boolean olajonvan;
	private boolean kiesett;

	/**
	 * 
	 * @param olajszam
	 * @param ragacsszam
	 */
	public Robot(int olajszam, int ragacsszam) {
		// TODO - implement Robot.Robot
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param v
	 */
	public int[] lep(Vektor v) {
		// TODO - implement Robot.lep
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param v
	 */
	public int[] vektorAtvalt(Vektor v) {
		// TODO - implement Robot.vektorAtvalt
		throw new UnsupportedOperationException();
	}

	public void olajLerak() {
		// TODO - implement Robot.olajLerak
		throw new UnsupportedOperationException();
	}

	public void ragacsLerak() {
		// TODO - implement Robot.ragacsLerak
		throw new UnsupportedOperationException();
	}

	public void ragacsraLepett() {
		// TODO - implement Robot.ragacsraLepett
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param olajonvan
	 */
	public void setOlajonvan(boolean olajonvan) {
		this.olajonvan = olajonvan;
	}

	public boolean getOlajonvan() {
		return this.olajonvan;
	}

	public int getCheckpoint() {
		return this.checkpoint;
	}

	public void addCheckpoint() {
		// TODO - implement Robot.addCheckpoint
		throw new UnsupportedOperationException();
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

	/**
	 * 
	 * @param v
	 */
	public int compareTo(Vektor v) {
		// TODO - implement Robot.compareTo
		throw new UnsupportedOperationException();
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
	 * @param r
	 */
	public int utkozes(Robot r) {
		// TODO - implement Robot.utkozes
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param r
	 */
	public int utkozes(KisRobot r) {
		// TODO - implement Robot.utkozes
		throw new UnsupportedOperationException();
	}

}