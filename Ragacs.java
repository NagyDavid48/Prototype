package Prototype;

public class Ragacs extends Akadaly {

	private int elet;
	private boolean takaritjak;

	//Fuck the police! (and contructors!)
	{this.elet = 4; this.takaritjak = false;}
	
	/**
	 * 
	 * @param r
	 */
	public void viselkedes(Robot r) {
		
	}

	public void eletcsokkent() {
		this.elet--;
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
	public void oregit() {
		// TODO Auto-generated method stub
		
	}

}
