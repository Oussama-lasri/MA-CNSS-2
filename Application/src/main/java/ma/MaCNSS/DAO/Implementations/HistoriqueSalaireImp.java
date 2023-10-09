package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoriqueSalaireImp {
    Connection con = DBConnection.getConnection();
    public int getAllWorkDays(String immatricule){
        String sql = "select SUM(nbj_mois) from Historique_salaire where immatricule = ?" ;
        int nbj = 0 ;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,immatricule);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()){
                nbj = resultSet.getInt(1);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return nbj ;
    }
    public float getSalaries(String immatricule){
        float totalSalaries = 0 ;
        String sql = "select salaire from Historique_salaire where immatricule = ? limit 96";
        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,immatricule);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                totalSalaries += resultSet.getFloat(1);
            }


        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return totalSalaries ;
    }
}
