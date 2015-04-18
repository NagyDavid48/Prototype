package Prototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Keret {
	public static void main(String [] args)
	{
		Command cmd = new Command();
		String infile = null;
		String outfile = "output.txt";
		BufferedReader reader;
		PrintWriter writer;
		if (args.length == 1)
			infile = args[0];
		try{
			reader = new BufferedReader(new FileReader(infile));		// ha volt argumentum, onnan olvasunk
		} catch(Exception e){
			reader = new BufferedReader(new InputStreamReader(System.in)); // ha nem l�tezik a bemeneti f�jl, vagy nincs argumentum, konzolr�l olvasunk
		}
		try{
			writer=new PrintWriter(new FileWriter(outfile));
			while(cmd.isEnded != true)                   				// am�g meg nem �llt a program
				cmd.fun(cmd.readCommand(reader), writer);               // olvassuk a parancsokat
			reader.close();
			writer.close();
		}catch(Exception e){e.printStackTrace();}

	}
	
	
}