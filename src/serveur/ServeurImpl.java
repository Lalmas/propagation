package serveur;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServeurImpl extends UnicastRemoteObject implements Serveur 
{
	
	private ServeurImpl father; 
	
	private String fatherHost; 
	
	
	private String host;
	
	
	private Map<String, Serveur> sons; 
	
	
	
	protected ServeurImpl() throws RemoteException {
		super();
		this.host 	= "Serveur sans nom";
		this.fatherHost = ""; 
		this.sons = new HashMap<String, Serveur>(); 
	}
	
	protected ServeurImpl(String host) throws RemoteException {
		super();
		this.host 	= host;
		this.fatherHost = ""; 
		this.sons = new HashMap<String, Serveur>(); 
		this.bindHimSelf();
	}


	public String showDetails() throws RemoteException 
	{
		String res = "Serveur Numero " + this.host + " : Pere " + this.fatherHost + " ";
		if ( this.sons.keySet().size() == 0)
		{
			res += " Aucun fils.";
		}
		else
		{
			Iterator<String> it = this.sons.keySet().iterator();
			int i = 1;
			String s = "";
			while ( it.hasNext())
			{
				s = it.next();
				res += "Fils N° " + s + ", ";
						
			}
		}
		
		return res;
	}

	public String getFatherHost() throws RemoteException 
	{
		return this.fatherHost;
	}


	public Serveur getSon(String nom) throws RemoteException{ 
		return this.sons.get(nom);
	}


	public int addSon(Serveur nom) throws RemoteException {
		
		try {
			this.sons.put(nom.getHost(), nom) ;
			nom.setFatherHost(this.host);
			System.out.println("[" + this.host + "] Adding son " + nom.getHost());
			return 1; 
		} catch (Exception e) {
			System.out.println("[" + this.host + "] Adding son " + nom.getHost() + "--- Echec");
			return 0;
		}
		
	}


	public String getHost() throws RemoteException 
	{
		return this.host;
	}


	public int setHost(String nom) throws RemoteException 
	{		
			this.host = nom;
			System.out.println("[" + this.host + "] setting hostname  " + nom);
			return 1;
		
	}


	public int setFatherHost(String nom) throws RemoteException {
		try {
			this.fatherHost = nom;
			System.out.println("[" + this.host + "] Setting father's hostname " + nom);
			return 1;
		} catch (Exception e) {
			System.out.println("[" + this.host + "] Setting father's hostname " + nom + "--- Echec");
			return 0;
		}
	}


	public int bindHimSelf() {
		try {
			
			Naming.bind("rmi://" + InetAddress.getLocalHost().getHostAddress() + "/" + this.host, this);
			System.out.println("[" + this.host + "] Binding myself  with name " + this.host);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public int propage(String message) throws RemoteException {
		System.out.println(" [ " + this.getHost() + " ] Receive message :  " + message  );
		if ( this.sons.keySet().size() == 0)
		{
			System.out.println(" [ " + this.getHost() + " ] Don't have son. This message will be not propagate." );
			return 1;
		}
		else
		{
			Iterator<String> it = this.sons.keySet().iterator();
			Serveur fils = null; 
			String s = "";
			int fils_res = 0;
			while ( it.hasNext() )
			{
				s = it.next();
				fils = (Serveur) this.sons.get(s);
				System.out.println(" [ " + this.getHost() + " ]  Sending message to my son " + fils.getHost());
				try
				{					
					fils_res = fils.propage(message);
				}catch (Exception e)
				{
					System.out.println(" [ " + this.getHost() + " ] Exception --- My son  " + fils.getHost() + " failed ");
					return -1;
				}				
				if ( fils_res == -1 )
					System.out.println(" [ " + this.getHost() + " ] Exception --- My son  " + fils.getHost() + " failed ");
				else
					System.out.println(" [ " + this.getHost() + " ] propagation of my son " + fils.getHost() + " has succesful ");
			}
			
		}
		return 0;
	}
	
	
	public static void main(String[] args) throws RemoteException
	{
		Serveur s1 = new ServeurImpl();
		s1.setHost(args[0]);
		s1.bindHimSelf();
	}


	@Override
	public int forgiveConfig() throws RemoteException {
		System.out.println(" [ " + this.getHost() + " ]  Removing last configuration " );
		this.father = null; 
		this.fatherHost = "";
		this.sons = null; 
		this.sons = new HashMap<String, Serveur>();
		return 0;
	}


}
