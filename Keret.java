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
		String[] line;
		PrintWriter writer;
		if (args.length == 1)
			infile = args[0];
		try{
			reader = new BufferedReader(new FileReader(infile));
		} catch(Exception e){
			reader = new BufferedReader(new InputStreamReader(System.in));
		}
		try{
			writer=new PrintWriter(new FileWriter(outfile));
			while(cmd.isEnded != true && (line = cmd.readCommand(reader))!=null)                   // amíg meg nem állt a program
				cmd.fun(line, writer);              // olvassuk a parancsokat
			reader.close();
			writer.close();
		}catch(Exception e){/*System.out.println(e.getMessage())*/e.printStackTrace();}

	}
	
	
}