package serveur;

import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InitialiseArchitecture 
{
	
	
	public static Map<String, Serveur> initConfiguration(String fichier) throws RemoteException, MalformedURLException, NotBoundException 
	{
		
		try {
			Scanner sc = new Scanner(new File(fichier));
			String s = "";
			String[] liste;
			String url = ""; 
			String init;
			Serveur serv; 
			Map<String, Serveur> list = new HashMap<String, Serveur>();
			s = sc.nextLine();
			String p = s.split(":")[1];
			liste = p.split("\\|");
			init = sc.nextLine().split(":")[1];
			for ( int i = 0; i < liste.length; i++)
			{
				url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/" +  liste[i];
				serv = (Serveur) Naming.lookup(url);
				serv.forgiveConfig();
				serv.setHost(liste[i]);
				if ( liste[i].equals(init))
				{
					serv.setFatherHost(init);
				}
				list.put(liste[i], serv);
			}	
			list.put("init", list.get(init));
			while ( sc.hasNextLine())
			{
				liste = sc.nextLine().split(":");
				serv = list.get(liste[0]);
				if ( liste.length > 1)
				{
					for ( int i = 1 ; i < liste.length; i++)
					{
						serv.addSon(list.get(liste[i]));
					}
				}			
			}
			liste = null;
			return list;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
