package Prototype;

import java.io.PrintWriter;

public class Ragacs extends Akadaly {
	
	public Ragacs(){
		this.elet = 4;
		this.takaritjak = false;
	}
	
	/**
	 * A robot sebessége megfelezõdik.
	 * @param r - Ez lépett a ragacsba.
	 */
	public void viselkedes(Robot r) {
		this.eletcsokkent();
		r.ragacsraLepett();
	}

	/**
	 * PM hívja.
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
