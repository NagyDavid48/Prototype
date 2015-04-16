package Prototype;

public class Palya_Menedzser {

	private int kor;//Ennyi a max korok szama
	private int olajkeszlet;//Ennyi olaja lesz egy robotnak
	private int ragacskeszlet;//Ennyi ragacsa lesz egy robotnak
	private Palya palya;//Ez a játék tér

	/**
	 * PM Letrehozasa.
	 * @param szelesseg - Ekkora legyen a palya szelessege.
	 * @param magassag - Ekkora legyen a palya magassaga.
	 * @param robotszam - Ennyi jatekos legyen.
	 * @param kor - Ennyi korig menjen a jatek.
	 * @param ragacs - Ennyi ragacsa legyen a robotoknak.
	 * @param olaj - Ennyi olaja legyen a robotoknak.
	 */
	public Palya_Menedzser(int szelesseg, int magassag, int robotszam, int kor, int ragacs, int olaj) {
		this.kor = kor;
		this.ragacskeszlet = ragacs;
		this.olajkeszlet = olaj;
		this.palya = new Palya(szelesseg, magassag, robotszam, olaj, ragacs);
	}
	
	//Valahogy meg kell oldani az inputok feldolgozasat. - Megbeszeles.
	
	/**
	 * Szamolja a koroket, ha letelt vege a jateknak. Egy kor akkor telik le, ha minden robot lepett egyet.
	 * Ha neggyel oszthato, akkor kiosztja a CP-ket.
	 * Minden kor vegen oregiti az olajfoltokat.
	 * Ha 0 vagy kissebb a korszam, akkor kivalasztja a gyoztest.
	 * Minden esetben a palya egy fv-jét hívja meg.
	 */
	public void korSzamol() {
		kor--;
		if(kor>0){//Megy a jatek
			if(kor % 4 == 0)//Minden negyedik kor vegen kiosztjuk a cp-ket.
				palya.cpKioszt();
			palya.oregit();//Olaj szaradasa
		}else{//Itt van vege a jateknak.
			palya.gyoztesValaszt();
		}
	}

	/**
	 * Vissza adja a korok szamat.
	 * @return - Ennyi kor van meg.
	 */
	public int getKor() {
		return this.kor;
	}

	/**
	 *  Beallit egy kor hatart.
	 * @param kor - Eddig mehet.
	 */
	public void setKor(int kor) {
		this.kor = kor;
	}

	/**
	 * Ennyi olaja lesz a robotnak.
	 * @param olajkeszlet - Olaj szam.
	 */
	public void setOlajkeszlet(int olajkeszlet) {
		this.olajkeszlet = olajkeszlet;
	}

	/**
	 * Ennyi ragacsa lesz a robotnak.
	 * @param ragacskeszlet - Ragacs szam.
	 */
	public void setRagacskeszlet(int ragacskeszlet) {
		this.ragacskeszlet = ragacskeszlet;
	}

}