package Prototype;

public class Mezo {

	private boolean checkpoint;
	private boolean palyaszakasz;
	private Akadaly akadaly;
	private Robotok robot;
	private Vektor poziciovektor;

	/**
	 * 
	 * @param checkp
	 * @param palyaszak
	 * @param akadaly
	 * @param robot
	 * @param pozicio
	 */
	public Mezo(boolean checkp, boolean palyaszak, Akadaly akadaly, Robotok robot, Vektor pozicio) {
		this.checkpoint = checkp;
		this.palyaszakasz = palyaszak;
		this.akadaly = akadaly;
		this.robot = robot;
		this.poziciovektor = pozicio;
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
		return this.akadaly;
	}

	/**
	 * 
	 * @param akadaly
	 */
	public void setAkadaly(Akadaly akadaly) {
		this.akadaly = akadaly;
	}

	public Robotok getRobot() {
		return this.robot;
	}

	/**
	 * 
	 * @param robot
	 */
	public void setRobot(Robotok robot) {
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