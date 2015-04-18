package Prototype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Command {
	Palya_Menedzser pm;
	protected boolean isEnded = false;                                   // ezt állítjuk be, ha megállt a program
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public String[] readCommand() throws IOException {
		System.out.print("> ");
		return reader.readLine().split(" ");                             // space-ek mentén daraboljuk a beírt parancsot
	}
	/**
	 * parancsok
	 * @param cmd - a beírt string sor tömbbe rendezve
	 */
	protected void fun(String[] cmd) {
		if (cmd[0].equals("uj_jatek")) // új játékot kezd
			uj_jatek(cmd);
		else if (cmd[0].equals("rag_be")) // beállítja a robotok ragacskészletét
			rag_be(cmd);
		else if (cmd[0].equals("olj_be")) // beállítja a robotok olajkészletét
			olj_be(cmd);
		else if (cmd[0].equals("kor_be")) // beállítja a körszámot
			kor_be(cmd);
		else if (cmd[0].equals("kor_lep")) // eltelik ennyi kör
			kor_lep(cmd);
		else if (cmd[0].equals("robo_be")) // robotok számát beállítja
			robo_be(cmd);
		else if (cmd[0].equals("lep")) // robotot léptet
			lep(cmd);
		else if (cmd[0].equals("robo_le")) // robotot lerak
			robo_le(cmd);
		else if (cmd[0].equals("robo_vek")) // robot vektorát beállítja
			robo_vek(cmd);
		else if (cmd[0].equals("robo_olr")) // robot olajat rak le
			robo_olr(cmd);
		else if (cmd[0].equals("robo_rlr")) // robot ragacsot rak le
			robo_rlr(cmd);
		else if (cmd[0].equals("krl")) // kisrobot lerakása a pályára
			krl(cmd);
		else if (cmd[0].equals("olr")) // olajat rak le a pályára
			olr(cmd);
		else if (cmd[0].equals("rlr")) // ragacsot rak le a pályára
			rlr(cmd);
		else if (cmd[0].equals("cp")) // checkpointnak állít be mezõt
			cp(cmd);
		else if (cmd[0].equals("palya")) // létrehozza a pályát
			palya(cmd);
		else if (cmd[0].equals("szakadek_be")) // szakadékot állít be
			szakadek_be(cmd);
		else if (cmd[0].equals("KiirRobot")) // kiírja a robotok adatait
			kiirrobot(cmd);
		else if (cmd[0].equals("KiirKisRobot")) // kiírja a kisrobotok adatait
			kiirkisrobot(cmd);
		else if (cmd[0].equals("KiirPalya")) // kiírja a pálya adatait
			kiirpalya(cmd);
		else if (cmd[0].equals("KiirMezo")) // kiírja a mezõ adatait
			kiirmezo(cmd);
		else if (cmd[0].equals("KiirAllas")) //kiírja az állást
			kiirallas(cmd);
		else if (cmd[0].equals("Kilepes")) // kilép a programból
			exit(cmd);
		else System.out.println("ervenytelen parancs");
		
	}
	
	private void kiirallas(String[] cmd) {
		System.out.print(pm.getKor()+" "); // hátralevõ körök
		for (int i = 0; i < pm.palya.robotok.size(); i++)
			System.out.print(pm.palya.robotok.get(i).getCheckpoint()+" "); // robotok checkpontjai
		System.out.println(pm.palya.gyoztesValaszt()); // aktuális gyõztes
	}

	private void kiirmezo(String[] cmd) {
		if (cmd.length == 3){
			int[] koord = new int[2];				//
			try {
				koord[0] = Integer.parseInt(cmd[1]);	//
				koord[1] = Integer.parseInt(cmd[2]);	//
				Mezo m = pm.palya.t.getMezo(koord);		// a megadott koordinátából meghatározzuk a mezõt
				System.out.print(koord[0]+" "+koord[1]+" "+m.getCheckpoint()+" "); //kiírjuk a koordinátát + hogy checkpoint-e
				if (m.getAkadaly()!=null)
					m.getAkadaly().kiirstat();			// akadály (ha van) adatainak kiírása
				if (m.getRobot()!=null){
					int index=-1;
					for (int n = 0;n < pm.palya.robotok.size(); n++)
						if(pm.palya.robotok.get(n).equals(m.getRobot()))
							index = n;
					for (int n = 0;n < pm.palya.kisrobotok.size(); n++)
						if(pm.palya.kisrobotok.get(n).equals(m.getRobot()))
							index = n;
					m.getRobot().kiirstat(index);		// robot (ha van) index megkeresése és adatok kiírása
				}
				System.out.println();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: KiirMezo <y> <x>");
	}

	private void kiirkisrobot(String[] cmd) { // minden kisrobotra kiírjuk az adatot
		ArrayList<KisRobot> kisrobotok = pm.palya.kisrobotok;
		for(int i=0;i<kisrobotok.size();i++){
			kisrobotok.get(i).kiirstat(i);
			System.out.println();
		}
	}

	private void kiirrobot(String[] cmd) { // minden robotra kiírjuk az adatot
		ArrayList<Robot> robotok = pm.palya.robotok;
		for(int i=0;i<robotok.size();i++){
			robotok.get(i).kiirstat(i);
			System.out.println();
		}
	}

	private void szakadek_be(String[] cmd) { // paraméterbõl megkeressük a mezõt, beállítjuk szakadéknak
		if (cmd.length == 3){
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]);
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				m.setPalyaszakasz(false);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: szakadek_be <y> <x>");
	}

	private void cp(String[] cmd) { // paraméterbõl megkeressük a mezõt, beállítjuk checkpointnak
		if (cmd.length == 3){
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]);
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				m.setCheckpoint(true);
//				m.setPoziciovektor(new Vektor(0,20)); tesztelésre, amíg a mezõnek nem lesz saját
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: cp <y> <x>");
	}

	private void rlr(String[] cmd) { // paraméterbõl megkeressük a mezõt, ragacsot rakunk rá
		if (cmd.length == 3){
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]);
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				m.setAkadaly(new Ragacs());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: rlr <y> <x>");
	}

	private void palya(String[] cmd) { // létrehozunk egy paramétereknek megfelelõ pályát
		if (cmd.length == 3)
			try {
				pm.palya = pm.palyaLetreHoz(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else System.out.println("Használat: palya <szelesseg> <magassag>");
	}

	private void kiirpalya(String[] cmd) { // kiírjuk az összes mezõ adatait
		Mezo[][] palya = pm.palya.t.getMezok();
		int i = 0;
		int j = 0;
		for (i = 0; i<palya.length; i++){         // végigiterálunk a mezõkön
			for(j =0; j<palya[0].length;j++){
				System.out.print(i+" "+j+" ");
				if (palya[i][j].getPalyaszakasz()==false) // ha szakadék, kiírjuk
					System.out.print("szakadek ");
				if (palya[i][j].getCheckpoint()==true) // ha checkpoint, kiírjuk (fontos! szakadék ne legyen checkpoint!)
					System.out.print("checkpoint ");
				if (palya[i][j].getAkadaly()!=null)   // ha van akadály, kiírjuk az adatait
					palya[i][j].getAkadaly().kiirstat();
				if (palya[i][j].getRobot()!=null){        // ha van robot, megkeressük az indexét (a kiirstathoz), és kiírjuk
					int index=-1;
					for (int n = 0;n < pm.palya.robotok.size(); n++)
						if(pm.palya.robotok.get(n).equals(palya[i][j].getRobot())) // elõször a robotok között keressük
							index = n;
					for (int n = 0;n < pm.palya.kisrobotok.size(); n++)
						if(pm.palya.kisrobotok.get(n).equals(palya[i][j].getRobot())) // aztán a kisrobotok között
							index = n;
					palya[i][j].getRobot().kiirstat(index);
				}
				System.out.println();
			}
		}
	}

	private void olr(String[] cmd) { // paraméterbõl megkeressük a mezõt, olajat rakunk rá
		if (cmd.length == 3){
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]);
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				m.setAkadaly(new Olajfolt());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: olr <y> <x>");
	}

	private void krl(String[] cmd) {
		if (cmd.length == 3){
			KisRobot r = new KisRobot();  // új kisrobotot hozunk létre
			pm.palya.kisrobotok.add(r);
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]); // paraméterbõl megkeressük a mezõt
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				if (m.getRobot() == null){           // kisrobot mezõjét beállítjuk
					r.setMezo(m);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: krl <y> <x>");
	}

	private void robo_rlr(String[] cmd) {     // a megadott robotnak meghívjuk a ragacslerak függvényét
		if (cmd.length == 3){
			try {
				int index = Integer.parseInt(cmd[1]);
//				int elet = Integer.parseInt(cmd[2]);
				pm.palya.robotok.get(index).ragacsLerak();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: robo_rlr <index>");
	}

	private void robo_olr(String[] cmd) {    // a megadott robotnak meghívjuk az olajlerak függvényét
		if (cmd.length == 3){
			try {
				int index = Integer.parseInt(cmd[1]);
//				int elet = Integer.parseInt(cmd[2]);
				pm.palya.robotok.get(index).olajLerak();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: robo_olr <index>");
	}

	private void robo_vek(String[] cmd) {    // a megadott robotnak paraméterek szerinti sebességvektort állítunk be
		if (cmd.length == 4){
			try {
				int index = Integer.parseInt(cmd[1]);
				Vektor v = new Vektor(Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
				pm.palya.robotok.get(index).setSebessegvektor(v);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: robo_vek <index> <y> <x>");
	}

	private void robo_le(String[] cmd) {    // a megadott robotot lerakjuk a paraméter mezõre
		if (cmd.length == 4){
			try {
				int[] koord = new int[2];
				Robot r = pm.palya.robotok.get(Integer.parseInt(cmd[1]));
				koord[0] = Integer.parseInt(cmd[2]);
				koord[1] = Integer.parseInt(cmd[3]);
				Mezo m = pm.palya.t.getMezo(koord);  // paraméter alapján megkeressük a mezõt
				r.setMezo(m);                        // rátesszük a robotot
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: robo_le <index> <y> <x>");
	}
	
	private void lep(String[] cmd) {  // megadott robotot léptetjük megadott vektorral (hozzáadódik a megadott vektor a robot sebességektorához)
		if (cmd.length == 4){
			try {
				int index = Integer.parseInt(cmd[1]);
				Vektor v = new Vektor(Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
				pm.palya.robotLeptet(pm.palya.robotok.get(index), v);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else System.out.println("Használat: lep <index> <y> <x>");
	}
	
	private void robo_be(String[] cmd) {            // játék indításakor beállítjuk a játékosok számát
		if (cmd.length == 2)
			try {
				pm.setRobotszam(Integer.parseInt(cmd[1]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else System.out.println("Használat: robo_be <robotszam>");
	}
	
	private void kor_lep(String[] cmd) {           // ennyi kör eltelik a játékban
		if (cmd.length == 2){
			int hanykor = 0;
			try {
				hanykor = Integer.parseInt(cmd[1]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int vege = hanykor*pm.getRobotszam();      // ahány lépés kell: megadott szám szorozva a robotok számával,
			Vektor uresvektor = new Vektor();          //    ekkor minden robot a megadott számszor lép,
			for (int i=0;i<vege;i++){                  //    tehát ennyi kör eltelik
				pm.palya.vektorFeldolgoz(uresvektor);
				pm.korSzamol();
			}
		}else System.out.println("Használat: kor_lep <szam>");
	}
	private void kor_be(String[] cmd) {       // játék indításakor beállítjuk a körök számát
		if (cmd.length == 2)
			try {
				pm.setKor(Integer.parseInt(cmd[1]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else System.out.println("Használat: kor_be <körszám>");
	}
	private void olj_be(String[] cmd) {      // játék indításakor beállítjuk a robotok olajkészletét
		if (cmd.length == 2)
			try {
				pm.setOlajkeszlet(Integer.parseInt(cmd[1]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else System.out.println("Használat: olj_be <olajszám>");
	}
	private void rag_be(String[] cmd) {       // játék indításakor beállítjuk a robotok ragacskészletét
		if (cmd.length == 2)
			try {
				pm.setRagacskeszlet(Integer.parseInt(cmd[1]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else System.out.println("Használat: rag_be <ragacsszám>");
	}
	private void uj_jatek(String[] cmd) {     // új, üres pályamenedzsert indítunk
		pm = new Palya_Menedzser();
	}
	private void exit(String[] cmd) {         // kilépünk a programból
		isEnded = true;
	}
}