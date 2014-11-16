package serveur;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Serveur extends Remote 
{
	
	public String showDetails() throws RemoteException; 
		
	public String getFatherHost() throws RemoteException; 
	
	public int setFatherHost(String nom) throws RemoteException; 
	
	
	public Serveur getSon(String nom) throws RemoteException; 
	
		
	public int addSon(Serveur nom) throws RemoteException;
	
	
	public String getHost() throws RemoteException; 
	
	
	public int setHost(String nom) throws RemoteException; 
	
	
	public int bindHimSelf() throws RemoteException;
	
	
	public int propage(String message) throws RemoteException;
	
	
	public int forgiveConfig() throws RemoteException;
	
	
	
	
	
	
	

}
