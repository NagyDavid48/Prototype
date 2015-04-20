package Prototype;

import java.util.ArrayList;
import java.util.Random;

public class Palya {

	private int soronlevo;					//gyõztesválasztásnál lehet kelleni fog neki getter setter
	public ArrayList<Robot> robotok;
	public ArrayList<KisRobot> kisrobotok;
	
	public Tarolo t; //Asszociációs kapcsolatból
	
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
	public Palya(int magassag, int szelesseg, int robotszam, int olaj, int ragacs) {
		this.robotok = new ArrayList<Robot>();
		this.kisrobotok = new ArrayList<KisRobot>();
		this.magassag = magassag;
		this.szelesseg = szelesseg;
		this.soronlevo = 0;
		
		//hogyan állítsuk be a mezõk specialításait akadály, robot, cp, szakadek 
		//Egyelõre meredt úgy hogy sima mezõket hoz létre nincs rajta semmi és nem szakadék
		t = new Tarolo(magassag, szelesseg);
		
		//robot melyik mezõre kerüljön, mi alapján válasszunk random normál üres mezõ?
		//Egyelõre valószínûleg a keretprogram fogja megmondani
		for(int i = 0; i<robotszam; i++)
			robotok.add(new Robot(olaj,ragacs));
	}

	/**
	 * 
	 * @param v
	 */
	//elvileg kész
	public void vektorFeldolgoz(Vektor v) {
		if(soronlevo >= robotok.size())
			soronlevo = 0;
		
		if (robotok.size() > 0)
			robotLeptet(robotok.get(soronlevo),v);	
		
		if(soronlevo == robotok.size()){
			soronlevo = 0;
			if (kisrobotok.size() != 0){
				int i = 0;
				while(i<kisrobotok.size())
				{
					/*Ide kéne az akadálytaláló magic algoritmus*/
					if (!kisrobotok.get(i).getKiesett())
						kisrobotLeptet(kisrobotok.get(i),foltKeres(kisrobotok.get(i)));
					i++;
				}
			}
		}
	}
	//Megkeresi, hogy hol van a pályán a kisrobothoz legközelebbi folt
	private Vektor foltKeres(KisRobot r){
		Vektor kozeli = new Vektor(10000, 10000);
		Vektor mostani = r.getMezo().getPoziciovektor();
		
		//Megnézzük merre vannak foltok, kiválasztjuk a legközelebbit
		for(int i=0; i < magassag; i++){
			for(int j = 0; j < szelesseg; j++){
				if(t.mezok[i][j].getAkadaly()!=null && t.mezok[i][j].getAkadaly().getTakaritjak()==false){
					Vektor tmp = t.mezok[i][j].getPoziciovektor().subVektor(mostani);
					if(tmp.hossz()<kozeli.hossz()){
						kozeli = tmp;
					}
				}
			}
		}
		
		if(kozeli.getX()==10000){
			kozeli.setX(0);
			kozeli.setY(0);
		}
		
		//Ez indexekben hogy néz ki?
		int[] idx = r.vektorAtvalt(kozeli);
		int[] most = r.vektorAtvalt(mostani);
		
		//idx[0]-->függõlegesen mennyit kell menni a legközelebbi foltig
		//idx[1]-->vízszintesen mennyit kell menni a legközelebbi foltig
		
		//elõbb függõlegesen ameddig tud
		//Ha jó sorban vagyunk vagy nem tudunk függõlegesen menni, induljunk el vízszintesen
		//lefelé kell?
		if(idx[0]<0){
			if(t.mezok[most[0]-1][most[1]].getPalyaszakasz()==true)
				return new Vektor(-20, 0);
			else{
				if(most[1]==0)
					return new Vektor(0, 20);
				if(most[1]==szelesseg-1)
					return new Vektor(0, -20);
				int jobb=0;
				int bal=0;
				while(most[1]+jobb < szelesseg && t.mezok[most[0]][most[1]+jobb].getPalyaszakasz()==true && t.mezok[most[0]-1][most[1]+jobb].getPalyaszakasz()!=true)
					jobb++;
				while(most[1]-bal >=0 && t.mezok[most[0]][most[1]-bal].getPalyaszakasz()!=true && t.mezok[most[0]-1][most[1]-bal].getPalyaszakasz()!=true)
					bal++;
				if(bal<jobb){
					return new Vektor(0, -20);
				}else return new Vektor(0, 20);
			}
			/*if(most[1]>=0 && idx[1]<0 && t.mezok[most[0]][most[1]-1].getPalyaszakasz()==true)
				return new Vektor(0, -20);
			if(most[1]<szelesseg && idx[1]>0 && t.mezok[most[0]][most[1]-1].getPalyaszakasz()==true)
				return new Vektor(0, 20);*/
			
		}
		
		//vagy felfelé?
		else if(idx[0]>0){
			if(t.mezok[most[0]+1][most[1]].getPalyaszakasz()==true)
				return new Vektor(20, 0);
			else{
				if(most[1]==0)
					return new Vektor(0, 20);
				if(most[1]==szelesseg-1)
					return new Vektor(0, -20);
				int jobb=0;
				int bal=0;
				while(most[1]+jobb < szelesseg && t.mezok[most[0]][most[1]+jobb].getPalyaszakasz()==true && t.mezok[most[0]+1][most[1]+jobb].getPalyaszakasz()!=true)
					jobb++;
				while(most[1]-bal >=0 && t.mezok[most[0]][most[1]-bal].getPalyaszakasz()!=true && t.mezok[most[0]+1][most[1]-bal].getPalyaszakasz()!=true)
					bal++;
				if(bal<jobb){
					return new Vektor(0, -20);
				}else return new Vektor(0, 20);
			}
			/*if(most[1]>0 && t.mezok[most[0]][most[1]-1].getPalyaszakasz()==true)
				return new Vektor(0, -20);
			if(most[1]<szelesseg-1 && t.mezok[most[0]][most[1]-1].getPalyaszakasz()==true)
				return new Vektor(0, 20);*/
		}
		
		//balra tán?
		else if(idx[1]<0){
			if(t.mezok[most[0]][most[1]-1].getPalyaszakasz()==true)
				return new Vektor(0, -20);
			
			/*if(most[0]>=0 && t.mezok[most[0]][most[1]-1].getPalyaszakasz()==true)
				return new Vektor(-20, 0);
			if(most[0]<magassag && t.mezok[most[0]][most[1]-1].getPalyaszakasz()==true)
				return new Vektor(20, 0);*/
		}
		
		//marad a jobbra
		else if(idx[1]>0){
			if(t.mezok[most[0]][most[1]+1].getPalyaszakasz()==true)
				return new Vektor(0, 20);
			/*if(most[0]>=0 && t.mezok[most[0]][most[1]-1].getPalyaszakasz()==true)
				return new Vektor(-20, 0);
			if(most[0]<magassag && t.mezok[most[0]][most[1]-1].getPalyaszakasz()==true)
				return new Vektor(20, 0);*/
		}
		return new Vektor();
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
		
		int i = 0;
		while (i<5){
			int sor = rand.nextInt(magassag);
			int oszlop = rand.nextInt(szelesseg);
			koordinata[0]=sor;
			koordinata[1]=oszlop;
			if(m[sor][oszlop].getPalyaszakasz() == true && m[sor][oszlop].getRobot() == null)
			{
				kisrobotok.add(new KisRobot());		//kisrobot konstruktoába létrehozza magának a vektorát
				kisrobotok.get(i).setMezo(m[sor][oszlop]);
				i++;
			}
		}
	}

