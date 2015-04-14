package Prototype;

public abstract class Akadaly {

	/**
	 * Ezt a robotot kapja meg es erre hat az akadaly.
	 * Minden tipusu akadalyban felul kell definialni.
	 * @param r - Robot, amire hat az akadaly.
	 */
	public abstract void viselkedes(Robot r);

	public abstract void eletcsokkent();

	public abstract void oregit();

	// comment git change
}
