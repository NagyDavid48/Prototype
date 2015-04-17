package Prototype;


public class Keret {
	public static void main(String [] args)
	{
		Command cmd = new Command();
		try{
			while(cmd.isEnded != true)
				cmd.fun(cmd.readCommand());
		}catch(Exception e){System.out.println(e.getMessage());}
	}
	
	
}