package Prototype;

import java.io.PrintWriter;

public abstract class Robotok {
	protected Tarolo t; //Asszociáció miatt
	
	/**
	 * A robotokat lepteti.
	 * Minden fajta robot mashogy lep.
	 * @param v - Uj sebesseg vektor.
	 */
	public abstract void lep(Vektor v);

	/**
	 * A robotok vektorát mezõindexekké konvertálja
	 * Osztás 10-zel, majd a páros koordinátákhoz 1-et hozzáadunk, végül visszaszorozzuk 10-zel.
	 * Így már kerekítve van.
	 * Mezõ indexhez osztjuk 10-zel, kivonunk 1-et, majd osztunk 2-vel.
	 * @param v - Az új sebességvektor
	 */
	public int[] vektorAtvalt(Vektor v){
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
	 * Utkozes kezelesere.
	 * @param r - Ugro robot.
	 */
	public abstract int utkozes(Robot r);


	/**
	 * Kis Robot utkozese.
	 * A kisrobot raugrik a masik robotra, ekkor vissza kerul az eredeti poziciojara.
	 * Lepattan a masikrol.
	 * @param r - Kis robot, ami raugrik a masikra.
	 */
	public void utkozes(KisRobot r){
		r.lep(new Vektor());
	}
	
	/**
	 * Adatok kiíratására
	 * @param i - robot sorszáma
	 */
	public abstract void kiirstat(int i, PrintWriter w);

}