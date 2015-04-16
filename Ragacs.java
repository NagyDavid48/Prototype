package Prototype;

public class Ragacs extends Akadaly {
	
	private int elet = 4;
	private boolean takaritjak = false;
	
	/**
	 * A robot sebess�ge megfelez�dik.
	 * @param r - Ez l�pett a ragacsba.
	 */
	public void viselkedes(Robot r) {
		this.oregit();
		r.ragacsraLepett();
	}

	public int getElet() {
		return this.elet;
	}

	public boolean getTakaritjak() {
		return this.takaritjak;
	}

	/**
	 * Kisrobot takar�thatja ezt az akad�lyt �s amikor r�l�p, akkor h�v�dik.
	 * @param takaritjak - Takar�tj�k-e.
	 */
	public void setTakaritjak(boolean takaritjak) {
		this.takaritjak = takaritjak;
	}
	
	/**
	 * A ragacs �let�t cs�kkenti eggyel.
	 */
	@Override
	public void eletcsokkent() {
		if(this.elet>0)
			this.elet--;
	}

	/**
	 * PM h�vja.
	 */
	@Override
	public void oregit() {}

}
