package Prototype;

import java.io.PrintWriter;

public class KisRobot extends Robotok {

	private Vektor sebessegvektor;
	private Mezo mezo;
	private boolean foltonvan;
	private boolean kiesett;

	public KisRobot() {
		sebessegvektor = new Vektor(10, 10);
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
		// TODO - implement KisRobot.lep
		this.setSebessegvektor(v);
		Vektor poz = mezo.getPoziciovektor();
		Vektor pozicio = sebessegvektor.addVektor2(poz);		
		Mezo mezo_c = t.getMezo(vektorAtvalt(pozicio));
		
		if(mezo_c.getPalyaszakasz()==true && mezo_c.getAkadaly()==null && mezo_c.getRobot()==null){
			
		}
		

		//az alábbi mûveletet kiszedtem a vektorátváltból, mert így tudom kiíratni, hogy a kisrobot melyik mezõn van
		//ráadásul az jobb, ha a vekotrátvál nevû függvény csak átváltja  a vektotr és nem csinál mást
		
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
	 * 
	 * @param v
	 */
	public int[] vektorAtvalt(Vektor v) {
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
	public void utkozes(KisRobot r) {
		// TODO - implement KisRobot.utkozes
		throw new UnsupportedOperationException();
	}

	public void takarit() {
		if(mezo.getAkadaly().getElet()==0){
				mezo.setAkadaly(null);
				this.setFoltonvan(false);
		}else 
			mezo.getAkadaly().eletcsokkent();
	}
	
	public void kiirstat(int i, PrintWriter w) { // még nem teljesen mûködik, a mezõnek null a pozícióvektora
		int pozX=vektorAtvalt(mezo.getPoziciovektor())[0];
		int pozY=vektorAtvalt(mezo.getPoziciovektor())[1];
		System.out.print("kisrobot "+i+" "+pozX+" "+pozY+" "+foltonvan+" "+kiesett);
		w.print("kisrobot "+i+" "+pozX+" "+pozY+" "+foltonvan+" "+kiesett);
	}

}