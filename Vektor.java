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
	 * A HIVO VEKTORHOZ ADJA HOZZA a masik Vektor koordinatait.
	 * @param v - Masik vektor.
	 */
	public void addVektor(Vektor v){
		this.x += v.getX();
		this.y += v.getY();
	}
	
	/**
	 * Kivonja a hívó vektorból a kapott vektort és visszaadja a különbségét
	 * @param v - másik vektor
	 */
	public Vektor subVektor(Vektor v){
		return new Vektor(this.x-v.getX(), this.y-v.getY()); 
	}
	
	
	/**
	 * Egy UJ VEKTORT HOZZ LETRE. A hivo vektor koordinatait hozza adja a másikéhoz.
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
	 * Egy skalárral osztja a koordinatakat. 
	 * Egesz osztast vegezz! (/) Nem kerekit!
	 * @param skalar - A szam, mivel oszt.
	 */
	public void skalarOszt(int skalar){
		this.x /= skalar;
		this.y /= skalar;
	}
	
	/**
	 * A vektor es egy masik atlagat szamitja ki.
	 * @param v - Masik vektor.
	 */
	public void atlagol(Vektor v){
		this.setVektor(new Vektor((x+v.getX())/2,(y+v.getY())/2));
	}
	
	/**
	 * Megadja a vektor hosszat.
	 * @return - Vektor hossza. DOUBLE!!!!
	 */
	public double hossz(){
		return Math.sqrt(x*x + y*y);
	}
	
	/**
	 * A vektort elosztja 10-zel, majd ha valamelyik paros
	 * hozzaad 1-et. Vegul megszorozza 10-zel.
	 */
	public void kerekit(){
		this.skalarOszt(10);
		if(x%2==0)
			x++;
		if(y%2==0)
			y++;
		this.skalarSzoroz(10);
	}
	
	/**
	 * A kerekitett koordinatakat elosztja tizzel,
	 * (n-1)/2 kepletet alkalmazza rajtuk.
	 * @return - Ket elemu index tomb.
	 */
	public int[] indexSzamol(){
		this.skalarOszt(10);
		this.x = (x-1)/2;
		this.y = (y-1)/2;
		return new int[] {x,y};
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
	
	public void vektorKiir(){
		System.out.print(""+x+" "+y);
	}

}