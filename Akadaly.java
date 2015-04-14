package Class_Diagram;

public abstract class Akadaly {

	/**
	 * Ezt a robotot kapja meg és erre hat az akadály.
	 * Minden tipusú akadályban felül kell definiálni.
	 * @param r - Robot
	 */
	public abstract void viselkedés(Robot r);

	public abstract void eletcsokkent();

	public abstract void oregit();

	// comment git change
}
