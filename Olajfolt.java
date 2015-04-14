package Prototype;

public class Olajfolt extends Akadaly {

	private int elet;
	private boolean takaritjak;
	
	//Fuck the police! (and contructors!)
	{this.elet = 4; this.takaritjak = false;}
	
	
	/**
	 * Az olajfolt nem teszi lehetove, hogy a robot a kovetkezo korben sebesseget valtson.
	 * @param r - Erre a robotra hat.
	 */
	public void viselkedes(Robot r) {
		r.setOlajonvan(true);
	}

	public void eletcsokkent() {
		this.elet--;
		//this.elet = this.getElet() - 1;//Ha mar van hasznaljuk is! :D
	}

	public int getElet() {
		return this.elet;
	}

	public boolean getTakaritjak() {
		return this.takaritjak;
	}

	/**
	 * Ha egy kisrobot ugrik az olajra, akkor ezt takaritjak es
	 * koronkent csokken az elete.
	 * Ha leugrik onnan, vagy elpusztul, akkor mar nem takaritjak ezt a foltot.
	 * @param takaritjak - Beallitja azt, hogy takaritjak-e.
	 */
	public void setTakaritjak(boolean takaritjak) {
		this.takaritjak = takaritjak;
	}
	
	/**
	 * Ez minek?
	 */
	public void oregit() {
		// TODO - implement Olajfolt.oregit
		throw new UnsupportedOperationException();
	}

}
