package Prototype;

public class Tarolo {

	public static Mezo[][] mezok;

	/**
	 * 
	 * @param szelesseg
	 * @param magassag
	 */
	public Tarolo(int magassag, int szelesseg) {
		Mezo[][] tmp = new Mezo [magassag][szelesseg];
		
		for(int i = 0 ; i<magassag; i++)
			for(int j = 0 ; j<szelesseg; j++){
				int koordX = (i*2+1)*10;															// ha 20 széles a mezõ
				int koordY = (j*2+1)*10;
				tmp[i][j] = new Mezo(false, true, null, null, new Vektor(koordX, koordY));	
			}
		
		mezok = tmp;
	}

	public static Mezo[][] getMezok() {
		return mezok;
	}

	/**
	 * 
	 * @param koordinata
	 */
	public static Mezo getMezo(int[] koordinata) {
		return mezok[koordinata[0]][koordinata[1]];
	}
		

}
