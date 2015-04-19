package Prototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Command {
	Palya_Menedzser pm;
	protected boolean isEnded = false;                                   // ezt �ll�tjuk be, ha meg�llt a program
//	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public String[] readCommand(BufferedReader reader) throws IOException {
		return reader.readLine().split(" ");                             // space-ek ment�n daraboljuk a be�rt parancsot
	}
	/**
	 * parancsok
	 * @param cmd - a be�rt string sor t�mbbe rendezve
	 */
	protected void fun(String[] cmd, PrintWriter writer) {
		if (cmd[0].equals("uj_jatek")) // �j j�t�kot kezd
			uj_jatek(cmd);
		else if (cmd[0].equals("rag_be")) // be�ll�tja a robotok ragacsk�szlet�t
			rag_be(cmd);
		else if (cmd[0].equals("olj_be")) // be�ll�tja a robotok olajk�szlet�t
			olj_be(cmd);
		else if (cmd[0].equals("kor_be")) // be�ll�tja a k�rsz�mot
			kor_be(cmd);
		else if (cmd[0].equals("kor_lep")) // eltelik ennyi k�r
			kor_lep(cmd);
		else if (cmd[0].equals("robo_be")) // robotok sz�m�t be�ll�tja
			robo_be(cmd);
		else if (cmd[0].equals("lep")) 	   // robotot l�ptet
			lep(cmd);
		else if (cmd[0].equals("robo_le")) // robotot lerak
			robo_le(cmd);
		else if (cmd[0].equals("robo_vek")) // robot vektor�t be�ll�tja
			robo_vek(cmd);
		else if (cmd[0].equals("robo_olr")) // robot olajat rak le
			robo_olr(cmd);
		else if (cmd[0].equals("robo_rlr")) // robot ragacsot rak le
			robo_rlr(cmd);
		else if (cmd[0].equals("krl")) // kisrobot lerak�sa a p�ly�ra
			krl(cmd);
		else if (cmd[0].equals("olr")) // olajat rak le a p�ly�ra
			olr(cmd);
		else if (cmd[0].equals("rlr")) // ragacsot rak le a p�ly�ra
			rlr(cmd);
		else if (cmd[0].equals("cp")) // checkpointnak �ll�t be mez�t
			cp(cmd);
		else if (cmd[0].equals("palya")) // l�trehozza a p�ly�t
			palya(cmd);
		else if (cmd[0].equals("szakadek_be")) // szakad�kot �ll�t be
			szakadek_be(cmd);
		else if (cmd[0].equals("KiirRobot")) // ki�rja a robotok adatait
			kiirrobot(cmd, writer);
		else if (cmd[0].equals("KiirKisRobot")) // ki�rja a kisrobotok adatait
			kiirkisrobot(cmd, writer);
		else if (cmd[0].equals("KiirPalya")) // ki�rja a p�lya adatait
			kiirpalya(cmd, writer);
		else if (cmd[0].equals("KiirMezo")) // ki�rja a mez� adatait
			kiirmezo(cmd, writer);
		else if (cmd[0].equals("KiirAllas")) //ki�rja az �ll�st
			kiirallas(cmd, writer);
		else if (cmd[0].equals("Kilepes")) // kil�p a programb�l
			exit(cmd);
		else System.out.println("�rv�nytelen parancs");
		
	}
	
	private void kiirallas(String[] cmd, PrintWriter w) {
		System.out.print(pm.getKor()+" "); // h�tralev� k�r�k
		for (int i = 0; i < pm.palya.robotok.size(); i++){
			System.out.print(pm.palya.robotok.get(i).getCheckpoint()+" ");				// robotok checkpontjai
			w.print(pm.palya.robotok.get(i).getCheckpoint()+" ");
		}
		System.out.println(pm.palya.gyoztesValaszt());                   				// aktu�lis gy�ztes
		w.println(pm.palya.gyoztesValaszt());
	}

	private void kiirmezo(String[] cmd, PrintWriter w) {
		if (cmd.length == 3){
			int[] koord = new int[2];				//
			try {
				koord[0] = Integer.parseInt(cmd[1]);	//
				koord[1] = Integer.parseInt(cmd[2]);	//
				Mezo m = pm.palya.t.getMezo(koord);		// a megadott koordin�t�b�l meghat�rozzuk a mez�t
				System.out.print(koord[0]+" "+koord[1]+" "+m.getCheckpoint()+" "); //ki�rjuk a koordin�t�t + hogy checkpoint-e
				w.print(koord[0]+" "+koord[1]+" "+m.getCheckpoint()+" ");
				if (m.getAkadaly()!=null)
					m.getAkadaly().kiirstat(w);			// akad�ly (ha van) adatainak ki�r�sa
				if (m.getRobot()!=null){
					int index=-1;
					for (int n = 0;n < pm.palya.robotok.size(); n++)
						if(pm.palya.robotok.get(n).equals(m.getRobot()))
							index = n;
					for (int n = 0;n < pm.palya.kisrobotok.size(); n++)
						if(pm.palya.kisrobotok.get(n).equals(m.getRobot()))
							index = n;
					m.getRobot().kiirstat(index, w);		// robot (ha van) index megkeres�se �s adatok ki�r�sa
				}
				System.out.println();
				w.println();
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: KiirMezo <y> <x>");
	}

	private void kiirkisrobot(String[] cmd, PrintWriter w) { // minden kisrobotra ki�rjuk az adatot
		ArrayList<KisRobot> kisrobotok = pm.palya.kisrobotok;
		for(int i=0;i<kisrobotok.size();i++){
			kisrobotok.get(i).kiirstat(i, w);
			System.out.println();
			w.println();
		}
	}

	private void kiirrobot(String[] cmd, PrintWriter w) { // minden robotra ki�rjuk az adatot
		ArrayList<Robot> robotok = pm.palya.robotok;
		for(int i=0;i<robotok.size();i++){
			robotok.get(i).kiirstat(i, w);
			System.out.println();
			w.println();
		}
	}

	private void szakadek_be(String[] cmd) { // param�terb�l megkeress�k a mez�t, be�ll�tjuk szakad�knak
		if (cmd.length == 3){
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]);
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				m.setPalyaszakasz(false);
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: szakadek_be <y> <x>");
	}

	private void cp(String[] cmd) { // param�terb�l megkeress�k a mez�t, be�ll�tjuk checkpointnak
		if (cmd.length == 3){
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]);
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				m.setCheckpoint(true);
//				m.setPoziciovektor(new Vektor(0,20)); tesztel�sre, am�g a mez�nek nem lesz saj�t
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: cp <y> <x>");
	}

	private void rlr(String[] cmd) { // param�terb�l megkeress�k a mez�t, ragacsot rakunk r�
		if (cmd.length == 3){
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]);
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				m.setAkadaly(new Ragacs());
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: rlr <y> <x>");
	}

	private void palya(String[] cmd) { // l�trehozunk egy param�tereknek megfelel� p�ly�t
		if (cmd.length == 3)
			try {
				pm.palya = pm.palyaLetreHoz(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		else System.out.println("Haszn�lat: palya <szelesseg> <magassag>");
	}

	private void kiirpalya(String[] cmd, PrintWriter w) { // ki�rjuk az �sszes mez� adatait
		Mezo[][] palya = pm.palya.t.getMezok();
		int i = 0;
		int j = 0;
		for (i = 0; i<palya.length; i++){         // v�gigiter�lunk a mez�k�n
			for(j =0; j<palya[0].length;j++){
				System.out.print(i+" "+j+" ");
				w.print(i+" "+j+" ");
				if (palya[i][j].getPalyaszakasz()==false){ // ha szakad�k, ki�rjuk
					System.out.print("szakadek ");
					w.print("szakadek ");
				}
				if (palya[i][j].getCheckpoint()==true){ // ha checkpoint, ki�rjuk (fontos! szakad�k ne legyen checkpoint!)
					System.out.print("checkpoint ");
					w.print("checkpoint ");
				}
				if (palya[i][j].getAkadaly()!=null){   // ha van akad�ly, ki�rjuk az adatait
					palya[i][j].getAkadaly().kiirstat(w);
				}
				if (palya[i][j].getRobot()!=null){        // ha van robot, megkeress�k az index�t (a kiirstathoz), �s ki�rjuk
					int index=-1;
					for (int n = 0;n < pm.palya.robotok.size(); n++)
						if(pm.palya.robotok.get(n).equals(palya[i][j].getRobot())) // el�sz�r a robotok k�z�tt keress�k
							index = n;
					for (int n = 0;n < pm.palya.kisrobotok.size(); n++)
						if(pm.palya.kisrobotok.get(n).equals(palya[i][j].getRobot())) // azt�n a kisrobotok k�z�tt
							index = n;
					palya[i][j].getRobot().kiirstat(index, w);
				}
				System.out.println();
				w.println();
			}
		}
	}

	private void olr(String[] cmd) { // param�terb�l megkeress�k a mez�t, olajat rakunk r�
		if (cmd.length == 3){
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]);
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				m.setAkadaly(new Olajfolt());
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: olr <y> <x>");
	}

	private void krl(String[] cmd) {
		if (cmd.length == 3){
			KisRobot r = new KisRobot();  // �j kisrobotot hozunk l�tre
			pm.palya.kisrobotok.add(r);
			int[] koord = new int[2];
			try {
				koord[0] = Integer.parseInt(cmd[1]); // param�terb�l megkeress�k a mez�t
				koord[1] = Integer.parseInt(cmd[2]);
				Mezo m = pm.palya.t.getMezo(koord);
				if (m.getRobot() == null){           // kisrobot mez�j�t be�ll�tjuk
					r.setMezo(m);
				}
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: krl <y> <x>");
	}

	private void robo_rlr(String[] cmd) {     // a megadott robotnak megh�vjuk a ragacslerak f�ggv�ny�t
		if (cmd.length == 2){
			try {
				int index = Integer.parseInt(cmd[1]);
//				int elet = Integer.parseInt(cmd[2]);
				pm.palya.robotok.get(index).ragacsLerak();
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: robo_rlr <index>");
	}

	private void robo_olr(String[] cmd) {    // a megadott robotnak megh�vjuk az olajlerak f�ggv�ny�t
		if (cmd.length == 2){
			try {
				int index = Integer.parseInt(cmd[1]);
//				int elet = Integer.parseInt(cmd[2]);
				pm.palya.robotok.get(index).olajLerak();
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: robo_olr <index>");
	}

	private void robo_vek(String[] cmd) {    // a megadott robotnak param�terek szerinti sebess�gvektort �ll�tunk be
		if (cmd.length == 4){
			try {
				int index = Integer.parseInt(cmd[1]);
				Vektor v = new Vektor(Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
				pm.palya.robotok.get(index).setSebessegvektor(v);
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: robo_vek <index> <y> <x>");
	}

	private void robo_le(String[] cmd) {    // a megadott robotot lerakjuk a param�ter mez�re
		if (cmd.length == 4){
			try {
				int[] koord = new int[2];
				Robot r = pm.palya.robotok.get(Integer.parseInt(cmd[1]));
				koord[0] = Integer.parseInt(cmd[2]);
				koord[1] = Integer.parseInt(cmd[3]);
				Mezo m = pm.palya.t.getMezo(koord);  // param�ter alapj�n megkeress�k a mez�t
				r.setMezo(m);                        // r�tessz�k a robotot
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			}
		}else System.out.println("Haszn�lat: robo_le <index> <y> <x>");
	}
	
	private void lep(String[] cmd) {  // megadott robotot l�ptetj�k megadott vektorral (hozz�ad�dik a megadott vektor a robot sebess�gektor�hoz)
		if (cmd.length == 4){
			int index = 0;
			try {
				index = Integer.parseInt(cmd[1]);
				Vektor v = new Vektor(Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
				pm.palya.robotLeptet(pm.palya.robotok.get(index), v);
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			} catch (NullPointerException e2){
				if (pm == null)
					System.out.println("Nem kezdt�l �j j�t�kot!");
				else if (pm.palya == null)
					System.out.println("Nincs m�g p�lya!");
				else if (pm.palya.robotok.get(index) == null)
					System.out.println("Nem el�rhet� a megadott robot!");
				else System.out.println("NullPointer hiba!");
			} catch (IndexOutOfBoundsException e){
				System.out.println("Nem el�rhet� a megadott robot!");
			}
		}else System.out.println("Haszn�lat: lep <index> <y> <x>");
	}
	
	private void robo_be(String[] cmd) {            // j�t�k ind�t�sakor be�ll�tjuk a j�t�kosok sz�m�t
		if (cmd.length == 2)
			try {
				pm.setRobotszam(Integer.parseInt(cmd[1]));
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			} catch (NullPointerException e2) {
				System.out.println("Nem kezdt�l �j j�t�kot!");
			}
		else System.out.println("Haszn�lat: robo_be <robotszam>");
	}
	
	private void kor_lep(String[] cmd) {           // ennyi k�r eltelik a j�t�kban
		if (cmd.length == 2){
			int hanykor = 0;
			try {
				hanykor = Integer.parseInt(cmd[1]);
				int vege = hanykor*pm.getRobotszam();      // ah�ny l�p�s kell: megadott sz�m szorozva a robotok sz�m�val,
				if (pm.getRobotszam()==0)
					vege = hanykor;
				Vektor uresvektor = new Vektor();          //    ekkor minden robot a megadott sz�mszor l�p,
				for (int i=0;i<vege;i++){                  //    teh�t ennyi k�r eltelik
					pm.palya.vektorFeldolgoz(uresvektor);
					pm.korSzamol();
				}
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			} catch (NullPointerException e2){
				e2.printStackTrace();
			}
		}else System.out.println("Haszn�lat: kor_lep <szam>");
	}
	private void kor_be(String[] cmd) {       // j�t�k ind�t�sakor be�ll�tjuk a k�r�k sz�m�t
		if (cmd.length == 2)
			try {
				pm.setKor(Integer.parseInt(cmd[1]));
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			} catch (NullPointerException e2) {
				System.out.println("Nem kezdt�l �j j�t�kot!");
			}
		else System.out.println("Haszn�lat: kor_be <k�rsz�m>");
	}
	private void olj_be(String[] cmd) {      // j�t�k ind�t�sakor be�ll�tjuk a robotok olajk�szlet�t
		if (cmd.length == 2)
			try {
				pm.setOlajkeszlet(Integer.parseInt(cmd[1]));
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			} catch (NullPointerException e2) {
				System.out.println("Nem kezdt�l �j j�t�kot!");
			}
		else System.out.println("Haszn�lat: olj_be <olajsz�m>");
	}
	private void rag_be(String[] cmd) {       // j�t�k ind�t�sakor be�ll�tjuk a robotok ragacsk�szlet�t
		if (cmd.length == 2)
			try {
				pm.setRagacskeszlet(Integer.parseInt(cmd[1]));
			} catch (NumberFormatException e) {
				System.out.println("Eg�sz sz�mot �rj param�ternek!");
			} catch (NullPointerException e2) {
				System.out.println("Nem kezdt�l �j j�t�kot!");
			}
		else System.out.println("Haszn�lat: rag_be <ragacssz�m>");
	}
	private void uj_jatek(String[] cmd) {     // �j, �res p�lyamenedzsert ind�tunk
		pm = new Palya_Menedzser();
	}
	private void exit(String[] cmd) {         // kil�p�nk a programb�l
		isEnded = true;
	}
}