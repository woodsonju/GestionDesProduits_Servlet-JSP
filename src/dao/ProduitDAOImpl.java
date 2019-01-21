package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.entities.Produit;
import sun.nio.cs.SingleByte;

//Dans cette classe je fais le Mapping Objet Relationnel
public class ProduitDAOImpl implements IProduitDAO{

	@Override
	public Produit saveProduit(Produit p) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUITS (DESIGNATION, PRIX, QUANTITE) VALUES (?, ?, ?)");
			//Remplacer les ? par leurs valeurs recupérées dans l'objet p
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			
			//Execution de la requête insert
			ps.executeUpdate();
			
			
			/*
			 * On a besoin d'afficher le produit sur l'interface utilisateur
			 * Il nous manque le paramètre id
			 */
			
			//Recuperation de l'id dans la base de données
			PreparedStatement ps1 = connection.prepareStatement("SELECT MAX(ID) AS MAXID FROM PRODUITS");
			//Execution de la requête select dont les valeurs sous forme de tableau, est mis dans l'objet ResultSet
			ResultSet rs = ps1.executeQuery();
			
			//S'il y a un enregistrement, une valeur
			if(rs.next())
				{
					//On ajoute l'id à l'objet Produit
					p.setId(rs.getLong("MAXID"));
				}
			
			
			//Fermeture de l'objet PreparedStatement
			ps.close();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Produit> produitParMC(String mc) {
		Connection connection = SingletonConnection.getConnection();
		List<Produit> produits = new ArrayList<Produit>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE DESIGNATION LIKE ? ");
			ps.setString(1, mc);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Produit p = new Produit();
				p.setId(rs.getLong("ID"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
				produits.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produits;
	}

	@Override
	public Produit getProduit(Long id) {
		Produit produit = null;
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())//On utilise if au lieu de while car il y a un seul
			{
				produit = new Produit();
				produit.setId(rs.getLong("id"));
				produit.setDesignation(rs.getString("DESIGNATION"));
				produit.setPrix(rs.getDouble("PRIX"));
				produit.setQuantite(rs.getInt("QUANTITE"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produit;
	}

	@Override
	public Produit updateProduit(Produit p) {
		
		Connection connection = SingletonConnection.getConnection();
		try {
			
			PreparedStatement ps = connection.prepareStatement("UPDATE PRODUITS SET DESIGNATION=?, PRIX=?, QUANTITE=? WHERE ID=?");
			
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			ps.setLong(4, p.getId());
			
			ps.executeUpdate();
			
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public void deleteProduit(Long id) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM PRODUITS WHERE ID=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//On va demander à spring d'executer une méthode après l'instanciation avec init-method
	public void init() {
		System.out.println("Inialisation .....");
	}
	
	
	
	
	
}
