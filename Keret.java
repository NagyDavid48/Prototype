package Prototype;


public class Keret {
	public static void main(String [] args)
	{
		Command cmd = new Command();
		try{
			while(cmd.isEnded != true)                   // am�g meg nem �llt a program
				cmd.fun(cmd.readCommand());              // olvassuk a parancsokat
		}catch(Exception e){System.out.println(e.getMessage());}

	}
	
	
}