package Prototype;

import java.io.PrintWriter;

public class Ragacs extends Akadaly {
	
	public Ragacs(){
		this.elet = 4;
		this.takaritjak = false;
	}
	
	/**
	 * A robot sebess�ge megfelez�dik.
	 * @param r - Ez l�pett a ragacsba.
	 */
	public void viselkedes(Robot r) {
		this.eletcsokkent();
		r.ragacsraLepett();
	}

	/**
	 * PM h�vja.
	 */
	@Override
	public void oregit() {
		//Ido nem hat ra!
	}
	
	public void kiirstat(PrintWriter w) {
		System.out.print("ragacs "+elet+" "+takaritjak+" ");
		w.print("ragacs "+elet+" "+takaritjak+" ");
	}

}
