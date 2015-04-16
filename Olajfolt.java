package Prototype;

public class Olajfolt extends Akadaly {
	
	private int elet = 4;
	private boolean takaritjak = false;
	
	/**
	 * Az olajfolt nem teszi lehetove, hogy a robot a kovetkezo korben sebesseget valtson.
	 * @param r - Erre a robotra hat.
	 */
	public void viselkedes(Robot r) {
		r.setOlajonvan(true);//Innen a robot vegzi a sebesseg varialast.
	}

	/**
	 * Vissza adja az olaj eletet.
	 * @return - Az olajfolt elete.
	 */
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
	 * Meghivja az elet csokkent fv-t.
	 * PM hivja minden kor vegen.
	 */
	@Override
	public void oregit() {
		this.eletcsokkent();
		
	}

	/**
	 * Ez csokkenti az akadaly eleteteggyel.
	 * Az oregit fv hivja meg.
	 */
	@Override
	public void eletcsokkent() {
		if(this.elet>0)//Tudom, hogy mashol is ellenorizzuk, hogy elerte-e a nullat, de nem bizom a veletlenre, es ez nem art semmit.
			this.elet--;
	}

}
