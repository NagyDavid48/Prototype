package Prototype;

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
		this.olaj = olajszam;
		this.ragacs = ragacsszam;
		this.olajonvan = false;
		this.kiesett = false;
		this.checkpoint = 0;
		this.sebessegvektor = new Vektor();//(0;0)
		//this.mezo = null;
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

	/**
	 * Ha a robot ragacsra lep, akkor ez megfelezi az aktualis sebesseget.
	 * A koordinatakat elosztja kettovel, nem torodik a kerekitessel.
	 * Lehet (3;0)-nal --> (1;0) lesz az uj vektor.
	 */
	public void ragacsraLepett() {
		this.sebessegvektor.skalarOszt(2);// Kerekites necces lehet.
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

	/**
	 * A robot CP szamat noveli eggyel CP-re lepesnel.
	 */
	public void addCheckpoint() {
		this.checkpoint++;
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
	 * Adott mezot beallit.
	 * @param mezo - Ezt a mezot allitja be.
	 */
	public void setMezo(Mezo mezo) {
		this.mezo = mezo;
	}

	/**
	 * Sajat (s) vektort hasonlitja ossze egy masik (v) vektorral.
	 * Ha s = v : 0
	 * Ha s < v : -1
	 * Ha s > v : 1
	 * @param v - Másik robot vektora.
	 */
	private int compareTo(Vektor v) {
		if(this.sebessegvektor.getX() < v.getX() && this.sebessegvektor.getY() == v.getY())
			return -1;//Ha csak a x kissebb
		else if(this.sebessegvektor.getX() == v.getX() && this.sebessegvektor.getY() < v.getY())
			return -1;//Ha csak a y kissebb
		else if(this.sebessegvektor.getX() < v.getX() && this.sebessegvektor.getY() < v.getY())
			return -1;//Ha mindketto kissebb
		else if(this.sebessegvektor.getX() > v.getX() && this.sebessegvektor.getY() == v.getY())
			return 1;//Ha csak  x nagyobb
		else if(this.sebessegvektor.getX() == v.getX() && this.sebessegvektor.getY() > v.getY())
			return 1;//Ha csak a y nagyobb
		else if(this.sebessegvektor.getX() > v.getX() && this.sebessegvektor.getY() > v.getY())
			return 1;//Ha mindketto nagyobb
		return 0;//Ha egyenloek
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
	 * Nagy Robot - Nagy Robot utkozese.
	 * A hívó robot ugrik éppen egy allora (this).
	 * @param r - Ez a robot ugrott erre a robotra.
	 */
	public int utkozes(Robot r) {
		int x = this.compareTo(r.getSebessegvektor());
		if(x <= 0){//Ha az allo volt a lassabb vagy egyenloek voltak.
			this.setKiesett(true);//Kiesett és a mezõt már átállítottuk null-ra, hiszen ez ugrik
			r.setMezo(this.mezo);
			return 0;
		}
		return 1;//Az allo volt a gyorsabb, az ugro pusztul.
	}

	/**
	 * Kis Robot - Nagy Robot utkozese.
	 * A kisrobot raugrik a nagyra, ekkor vissza kerul az eredeti poziciojara.
	 * @param r - Kis robot, ami ra ugrik a nagyra.
	 */
	public int utkozes(KisRobot r) {
		
		return 0;//Kell még.
	}

}