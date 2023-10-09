package ma.MaCNSS.DAO.Implementations.Person;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Implementations.SocieteImp;
import ma.MaCNSS.DAO.Interfaces.Person.EmployeeInterface;
import ma.MaCNSS.DAO.Interfaces.SocieteInterface;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Employee;
import ma.MaCNSS.enums.Genre;

import java.sql.*;


public class EmployeeImp implements EmployeeInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee" +
                " (immatricule, cin, nom, prenom, ville, telephone, email, password, genre, salaire, isPresent, isRetrait, id_societe, date_naissance) VALUES" +
                " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, employee.getImmatricule());
            stmt.setString(2, employee.getCIN());
            stmt.setString(3, employee.getNom());
            stmt.setString(4, employee.getPrenom());
            stmt.setString(5, employee.getVille());
            stmt.setString(6, employee.getTelephone());
            stmt.setString(7, employee.getEmail());
            stmt.setString(8, employee.getPassword());
            stmt.setString(9, employee.getGenre().name());
            stmt.setFloat(10, employee.getSalaire());
            stmt.setBoolean(11, employee.isPresent());
            stmt.setBoolean(12, employee.isRetrait());
            stmt.setInt(13, employee.getSociete().getId());
            java.sql.Date sqlDate = java.sql.Date.valueOf(employee.getDate_naissance());
            stmt.setDate(14, sqlDate);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Employee: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean update(Employee employee) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String cin) throws SQLException {
        return false;
    }

    @Override
    public AgentCNSS getEmployee(String cin) throws SQLException {
        return null;
    }

    @Override
    public AgentCNSS findByEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public Employee findByImmatricule(String immatricule) {
        String sql = "SELECT * FROM employee WHERE immatricule = ?" ;
        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,immatricule);
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                Employee employee = new Employee();
                employee.setImmatricule(immatricule);
                employee.setEmail(result.getString("email"));
                employee.setPassword(result.getString("password"));
                employee.setNom(result.getString("nom")) ;
                employee.setPrenom(result.getString("prenom")) ;
                employee.setCIN(result.getString("cin"));
                employee.setSalaire(result.getInt("salaire")) ;
                employee.setVille(result.getString("ville"));
                employee.setTelephone(result.getString("ville"));
                employee.setDate_naissance(result.getDate("date_naissance").toLocalDate());
                Genre genre = Genre.FEMME;
                if (result.getString("genre").equalsIgnoreCase(Genre.HOMME.name())) {
                    genre = Genre.HOMME;
                }
                employee.setGenre(genre);
                SocieteInterface societeImp = new SocieteImp();
                employee.setSociete(societeImp.findById(result.getInt("id_societe")));
                return employee ;
            }else {
                return null ;
            }

        }catch (SQLException ex){
            System.out.println("error message "+ex.getMessage());
            return null;
        }

    }
}
