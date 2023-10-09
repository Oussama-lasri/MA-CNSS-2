package ma.MaCNSS.DAO.Interfaces.Person;

import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeInterface {
    public boolean add(Employee employee)
            throws SQLException;

    public boolean update(Employee employee)
            throws SQLException;

    public boolean delete(String cin)
            throws SQLException;

    public AgentCNSS getEmployee(String cin)
            throws SQLException;


    public AgentCNSS findByEmail (String email)
            throws SQLException;

    Employee findByImmatricule(String immatricule);
}
