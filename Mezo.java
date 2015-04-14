package Class_Diagram;

public class Mezo {

	private boolean checkpoint;
	private boolean palyaszakasz;
	private Akadály akadaly;
	private Robot robot;
	private Vektor poziciovektor;

	/**
	 * 
	 * @param checkp
	 * @param akadaly
	 * @param robot
	 * @param pozicio
	 */
	public Mezo(boolean checkp, boolean akadaly, boolean robot, Vektor pozicio) {
		// TODO - implement Mezo.Mezo
		throw new UnsupportedOperationException();
	}

	public boolean getCheckpoint() {
		return this.checkpoint;
	}

	/**
	 * 
	 * @param checkpoint
	 */
	public void setCheckpoint(boolean checkpoint) {
		this.checkpoint = checkpoint;
	}

	public boolean getPalyaszakasz() {
		return this.palyaszakasz;
	}

	/**
	 * 
	 * @param palyaszakasz
	 */
	public void setPalyaszakasz(boolean palyaszakasz) {
		this.palyaszakasz = palyaszakasz;
	}

	public Akadaly getAkadaly() {
		// TODO - implement Mezo.getAkadaly
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param akadaly
	 */
	public void setAkadaly(Akadaly akadaly) {
		// TODO - implement Mezo.setAkadaly
		throw new UnsupportedOperationException();
	}

	public Robot getRobot() {
		return this.robot;
	}

	/**
	 * 
	 * @param robot
	 */
	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public Vektor getPoziciovektor() {
		return this.poziciovektor;
	}

	/**
	 * 
	 * @param poziciovektor
	 */
	public void setPoziciovektor(Vektor poziciovektor) {
		this.poziciovektor = poziciovektor;
	}

}