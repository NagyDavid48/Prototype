package Prototype;

//dgavsfvausvfabshufasuchbaiycbiudasbucbuahfvuzqeavufzsaiuhfiuasuzcauzfghasdiahsiucuazgfuzqagsiuf

public class Tarolo {

	private Mezo[][] mezok;

	/**
	 * 
	 * @param szelesseg
	 * @param magassag
	 */
	public Tarolo(int szelesseg, int magassag) {
		Mezo[][] tmp = new Mezo [magassag][szelesseg];
		
		for(int i = 0 ; i<magassag; i++)
			for(int j = 0 ; j<szelesseg; j++){
				tmp[i][j] = new Mezo(false, false, null, null, null);	
			}
		
		mezok = tmp;
	}

	public Mezo[][] getMezok() {
		return this.mezok;
	}

	/**
	 * 
	 * @param koordinata
	 */
	public Mezo getMezo(int[] koordinata) {
		return mezok[koordinata[0]][koordinata[1]];
	}
	
	public void probafuggveny(){
	// git tesztelésére
	}
	

}
