package Prototype;

public class Olajfolt extends Akadaly {
	
	public Olajfolt(){
		this.elet = 4;
		this.takaritjak = false;
	}
	
	/**
	 * Az olajfolt nem teszi lehetove, hogy a robot a kovetkezo korben sebesseget modositson.
	 * @param r - Erre a robotra hat.
	 */
	public void viselkedes(Robot r) {
		r.setOlajonvan(true);//Innen a robot vegzi a sebesseg varialast.
	}

	/**
	 * Meghivja az elet csokkent fv-t.
	 * PM hivja minden kor vegen.
	 */
	@Override
	public void oregit() {
		this.eletcsokkent();
		
	}
	
	public void kiirstat() {
		System.out.print("olaj "+elet+" "+takaritjak+" ");
	}
}
