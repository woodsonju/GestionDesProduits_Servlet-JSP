package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Utilisation d'un Design Pattern Singleton
 * Permet de crée une seule connexion à la base de données
 */
public class SingletonConnection {
	
	private static Connection connection;
	
	/*La connexion est crée dans le bloc static
	 * Quand la classe est chargé en memoire le premier bloc qui va s'executer 
	 * c'est le bloc static. L'objet est crée qu'une seule fois!!!
	 * */
	static {
		try {
			//Chargement du pilote JDBC
			Class.forName("com.mysql.jdbc.Driver");
			//Connexion à la base de données
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_produit_servlet", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
	

}
