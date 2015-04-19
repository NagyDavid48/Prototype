package Prototype;

import java.io.PrintWriter;

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
	// Elvileg kész
	public void lep(Vektor v) {				//lehet már nincs is szükség erre a visszatérési értékre
		if (olajonvan == false)				// ha olajon vagyunk, nem változtathatunk sebességet
			sebessegvektor.addVektor(v);
		Vektor poz = mezo.getPoziciovektor();	// a jelenlegi pozíciónk helyvektora
		//az alábbi mûveletet kiszedtem a vektorátváltból, mert így tudom kiíratni, hogy a kisrobot melyik mezõn van
		//ráadásul az jobb, ha a vekotrátvál nevû függvény csak átváltja  a vektotr és nem csinál mást
		Vektor pozicio = sebessegvektor.addVektor2(poz);	// a célmezõ helyvektora (sebesség+hely)
		
		try {
			
			this.mezo.setRobot(null);						// elugrunk a mezõrõl
			
			Mezo mezo_c = Tarolo.getMezo(vektorAtvalt(pozicio));// a célmezõ kiszámolása
			if(mezo_c.getPalyaszakasz() == false)
				this.kiesett = true;
			
			if(mezo_c.getRobot() != null && this.kiesett == false){
				Robotok r = mezo_c.getRobot();
				int eredmeny = r.utkozes(this);
				if(eredmeny == 0)
					this.mezo = mezo_c;
				else{
					this.mezo = null;
					this.setKiesett(true);
				}
			}
			
			if(mezo_c.getAkadaly() != null && this.kiesett == false){
				Akadaly a = mezo_c.getAkadaly();
				a.viselkedes(this);
				int elet = a.getElet();
					if(elet == 0)
						mezo_c.setAkadaly(null);
			}
			
			if(mezo_c.getCheckpoint() == true && this.kiesett == false){
				this.addCheckpoint();
				mezo_c.setCheckpoint(false);
			}
			
			if(this.kiesett == false){
				setMezo(mezo_c);
			}
		} catch (IndexOutOfBoundsException e) {								// ha leestünk a pálya szélérõl, kiestünk
			this.kiesett = true;
			this.olajonvan = false;
			setMezo(null);
		}
			
	}

	
	//Elvileg kész
	public void olajLerak() {
		if (olaj > 0){
			Olajfolt akadaly = new Olajfolt();
			mezo.setAkadaly(akadaly);
			olaj--;
			lep(new Vektor());
		}
	}

	//Elvileg Kész
	public void ragacsLerak() {
		if (ragacs > 0){
			Ragacs akadaly = new Ragacs();
			mezo.setAkadaly(akadaly);
			ragacs = ragacs - 1;
			lep(new Vektor());
		}
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
		if(mezo != null)
			mezo.setRobot(this);
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
		int x = this.compareTo(r.getSebessegvektor());//Hasonlitas
		if(x <= 0){//Ha az allo volt a lassabb vagy egyenloek voltak.
			this.setKiesett(true);//Kiesett és a mezõt már átállítottuk null-ra, hiszen ez ugrik
			this.setMezo(null);//Mivel mar nam letezik a mezon sem all
			r.getSebessegvektor().atlagol(sebessegvektor);
			return 0;
		}
		else{
			sebessegvektor.atlagol(r.getSebessegvektor());
			return 1;//Az allo volt a gyorsabb, az ugro pusztul.
		}
	}


	public int getOlaj() {
		return olaj;
	}

	public int getRagacs() {
		return ragacs;
	}
	public void kiirstat(int i, PrintWriter w) {
		System.out.print("robot "+i+" "+olaj+" "+ragacs+" "+sebessegvektor.getX()+" "+sebessegvektor.getY()+" "+kiesett+" "+checkpoint+" "+olajonvan);
		w.print("robot "+i+" "+olaj+" "+ragacs+" "+sebessegvektor.getX()+" "+sebessegvektor.getY()+" "+kiesett+" "+checkpoint+" "+olajonvan);
	}

}