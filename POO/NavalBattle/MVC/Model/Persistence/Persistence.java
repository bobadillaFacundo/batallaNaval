package NavalBattle.MVC.Model.Persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Persistence implements Serializable{

	private static final long serialVersionUID = 3648839823854088052L;
	private String nameFile;	
	
	public Persistence(String nameFile) {
		this.nameFile = nameFile;
	}
	
	public String read() throws RemoteException, FileNotFoundException, IOException{
		 String cadena;
		 String x="";
		 FileReader f = new FileReader(nameFile);
		 BufferedReader b = new BufferedReader(f);
		 while((cadena = b.readLine())!=null) {
		  x=x+cadena+"\n";
		 }
		  b.close();
		  return x;
	}

	public void write (String playerName)throws IOException  {
		 FileWriter f = new FileWriter(nameFile);
		 BufferedWriter b = new BufferedWriter(f);
		 b.write(playerName);
		 b.close();
	}
		
	
}
