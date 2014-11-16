package serveur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main 
{
	
	public static void main(String[] args) throws NotBoundException, IOException
	{
		if ( args.length != 1 )
		{
			System.out.println("Usage: Main config_file");
			System.exit(1);
		}
		else
		{
			System.out.println(args[0]);
			Map<String, Serveur> processList = new HashMap<String,Serveur>();
			System.out.println("###########################################################################");
			System.out.println("############                                                   ############");
			System.out.println("############    PROGRAMME INTERACTIF -- MESSAGE A PROPAGE ICI  ############");
			System.out.println("############                                                   ############");
			System.out.println("###########################################################################");
			
			System.out.println("> Ce programme est interactif, 6 process seront crées par défaut avec la con-");
			System.out.println("> fig par défaut.");
			System.out.println("> Avec ce programme intéractif vous pouvez aujouter ou supprimer des process");
			System.out.println("> Aussi changer de configuration des process en créant un fichier et en indi-");
			System.out.println("> Le lien vers ce fichier.");
	
			System.out.println("> Les commandes: ");
			System.out.println(" # propage \"message\" : propage le message à partir de l'initiateur");
			System.out.println(" # showConfig: Affiche la configuration courante.");
			System.out.println("> Amusez-vous bien!!!!!");
			
			processList = InitialiseArchitecture.initConfiguration(args[0]);
			System.out.println("Initialisateur : serveur numero  " + processList.get("init").getHost());
			
			String s; 
			
			Scanner sc = new Scanner(System.in);
			System.out.print("Commande > ");
			while ( !(s = sc.nextLine()).equals("quit") )
			{
				if (s.startsWith("propage"))
				{
					processList.get("init").propage(s.substring(8));
				}
				if (s.equals("showConfig"))
				{
					Scanner scanner = new Scanner(new File(args[0]));
					String conf; 
					conf = scanner.nextLine();
					conf = scanner.nextLine();
					String[] tab;
					System.out.println("Initializer =  " + conf.split(":")[1]);
					while ( scanner.hasNextLine())
					{
						conf = scanner.nextLine();
						tab = conf.split(":");
						System.out.println("Server Number " + tab[0]);
						if (tab.length > 1 )
						{
							for (int i = 1; i < tab.length ; i++)
							{
								System.out.println(" _________ Son number " + i + " : " +  tab[i] );
							}
						}else
						{
							System.out.println(" _________ Don't have sons." );
						}
					}
					
					
					
				}
				System.out.print("Commande > ");
			}
		}	
	}

}