	/**
	 * 
	 * @param r
	 * @param v
	 */
	public void kisrobotLeptet(KisRobot r, Vektor v) { 
			if(r.getFoltonvan() == true)
				r.takarit();
			else
				r.lep(v);
	}

	//lehet itt is át kell írni hogy a teszteset müködjön
	//most úgy van megírva hogy ahol robot van ode nem kerülhet akadály kerülhessen a robot alá is ? :D
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
		soronlevo++;
	}

	/**
	 * 
	 * @param r
	 */
	//kész
	public void ragacsLerak(Robot r) {
		r.ragacsLerak();
		soronlevo++;
	}

	public int gyoztesValaszt() {
		int i = 1;
		int nyero = 0;
		Robot nyertes = robotok.get(nyero);
		while(i<robotok.size())
		{
			if(robotok.get(i).getCheckpoint() > nyertes.getCheckpoint()){
				nyertes = robotok.get(i);
				nyero = i;
			}
			i++;
		}
		// ide gondolom jönne még egy kiirás lehet kellene visszatérési érték a protohoz
		return nyero;
		
	}

	/**
	 * Az olajfoltot oregitjuk ezzel. Minden korben eggyel csokken az olajfolt elete, ha nincs rajta kis robot, akkor kettovel.
	 */
	public void oregit() {
		Mezo[][] m = t.getMezok();//Lekérdezzük a vilagot
		int elet;
		int k = 0;
		for(int i = 0; i<magassag; i++){//Hosszaban
			for(int j = 0; j<szelesseg; j++){//Szelteben
				if(m[i][j].getAkadaly() != null){//Ha van akadaly az adott mezon
					m[i][j].getAkadaly().oregit();//Szimplan oregitjuk
					elet = m[i][j].getAkadaly().getElet();
					if(elet==0){//Ha vege van
						m[i][j].setAkadaly(null);//Akkor eltuntejuk
						while (k<kisrobotok.size()){//Vegig megyunk a kisrobotok tombjen
							if(m[i][j] == kisrobotok.get(k).getMezo())//Ha az akadaly helyen van kisrobot
								kisrobotok.get(k).setFoltonvan(false);//Akkor ezt megszüntetjuk
							k++;
						}
					}
				}//Ha nincs, akkor nem csinalunk semmit.
			}
		}
	}
}