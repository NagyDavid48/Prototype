package Prototype;

import java.io.PrintWriter;

public class KisRobot extends Robotok {

	private Vektor sebessegvektor;
	private Mezo mezo;
	private boolean foltonvan;
	private boolean kiesett;

	public KisRobot() {
		sebessegvektor = new Vektor();
		mezo = null;
		foltonvan = false;
		kiesett = false;
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
	public void lep(Vektor v) {
		//Ha m�r meghalt szeg�ny akkor ne is l�pjen
		if (this.kiesett == true){
			return;
		}
		
		this.setSebessegvektor(v);
		Vektor poz = mezo.getPoziciovektor();
		//az al�bbi m�veletet kiszedtem a vektor�tv�ltb�l, mert �gy tudom ki�ratni, hogy a kisrobot melyik mez�n van
		//r�ad�sul az jobb, ha a vekotr�tv�l nev� f�ggv�ny csak �tv�ltja  a vektotr �s nem csin�l m�st
		Vektor pozicio = sebessegvektor.addVektor2(poz);		
		Mezo mezo_c = Tarolo.getMezo(vektorAtvalt(pozicio));
		
		this.mezo.setRobot(null);
		
//		setMezo(mezo_c);
//		mezo=mezo_c;
		
		if(mezo_c.getRobot()!=null && this.kiesett == false){
			Robotok r = mezo_c.getRobot();
			r.utkozes(this);
		}
		else if(this.kiesett == false)
			setMezo(mezo_c);
		
		if(mezo_c.getAkadaly()!=null){
			this.setFoltonvan(true);
			mezo_c.getAkadaly().setTakaritjak(true);
		}
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
		if(mezo!=null)
			mezo.setRobot(this);
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
	 * Nagy Robot - Kis Robot �tk�z�se
	 * Egy robot r�ugrik a kisrobotra.
	 * A kisrobot megsemmis�l �s olajfoltot hagy maga ut�n.
	 * @param r - A nagy robot, ami megsemmis�ti a kicsit
	 */
	public int utkozes(Robot r) {
		this.setKiesett(true);	//Szeg�ny kisrobotnak annyi
		this.getMezo().setAkadaly(new Olajfolt());	//A hely�re olajfolt ker�l
		r.setMezo(mezo);	//A robot megkapja az �j mez�t
		r.setOlajonvan(true);	//Viszont olajfoltra ker�lt �gy
		this.setMezo(null);
		return 0; //nincs haszna, csak az�rt kell mert a Robotok utkozes(Robot r) met�dus�t implement�lja
	}

	
	/**
	 * A kisrobot ha tal�l egy foltot, azt takar�tja
	 * 
	 */
	public void takarit() {
		if(mezo.getAkadaly().getElet()==0){	//Ha a foltnak 0 az �lete
				mezo.setAkadaly(null);		//Akkor elt�nik
				this.setFoltonvan(false);	//�s �gy a kisrobot sincs m�r folton
		}else 
			mezo.getAkadaly().eletcsokkent();//Ellenben takar�tja
	}
	
	public void kiirstat(int i, PrintWriter w) { // m�g nem teljesen m�k�dik, a mez�nek null a poz�ci�vektora
		String pozX = "null";
		String pozY = "null";
		if (!kiesett){
			pozX=""+vektorAtvalt(mezo.getPoziciovektor())[0];
			pozY=""+vektorAtvalt(mezo.getPoziciovektor())[1];
		}
		System.out.print("kisrobot "+i+" "+pozX+" "+pozY+" "+foltonvan+" "+kiesett);
		w.print("kisrobot "+i+" "+pozX+" "+pozY+" "+foltonvan+" "+kiesett);
	}

}