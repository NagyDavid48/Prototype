package Prototype;

import java.util.ArrayList;
import java.util.Random;

public class Palya {

	private int soronlevo;
	private ArrayList<Robot> robotok;
	private ArrayList<KisRobot> kisrobotok;
	
	private Tarolo t; //Asszociációs kapcsolatból
	
	//egyszerûbb randomgeneráláshoz tagváltozók
	private int szelesseg;	
	private int magassag;

	/**
	 * 
	 * @param szelesseg
	 * @param magassag
	 * @param robotszam
	 * @param olaj
	 * @param ragacs
	 */
	public Palya(int szelesseg, int magassag, int robotszam, int olaj, int ragacs) {
		this.magassag = magassag;
		this.szelesseg = szelesseg;
		soronlevo = 0;
		
		//hogyan állítsuk be a mezõk specialításait akadály, robot, cp, szakadek 
		t = new Tarolo(szelesseg, magassag);
		
		//robot melyik mezõre kerüljön, mi alapján válasszunk random normál üres mezõ?
		for(int i = 0; i<robotszam; i++)
			robotok.add(new Robot(olaj,ragacs));
	}

	/**
	 * 
	 * @param v
	 */
	//elvileg kész
	public void vektorFeldolgoz(Vektor v) {
		robotLeptet(robotok.get(soronlevo),v);
		if(soronlevo++ == robotok.size()){
			int i = 0;
			while(i<kisrobotok.size())
			{
				kisrobotLeptet(kisrobotok.get(i),v);
			}
		}	
	}

	/**
	 * 
	 * @param r
	 * @param v
	 */
	//elvileg kész
	public void robotLeptet(Robot r, Vektor v) {
		if(!r.getKiesett())
			r.lep(v);
		soronlevo++;
	}

	public void kisrobotLetrehoz() {
		Random rand = new Random();
		int [] koordinata = new int[2];
		Mezo[][] m = t.getMezok();
		Vektor v = new Vektor();		//át kell még írni nincs meg a konstruktor hozzá
		
		int i = 0;
		while (i<5){
			int sor = rand.nextInt(magassag);
			int oszlop = rand.nextInt(szelesseg);
			koordinata[0]=sor;
			koordinata[1]=oszlop;
			if(m[sor][oszlop].getPalyaszakasz() == true && m[sor][oszlop].getRobot() == null)
			{
				kisrobotok.add(new KisRobot());
				kisrobotok.get(i).setMezo(m[sor][oszlop]);
				kisrobotok.get(i).setSebessegvektor(v);		//lehet át lesz adva kisrobot konstruktoába
				i++;
			}
		}
	}

	/**
	 * 
	 * @param r
	 * @param v
	 */
	public void kisrobotLeptet(KisRobot r, Vektor v) { // kell a vektor paraméter, egyáltalán kell a függvény 
		r.lep(v);									
	}

	//lehet itt is át kell írni hogy a teszteset müködjön
	public void cpKioszt() {
		Random rand = new Random();
		int [] koordinata = new int[2];
		Mezo[][] m = t.getMezok();
		
		int i = 0;
		while (i<3){
			int sor = rand.nextInt(magassag);
			int oszlop = rand.nextInt(szelesseg);
			koordinata[0]=sor;
			koordinata[1]=oszlop;
			if(m[sor][oszlop].getPalyaszakasz() == true && m[sor][oszlop].getCheckpoint() == false && m[sor][oszlop].getRobot() == null)
			{
				m[sor][oszlop].setCheckpoint(true);
				i++;
			}
		}
	}

	/**
	 * 
	 * @param r
	 */
	//kész
	public void olajLerak(Robot r) {
		r.olajLerak();
	}

	/**
	 * 
	 * @param r
	 */
	//kész
	public void ragacsLerak(Robot r) {
		r.ragacsLerak();
	}

	public void gyoztesValaszt() {
		int i = 1;
		Robot nyertes = robotok.get(0);
		while(i<robotok.size())
		{
			if(robotok.get(i).getCheckpoint() > nyertes.getCheckpoint())
				nyertes = robotok.get(i);
		}
		// ide gondolom jönne még egy kiirás lehet kellene visszatérési érték a protohoz
	}

	//elvileg kész
	public void oregit() {
		Mezo[][] m = t.getMezok();
		for(int i = 0; i<magassag; i++)
			for(int j = 0; j<szelesseg; j++)
				if(m[i][j].getAkadaly() != null)
					m[i][j].getAkadaly().oregit();
	}

}