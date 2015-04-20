package Prototype;

import java.util.ArrayList;
import java.util.Random;

public class Palya {

	private int soronlevo;					//gy�ztesv�laszt�sn�l lehet kelleni fog neki getter setter
	public ArrayList<Robot> robotok;
	public ArrayList<KisRobot> kisrobotok;
	
	public Tarolo t; //Asszoci�ci�s kapcsolatb�l
	
	//egyszer�bb randomgener�l�shoz tagv�ltoz�k
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
		
		//hogyan �ll�tsuk be a mez�k special�t�sait akad�ly, robot, cp, szakadek 
		//Egyel�re meredt �gy hogy sima mez�ket hoz l�tre nincs rajta semmi �s nem szakad�k
		t = new Tarolo(magassag, szelesseg);
		
		//robot melyik mez�re ker�lj�n, mi alapj�n v�lasszunk random norm�l �res mez�?
		//Egyel�re val�sz�n�leg a keretprogram fogja megmondani
		for(int i = 0; i<robotszam; i++)
			robotok.add(new Robot(olaj,ragacs));
	}

	/**
	 * 
	 * @param v
	 */
	//elvileg k�sz
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
					/*Ide k�ne az akad�lytal�l� magic algoritmus*/
					if (!kisrobotok.get(i).getKiesett())
						kisrobotLeptet(kisrobotok.get(i),foltKeres(kisrobotok.get(i)));
					i++;
				}
			}
		}
	}
	//Megkeresi, hogy hol van a p�ly�n a kisrobothoz legk�zelebbi folt
	private Vektor foltKeres(KisRobot r){
		Vektor kozeli = new Vektor(10000, 10000);
		Vektor mostani = r.getMezo().getPoziciovektor();
		
		//Megn�zz�k merre vannak foltok, kiv�lasztjuk a legk�zelebbit
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
		
		//Ez indexekben hogy n�z ki?
		int[] idx = r.vektorAtvalt(kozeli);
		int[] most = r.vektorAtvalt(mostani);
		
		//idx[0]-->f�gg�legesen mennyit kell menni a legk�zelebbi foltig
		//idx[1]-->v�zszintesen mennyit kell menni a legk�zelebbi foltig
		
		//el�bb f�gg�legesen ameddig tud
		//Ha j� sorban vagyunk vagy nem tudunk f�gg�legesen menni, induljunk el v�zszintesen
		//lefel� kell?
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
		
		//vagy felfel�?
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
		
		//balra t�n?
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
	//elvileg k�sz
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
				kisrobotok.add(new KisRobot());		//kisrobot konstrukto�ba l�trehozza mag�nak a vektor�t
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

	//lehet itt is �t kell �rni hogy a teszteset m�k�dj�n
	//most �gy van meg�rva hogy ahol robot van ode nem ker�lhet akad�ly ker�lhessen a robot al� is ? :D
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
	//k�sz
	public void olajLerak(Robot r) {
		r.olajLerak();
		soronlevo++;
	}

	/**
	 * 
	 * @param r
	 */
	//k�sz
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
		// ide gondolom j�nne m�g egy kiir�s lehet kellene visszat�r�si �rt�k a protohoz
		return nyero;
		
	}

	/**
	 * Az olajfoltot oregitjuk ezzel. Minden korben eggyel csokken az olajfolt elete, ha nincs rajta kis robot, akkor kettovel.
	 */
	public void oregit() {
		Mezo[][] m = t.getMezok();//Lek�rdezz�k a vilagot
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
								kisrobotok.get(k).setFoltonvan(false);//Akkor ezt megsz�ntetjuk
							k++;
						}
					}
				}//Ha nincs, akkor nem csinalunk semmit.
			}
		}
	}
}