package Prototype;

public class Ragacs extends Akadaly {
	
	private int elet = 4;
	private boolean takaritjak = false;
	
	/**
	 * A robot sebessége megfelezõdik.
	 * @param r - Ez lépett a ragacsba.
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
	 * Kisrobot takaríthatja ezt az akadályt és amikor rálép, akkor hívódik.
	 * @param takaritjak - Takarítják-e.
	 */
	public void setTakaritjak(boolean takaritjak) {
		this.takaritjak = takaritjak;
	}
	
	/**
	 * A ragacs életét csökkenti eggyel.
	 */
	@Override
	public void eletcsokkent() {
		if(this.elet>0)
			this.elet--;
	}

	/**
	 * PM hívja.
	 */
	@Override
	public void oregit() {}

}
