package Prototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Keret {
	public static void main(String [] args)
	{
		Command cmd = new Command();
		String infile = null;
		String outfile = "output.txt";
		String expected = null;
		BufferedReader reader;
		PrintWriter writer;
		boolean testmode = false;
		if (args.length > 0){
			infile = args[0];
			expected = args[1];
		}
		try{
			reader = new BufferedReader(new FileReader(infile));		// ha volt argumentum, onnan olvasunk
			testmode = true;
		} catch(Exception e){
			reader = new BufferedReader(new InputStreamReader(System.in)); // ha nem létezik a bemeneti fájl, vagy nincs argumentum, konzolról olvasunk
		}
		try{
			writer=new PrintWriter(new FileWriter(outfile));
			while(cmd.isEnded != true)                   				// amíg meg nem állt a program
				cmd.fun(cmd.readCommand(reader), writer);               // olvassuk a parancsokat
			reader.close();
			if (testmode)
				writer.println("TESZT VEGE");
			writer.close();
			if (testmode){
				reader = new BufferedReader(new FileReader(outfile));
				ArrayList<String> outlines=new ArrayList<String>();
				ArrayList<String> expectedlines=new ArrayList<String>();
				String line;
				while(!(line=reader.readLine()).equals("TESZT VEGE"))
					outlines.add(line);
				reader.close();
				reader = new BufferedReader(new FileReader(expected));
				while(!(line=reader.readLine()).equals("TESZT VEGE"))
					expectedlines.add(line);
				reader.close();
				if (expectedlines.equals(outlines))
					System.out.println("TESZT SIKERES");
			}
		}catch(Exception e){e.printStackTrace();}

	}
	
	
}