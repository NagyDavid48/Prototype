package Prototype;

public abstract class Akadaly {
	
	protected int elet;
	protected boolean takaritjak;
	
	/**
	 * Robotra hato negativ hatast ezzel lehet kivaltani (pl.: megcsuszik, lelassul, stb)
	 * Minden tipusu akadalyban felul kell definialni.
	 * @param r - Robot, amire hat az akadaly.
	 */
	public abstract void viselkedes(Robot r);

	/**
	 * Az elet attr.-ot csokkenti.
	 */
	protected void eletcsokkent(){
		if(this.elet>0)
			this.elet--;
	}

	/**
	 * Ezt a fvt hivja a PM.
	 */
	public abstract void oregit();
	

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
}
