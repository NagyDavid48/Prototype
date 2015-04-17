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
		this.mezo = null;		//mezõ kérdése itt is
	}

	/**
	 * 
	 * @param v
	 */
	// if szerkezetet még át kell gondolni
	public int[] lep(Vektor v) {				//lehet már nincs is szükség erre a visszatérési értékre
		sebessegvektor.addVektor(v);
		Vektor poz = mezo.getPoziciovektor();
		Mezo mezo_c = t.getMezo(vektorAtvalt(poz));
		
		if(mezo_c.getPalyaszakasz() == false){
			this.kiesett = true;
		}else if(mezo_c.getRobot() != null){
			Robot r = mezo_c.getRobot();
			int eredmeny = r.utkozes(this);
			if(eredmeny == 0)
				this.mezo = mezo_c;
			else{
				this.mezo = null;
				this.setKiesett(true);
			}
			//ide kell még az ütközés kezelése 
		}else if(mezo_c.getAkadaly() != null){
			Akadaly a = mezo_c.getAkadaly();
			//akadaly életének lekérdezése kellene az Akadályba
			int elet = 2; //a.getElet();
			if(elet == 0){
				mezo_c.setAkadaly(null);
			}else{
				a.viselkedes(this);
			}
		}else if(mezo_c.getCheckpoint() == true){
			this.addCheckpoint();
			mezo_c.setCheckpoint(false);
		}
		this.mezo = mezo_c;
		
		return null;
	}

	/**
	 * 
	 * @param v
	 */
	public int[] vektorAtvalt(Vektor v) {
		Vektor pozicio = sebessegvektor.addVektor2(v);
		pozicio.skalarOszt(10);
		int[] tmp = new int[2];
		tmp[0] = pozicio.getX();
		tmp[1] = pozicio.getY();
		return tmp;
	}
	
	//Elvileg kész
	public void olajLerak() {
		Olajfolt akadaly = new Olajfolt();
		mezo.setAkadaly(akadaly);
	}

	//Elvileg Kész
	public void ragacsLerak() {
		Ragacs akadaly = new Ragacs();
		mezo.setAkadaly(akadaly);
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
		if(this.sebessegvektor.hossz() < v.hossz())
			return -1;//Ha mindketto hossza kissebb
		else if(this.sebessegvektor.hossz() > v.hossz())
			return 1;//Ha mindketto hossza nagyobb
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
			this.setMezo(null);
			return 0;
		}
		else{
			return 1;//Az allo volt a gyorsabb, az ugro pusztul.
		}
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