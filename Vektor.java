package Prototype;

public class Vektor {
	private int x, y;//Ket koordinata.
	
	/**
	 * Null vektort allitja elo.
	 * (0;0) lesz ezutan a konstruktor hivas utan.
	 */
	public Vektor(){
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Megadott parameterekkel keszit egy vektort.
	 * (X;Y) lesz az eredmeny.
	 * @param X - x koordinata.
	 * @param Y - y koordinata.
	 */
	public Vektor(int X, int Y){
		this.x = X;
		this.y = Y;
	}
	
	/**
	 * A HIVO VEKTORHOZ ADDJA HOZZA a masik Vektor koordinatait.
	 * @param v - Masik vektor.
	 */
	public void addVektor(Vektor v){
		this.x += v.getX();
		this.y += v.getY();
	}
	
	/**
	 * Egy UJ VEKTORT HOZZ LETRE. A hivo vektor koordinatait hozza adja a m�sik�hoz.
	 * @param w - Masik vektor
	 * @return - Az uj vektor a masik ketto osszegevel.
	 */
	public Vektor addVektor2(Vektor w){
		return new Vektor(this.x + w.getX(), this.y + w.getY());
	}
	
	/**
	 * Egy mar letezo vektort allit be.
	 * @param v - Mar letezo vektor.
	 */
	public void setVektor(Vektor v){
		this.x = v.getX();
		this.y = v.getY();
	}
	
	/**
	 * Egy szammal szorozza a koordinatakat.
	 * @param skalar - Ezzel szorozza oket.
	 */
	public void skalarSzoroz(int skalar){
		this.x *= skalar;
		this.y *= skalar;
	}
	
	/**
	 * Egy skal�rral osztja a koordinatakat. 
	 * Egesz osztast vegezz! (/)
	 * @param skalar - A szam, mivel oszt.
	 */
	public void skalarOszt(int skalar){
		this.x /= skalar;
		this.y /= skalar;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

}