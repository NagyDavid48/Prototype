package Prototype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {
	Palya_Menedzser pm;
	protected boolean isEnded = false;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public String[] readCommand() throws IOException {
		return reader.readLine().split(" ");
	}
	
	protected void fun(String[] cmd) {
		if (cmd[0].equals("uj_jatek")) // új játékot kezd
			uj_jatek(cmd);
		if (cmd[0].equals("rag_be")) // beállítja a robotok ragacskészletét
			rag_be(cmd);
		if (cmd[0].equals("olj_be")) // beállítja a robotok olajkészletét
			olj_be(cmd);
		if (cmd[0].equals("kor_be")) // beállítja a körszámot
			kor_be(cmd);
		if (cmd[0].equals("kor_lep")) // TODO
			kor_lep(cmd);
		if (cmd[0].equals("robo_be")) // robotok számát beállítja
			robo_be(cmd);
		if (cmd[0].equals("lep")) // robotot léptet
			lep(cmd);
		if (cmd[0].equals("robo_le")) // robotot lerak
			robo_le(cmd);
		if (cmd[0].equals("robo_vek")) // robot vektorát beállítja
			robo_vek(cmd);
		if (cmd[0].equals("robo_olr")) // robot olajat rak le
			robo_olr(cmd);
		if (cmd[0].equals("robo_rlr")) // robot ragacsot rak le
			robo_rlr(cmd);
		if (cmd[0].equals("krl"))
			exit(cmd);
		if (cmd[0].equals("olr"))
			exit(cmd);
		if (cmd[0].equals("rlr"))
			exit(cmd);
		if (cmd[0].equals("cp"))
			exit(cmd);
		if (cmd[0].equals("palya"))
			exit(cmd);
		if (cmd[0].equals("szakadek_be"))
			exit(cmd);
		if (cmd[0].equals("KiirRobot"))
			exit(cmd);
		if (cmd[0].equals("KiirKisRobot"))
			exit(cmd);
		if (cmd[0].equals("KiirPalya"))
			exit(cmd);
		if (cmd[0].equals("KiirMezo"))
			exit(cmd);
		if (cmd[0].equals("KiirAllas"))
			exit(cmd);
		if (cmd[0].equals("Kilepes"))
			exit(cmd);
		
	}
	
	private void robo_rlr(String[] cmd) {
		// TODO Auto-generated method stub
		int index = Integer.parseInt(cmd[1]);
//		int elet = Integer.parseInt(cmd[2]);
		pm.palya.robotok.get(index).ragacsLerak();;
	}

	private void robo_olr(String[] cmd) {
		// TODO Auto-generated method stub
		int index = Integer.parseInt(cmd[1]);
//		int elet = Integer.parseInt(cmd[2]);
		pm.palya.robotok.get(index).olajLerak();
	}

	private void robo_vek(String[] cmd) {
		// TODO Auto-generated method stub
		int index = Integer.parseInt(cmd[1]);
		Vektor v = new Vektor(Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
		pm.palya.robotok.get(index).setSebessegvektor(v);
	}

	private void robo_le(String[] cmd) {
		// TODO Auto-generated method stub
		int[] koord = new int[2];
		Robot r = pm.palya.robotok.get(Integer.parseInt(cmd[1]));
		koord[0] = Integer.parseInt(cmd[2]);
		koord[1] = Integer.parseInt(cmd[3]);
		Mezo m = pm.palya.t.getMezo(koord);
		m.setRobot(r);
		r.setMezo(m);
	}
	private void lep(String[] cmd) {
		// TODO Auto-generated method stub
		pm.palya.robotLeptet(pm.palya.robotok.get(Integer.parseInt(cmd[1])), new Vektor(Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3])));
	}
	private void robo_be(String[] cmd) {
		// TODO Auto-generated method stub
		pm.setRobotszam(Integer.parseInt(cmd[1]));
	}
	private void kor_lep(String[] cmd) {
		// TODO Auto-generated method stub
		
	}
	private void kor_be(String[] cmd) {
		// TODO Auto-generated method stub
		pm.setKor(Integer.parseInt(cmd[1]));
	}
	private void olj_be(String[] cmd) {
		// TODO Auto-generated method stub
		pm.setOlajkeszlet(Integer.parseInt(cmd[1]));
	}
	private void rag_be(String[] cmd) {
		// TODO Auto-generated method stub
		pm.setRagacskeszlet(Integer.parseInt(cmd[1]));
	}
	private void uj_jatek(String[] cmd) {
		// TODO Auto-generated method stub
		pm = new Palya_Menedzser();
	}
	private void exit(String[] cmd) {
		// TODO Auto-generated method stub
		isEnded = true;
	}
}