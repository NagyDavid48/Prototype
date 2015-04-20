package Prototype;

public class Palya_Menedzser {

	private int kor = 10;//Ennyi a max korok szama
	private int olajkeszlet = 0;//Ennyi olaja lesz egy robotnak
	private int ragacskeszlet = 0;//Ennyi ragacsa lesz egy robotnak
	public Palya palya;//A keret el tudja hívni a pálya fv-it.
	private int robotszam = 0;//Ennyi robot lesz a pályán
	private int robocntr=0; //Ennyi robot lepett a körben
	//private int[] cntr;//Ez számolja hanyszor volt cp kioszt vagy hoztak letre robotot
	

	private boolean isVege = false;

	/**
	 * PM Letrehozasa.
	 * @param szelesseg - Ekkora legyen a palya szelessege.
	 * @param magassag - Ekkora legyen a palya magassaga.
	 * @param robotszam - Ennyi jatekos legyen.
	 * @param kor - Ennyi korig menjen a jatek.
	 * @param ragacs - Ennyi ragacsa legyen a robotoknak.
	 * @param olaj - Ennyi olaja legyen a robotoknak.
	 */
	public Palya_Menedzser(int magassag, int szelesseg, int robotszam, int kor, int ragacs, int olaj) {
		this.kor = kor;
		this.ragacskeszlet = ragacs;
		this.olajkeszlet = olaj;
		this.robotszam = robotszam;
		this.robocntr = 0;
		//this.cntr = new int[]{0, 0};//0. a cp. 1. kisrobot
		this.palya = palyaLetreHoz(magassag, szelesseg);
	}
	
	

	public Palya_Menedzser(){}
	
	
	public Palya palyaLetreHoz(int magassag, int szelesseg){
		return new Palya(magassag, szelesseg,robotszam, olajkeszlet, ragacskeszlet);
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
		if(!isVege){//Ha nincs vege a jateknak.
			if (robotszam != 0)
				robocntr++;
			if (robocntr == robotszam) // ha minden robot lépett
			{
				kor--;
//				for(int i = 0; i<cntr.length; ++i)
//					cntr[i]++;
				if(kor>0){//Megy a jatek
//					if(cntr[0] == 4){//Minden negyedik kor vegen kiosztjuk a cp-ket.
//						palya.cpKioszt();
//						cntr[0] = 0;
//				}
//					if(cntr[1] == 5){//Lasd szekvencia
//						palya.kisrobotLetrehoz();//Release the MiniRobots!
//						cntr[1] = 0;
//				}
					palya.oregit();//Olaj szaradasa
				}else{//Itt van vege a jateknak.
					palya.gyoztesValaszt();
					isVege = true;
				}
			}
		}
//		palya.oregit();
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

	/**
	 * Ennyi robot lesz a pályán
	 * @param robotszam - Robot szam.
	 */
	public void setRobotszam(int robotszam) {
		this.robotszam = robotszam;
	}
	
	/**
	 * Vissza adja a jatekban resztvevo robotok szamat.
	 * Kis robotok szamat nem adja vissza.
	 * @return - Robotok szama
	 */
	public int getRobotszam() {
		return robotszam;
	}
}