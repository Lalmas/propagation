package serveur;

import java.rmi.registry.LocateRegistry;

public class LanceAnnuaire 
{
	public static void main(String[] args)
	{
		try {
			LocateRegistry.createRegistry(1099);
			while(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
