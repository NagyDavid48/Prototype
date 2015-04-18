package Prototype;

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
		
		///Ide kéne a magic algortimus
		
		
		
		/////////////////////////////////
		sebessegvektor.addVektor(v);
		Vektor poz = mezo.getPoziciovektor();
		Mezo mezo_c = t.getMezo(vektorAtvalt(poz));
		
		if(mezo_c.getPalyaszakasz()==true && mezo_c.getAkadaly()==null && mezo_c.getRobot()==null){
			
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
		Vektor pozicio = sebessegvektor.addVektor2(v);
		pozicio.skalarOszt(10);
		if(pozicio.getX()%2==0)
			pozicio.setX(pozicio.getX()+1);
		if(pozicio.getY()%2==0)
			pozicio.setY(pozicio.getY()+1);
		pozicio.skalarSzoroz(10);
		
		int[] tmp = new int[2];
		tmp[0] = ((pozicio.getX()/10)-1)/2;
		tmp[1] = ((pozicio.getY()/10)-1)/2;
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

}