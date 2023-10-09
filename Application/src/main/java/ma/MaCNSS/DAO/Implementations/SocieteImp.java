package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.SocieteInterface;
import ma.MaCNSS.Entities.Societe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SocieteImp implements SocieteInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean create(Societe societe) throws SQLException {
        String sql = "INSERT INTO societe" +
                " (label, email, password) VALUES" +
                " (?, ?, ?) ";
        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, societe.getLabel());
            stmt.setString(2, societe.getEmail());
            stmt.setString(3, societe.getPassword());
            return stmt.executeUpdate() > 0 ;

        }catch(SQLException e){
            System.err.println("Error inserting : " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Societe findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM societe where email = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,email);
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                Societe societe = new Societe();
                societe.setLabel(result.getString("label"));
                societe.setEmail(result.getString("email"));
                societe.setId(result.getInt("id"));
                societe.setPassword(result.getString("password"));
                return societe ;
            }else {
                return null ;
            }

        }catch (SQLException e){
            System.err.println("Error : " + e.getMessage());
        }
        return null;
    }
    @Override
    public Societe findById(int id) throws SQLException {
        String sql = "SELECT * FROM societe where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                Societe societe = new Societe();
                societe.setLabel(result.getString("label"));
                societe.setEmail(result.getString("email"));
                societe.setId(result.getInt("id"));
                societe.setPassword(result.getString("password"));
                return societe ;
            }else{
                System.out.println("Societe not found");
            }

        }catch (SQLException e){
            System.err.println("Error : " + e.getMessage());
        }
        return null;
    }
}
