package Prototype;

public class Ragacs extends Akadaly {
	
	private int elet = 4;
	private boolean takaritjak = false;
	
	/**
	 * 
	 * @param r
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
	 * 
	 * @param takaritjak
	 */
	public void setTakaritjak(boolean takaritjak) {
		this.takaritjak = takaritjak;
	}

	@Override
	public void eletcsokkent() {
		if(this.elet>0)
			this.elet--;
	}

	@Override
	public void oregit() {
		this.eletcsokkent();
	}

}
