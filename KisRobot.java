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
		//Ha már meghalt szegény akkor ne is lépjen
		if (this.kiesett == true){
			return;
		}
		
		this.setSebessegvektor(v);
		Vektor poz = mezo.getPoziciovektor();
		//az alábbi mûveletet kiszedtem a vektorátváltból, mert így tudom kiíratni, hogy a kisrobot melyik mezõn van
		//ráadásul az jobb, ha a vekotrátvál nevû függvény csak átváltja  a vektotr és nem csinál mást
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
	 * Nagy Robot - Kis Robot ütközése
	 * Egy robot ráugrik a kisrobotra.
	 * A kisrobot megsemmisül és olajfoltot hagy maga után.
	 * @param r - A nagy robot, ami megsemmisíti a kicsit
	 */
	public int utkozes(Robot r) {
		this.setKiesett(true);	//Szegény kisrobotnak annyi
		this.getMezo().setAkadaly(new Olajfolt());	//A helyére olajfolt kerül
		r.setMezo(mezo);	//A robot megkapja az új mezõt
		r.setOlajonvan(true);	//Viszont olajfoltra került így
		this.setMezo(null);
		return 0; //nincs haszna, csak azért kell mert a Robotok utkozes(Robot r) metódusát implementálja
	}

	
	/**
	 * A kisrobot ha talál egy foltot, azt takarítja
	 * 
	 */
	public void takarit() {
		if(mezo.getAkadaly().getElet()==0){	//Ha a foltnak 0 az élete
				mezo.setAkadaly(null);		//Akkor eltûnik
				this.setFoltonvan(false);	//És így a kisrobot sincs már folton
		}else 
			mezo.getAkadaly().eletcsokkent();//Ellenben takarítja
	}
	
	public void kiirstat(int i, PrintWriter w) { // még nem teljesen mûködik, a mezõnek null a pozícióvektora
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