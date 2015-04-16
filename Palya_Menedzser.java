package Prototype;

public class Palya_Menedzser {

	private int kor;
	private int olajkeszlet;
	private int ragacskeszlet;
	private int eddigiKorok;
	private Palya palya;

	/**
	 * PM Letrehozasa.
	 * @param kor - Ennyi korig menjen a jatek.
	 * @param ragacs - Ennyi ragacsa legyen a robotoknak.
	 * @param olaj - Ennyi olaja legyen a robotoknak.
	 */
	public Palya_Menedzser(int kor, int ragacs, int olaj) {
		this.kor = kor;
		this.ragacskeszlet = ragacs;
		this.olajkeszlet = olaj;
		//this.palya = new Palya(10, 10, 2, olaj, ragacs);
	}

	public void korSzamol() {
		// TODO - implement Palya_Menedzser.korSzamol
		throw new UnsupportedOperationException();
	}

	/**
	 * Vissza adja a max korok szamat.
	 * @return - Meddig mehet a jatek.
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
	 * @param olajkeszlet - Olaj szám.
	 */
	public void setOlajkeszlet(int olajkeszlet) {
		this.olajkeszlet = olajkeszlet;
	}

	/**
	 * Ennyi ragacsa leszz a robotnak.
	 * @param ragacskeszlet - Ragacs szám.
	 */
	public void setRagacskeszlet(int ragacskeszlet) {
		this.ragacskeszlet = ragacskeszlet;
	}

}