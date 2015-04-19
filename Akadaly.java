package Prototype;

import java.io.PrintWriter;

public abstract class Akadaly {
	
	protected int elet;//Akadaly elete
	protected boolean takaritjak;//Takaritjak-e
	
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
	

	/**
	 * Az akadaly eletet adja vissza.
	 * @return - Akadaly elete.
	 */
	public int getElet() {
		return this.elet;
	}
	
	/**
	 * Takaritjak-e a foltot. Ha egy kis robot egy akadalyra ugrik, akkor igen.
	 * Egyebkent nem.
	 * @return - Takaritjak-e.
	 */
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
	
	public abstract void kiirstat(PrintWriter w);
}
