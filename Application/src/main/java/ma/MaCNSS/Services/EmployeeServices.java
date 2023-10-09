package ma.MaCNSS.Services;



import ma.MaCNSS.DAO.Implementations.HistoriqueSalaireImp;
import ma.MaCNSS.DAO.Implementations.Person.EmployeeImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.DAO.Implementations.SocieteImp;
import ma.MaCNSS.DAO.Interfaces.Person.EmployeeInterface;
import ma.MaCNSS.DAO.Interfaces.SocieteInterface;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Employee;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.Entities.Societe;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.RandomStringGenerator;
import ma.MaCNSS.enums.Genre;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import static ma.MaCNSS.Helpers.PmScanner.dateInput;

public class EmployeeServices {
    static Scanner scanner = new Scanner(System.in);
    static EmployeeInterface employeeImp  = new EmployeeImp();
    static Employee employee = new Employee();

    public static void addEmplyeeInSociete(Societe societe){
        Patient patient = new Patient() ;
        String immatricule = "-1" ;
        do {
            try {
                 immatricule = PmScanner.takeStringInputValue("Enter the Employee immatricule: (enter 0 for exit)");
                 if (immatricule.equals("0")){
                     break;
                 }
                employee = employeeImp.findByImmatricule(immatricule);
                PatientImp patientImp = new PatientImp();
                patient = patientImp.getPtient(immatricule);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            if (employee == null && patient == null) {
                System.out.println("There is no Employee or patient with this immatricule, Try another one");
            }
            if (patient != null){
                System.out.println("you can create employee with this immatricule");
            }
        }while(employee == null && patient == null );
        if (!immatricule.equals("0")) {
            System.out.println("are you want to add this patient as employee (Y/N)");
            scanner.nextLine();
            // if employee not exist
            if (employee == null) {
                Employee employee1 = new Employee();
                employee1.setImmatricule(patient.getImmatricule());
                employee1.setCIN(patient.getCIN());
                employee1.setGenre(patient.getGenre());
                employee1.setTelephone(patient.getTelephone());
                employee1.setNom(patient.getNom());
                employee1.setPrenom(patient.getPrenom());
                employee1.setVille(patient.getVille());
                employee1.setEmail(patient.getEmail());
                employee1.setPassword(patient.getPassword());

                float salaire = PmScanner.takeFloatInputValue("enter salaire of employee");
                LocalDate birthDate  = PmScanner.dateInput();
                employee1.setDate_naissance(birthDate);
                employee1.setSalaire(salaire);
                employee1.setSociete(societe);
                try {
                    if (employeeImp.add(employee1)) {
                        System.out.println("added successfully");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (patient == null) {
                System.out.println("create now one ");
            }
        }


    }
    public static Employee createEmployee(int id){
        employee.setCIN(PmScanner.takeStringInputValue("CIN: "));
        employee.setNom(PmScanner.takeStringInputValue("Nom: "));
        employee.setPrenom(PmScanner.takeStringInputValue("Prenom: "));
        employee.setVille(PmScanner.takeStringInputValue("Ville: "));
        employee.setTelephone(PmScanner.takeStringInputValue("Telephone: "));
        employee.setGenre(Genre.valueOf(PmScanner.takeGender("Genre(HOMME/FEMME)? ")));
        employee.setEmail(PmScanner.takeStringInputValue("Email: "));
        employee.setDate_naissance(dateInput());
        employee.setPassword(PmScanner.takeStringInputValue("Mote de passe: "));
        employee.setSalaire(PmScanner.takeFloatInputValue("salaire : "));
        SocieteInterface societeImp = new SocieteImp();
        Societe societe = new Societe();
        try {
            societe = societeImp.findById(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        employee.setSociete(societe);
        try {
            Patient patient = new Patient(RandomStringGenerator.generateRandomString(), employee.getCIN(), employee.getNom(), employee.getPrenom(),employee.getVille(),employee.getTelephone(),employee.getEmail(),employee.getPassword(),employee.getGenre());
            PatientImp patientImp = new PatientImp();
            if (patientImp.add(patient)) {
                employee.setImmatricule(patient.getImmatricule());
                if (employeeImp.add(employee)) {
                    return employee;
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }


return null ;

    }
    public static boolean canRetire(){
    // calcul age of emplyee
        System.out.print("enter immatricule of employee : ");
        String immatricule = scanner.next();
        Employee employee1 = employeeImp.findByImmatricule(immatricule);
        if (employee1 != null) {
            Period period = Period.between(employee1.getDate_naissance(), LocalDate.now());
            int age = period.getYears();
            System.out.println(age);
            HistoriqueSalaireImp salaireImp = new HistoriqueSalaireImp();
            //int workDays = salaireImp.getAllWorkDays(immatricule);
            // for example
            int workDays = 3800  ;
            // all salaries from database divide by 96
            //float AVGsalary = salaireImp.getSalaries(immatricule);
            // for exemple
            float AVGsalary = 1700 ;
            double retirementSalary;
            if (age >= 55) {
                if (workDays >= 3240) {
                    int supp = workDays - 3240;
                    float percentage_supp = Math.abs(supp / 216);
                    System.out.println(percentage_supp);
                    retirementSalary = (float) (AVGsalary * (0.5 + (float) (percentage_supp / 100)));
                    if (retirementSalary > 6000) {
                        retirementSalary = 6000F;
                        System.out.println("your salary is more than 6000DH you can only benefit 6000DH");
                    } else if (retirementSalary <= 1000) {
                        retirementSalary = 1000;
                    }
                    System.out.println("your salary is " + retirementSalary);

                } else if (workDays >= 1320) {
                    // get salary AVG of 96 mounth
                    retirementSalary = (AVGsalary / 96) * 0.5;
                    System.out.println("your salary is" + retirementSalary);
                }
            } else {
                System.out.println(" you can't retirement , baqi lik " + (55 - age));
            }

        }else {
            System.out.println("immatricule is not exist");
        }

        return false ;
    }

}
