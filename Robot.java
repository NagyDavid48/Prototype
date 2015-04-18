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
		this.mezo = null;		//mez� k�rd�se itt is
	}

	/**
	 * 
	 * @param v
	 */
	// Elvileg k�sz
	public void lep(Vektor v) {				//lehet m�r nincs is sz�ks�g erre a visszat�r�si �rt�kre
		sebessegvektor.addVektor(v);
		Vektor poz = mezo.getPoziciovektor();
		//az al�bbi m�veletet kiszedtem a vektor�tv�ltb�l, mert �gy tudom ki�ratni, hogy a kisrobot melyik mez�n van
		//r�ad�sul az jobb, ha a vekotr�tv�l nev� f�ggv�ny csak �tv�ltja  a vektotr �s nem csin�l m�st
		Vektor pozicio = sebessegvektor.addVektor2(poz);
		Mezo mezo_c = t.getMezo(vektorAtvalt(pozicio));
		
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
			int elet = a.getElet();
				if(elet == 0){
					mezo_c.setAkadaly(null);
				}else{
					a.viselkedes(this);
				}
		}
		
		if(mezo_c.getCheckpoint() == true && this.kiesett == false){
			this.addCheckpoint();
			mezo_c.setCheckpoint(false);
		}
		
		if(this.kiesett == false)
			this.mezo = mezo_c;
	}

	/**
	 * A lepeshez atvaltja a vektorokat mezo indexe.
	 * Osszeadja az uj seb.v.-t es a jelenlegi seb. v.-t es elosztja 10-zel.
	 * Ha valamelyik koordinata paros, hozza ad egyet.
	 * Szorozza 10-zel a vektort.
	 * (n-1)/2-t alkalmazva a koordinatakon egy-egy 10-zel valo osztas utan kesz az index. 
	 * @param v - Az uj sebesseg vektor.
	 */
	public int[] vektorAtvalt(Vektor v) { // TOTO a vektor�tbv�lt jelenleg 20-asokra kerek�t, j� ez �gy?
		v.skalarOszt(10);
		if(v.getX()%2==0)
			v.setX(v.getX()+1);
		if(v.getY()%2==0)
			v.setY(v.getY()+1);
		v.skalarSzoroz(10);
		
		int[] tmp = new int[2];
		tmp[0] = ((v.getX()/10)-1)/2;
		tmp[1] = ((v.getY()/10)-1)/2;
		return tmp;
	}
	
	//Elvileg k�sz
	public void olajLerak() {
		if (olaj > 0){
			Olajfolt akadaly = new Olajfolt();
			mezo.setAkadaly(akadaly);
			olaj--;
		}
	}

	//Elvileg K�sz
	public void ragacsLerak() {
		if (ragacs > 0){
			Ragacs akadaly = new Ragacs();
			mezo.setAkadaly(akadaly);
			ragacs = ragacs - 1;
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
		mezo.setRobot(this);
	}

	/**
	 * Sajat (s) vektort hasonlitja ossze egy masik (v) vektorral.
	 * Ha s = v : 0
	 * Ha s < v : -1
	 * Ha s > v : 1
	 * @param v - M�sik robot vektora.
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
	 * A h�v� robot ugrik �ppen egy allora (this).
	 * @param r - Ez a robot ugrott erre a robotra.
	 */
	public int utkozes(Robot r) {
		int x = this.compareTo(r.getSebessegvektor());//Hasonlitas
		if(x <= 0){//Ha az allo volt a lassabb vagy egyenloek voltak.
			this.setKiesett(true);//Kiesett �s a mez�t m�r �t�ll�tottuk null-ra, hiszen ez ugrik
			this.setMezo(null);//Mivel mar nam letezik a mezon sem all
			return 0;
		}
		else{
			return 1;//Az allo volt a gyorsabb, az ugro pusztul.
		}
	}

	/**
	 * Kis Robot - Nagy Robot utkozese.
	 * A kisrobot raugrik a nagyra, ekkor vissza kerul az eredeti poziciojara.
	 * Lepattan a nagyrol.
	 * @param r - Kis robot, ami ra ugrik a nagyra.
	 */
	public void utkozes(KisRobot r) {
		Vektor tmp = r.getSebessegvektor();//Aktualis seb. v.
		tmp.skalarSzoroz(-1);//Vissza kell pattania.
		r.lep(tmp);//Ezzel leptetjuk vissza. Remelhetoleg a kiindulasi mezobe ter vissza.
		tmp.skalarSzoroz(-1);//Az eredeti vektort vissza kellene allitani.
		r.setSebessegvektor(tmp);
		
	}

	public int getOlaj() {
		return olaj;
	}

	public int getRagacs() {
		return ragacs;
	}
	public void kiirstat(int i) {
		System.out.print("robot "+i+" "+olaj+" "+ragacs+" "+sebessegvektor.getX()+" "+sebessegvektor.getY()+" "+kiesett+" "+checkpoint+" "+olajonvan);
	}

}