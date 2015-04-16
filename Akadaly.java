package Prototype;

public abstract class Akadaly {
	
	/**
	 * Robotra hato negativ hatast ezzel lehet kivaltani (pl.: megcsuszik, lelassul, stb)
	 * Minden tipusu akadalyban felul kell definialni.
	 * @param r - Robot, amire hat az akadaly.
	 */
	public abstract void viselkedes(Robot r);

	/**
	 * Az elet attr.-ot csokkenti.
	 */
	public abstract void eletcsokkent();

	/**
	 * Ezt a fvt hivja a PM.
	 */
	public abstract void oregit();
}
