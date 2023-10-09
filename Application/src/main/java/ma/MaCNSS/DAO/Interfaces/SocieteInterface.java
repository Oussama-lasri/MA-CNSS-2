package ma.MaCNSS.DAO.Interfaces;

import ma.MaCNSS.Entities.Admin;
import ma.MaCNSS.Entities.Societe;

import java.sql.SQLException;

public interface SocieteInterface {
    public boolean create(Societe societe)
            throws SQLException;
    public Societe findByEmail(String email)
            throws SQLException;
    public Societe findById(int id) throws SQLException  ;
}
