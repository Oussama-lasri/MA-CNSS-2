package ma.MaCNSS.Services;

import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.DAO.Implementations.SocieteImp;
import ma.MaCNSS.DAO.Interfaces.SocieteInterface;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.Entities.Societe;
import ma.MaCNSS.Helpers.GMailer;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SocieteServices {
    static Scanner scanner = new Scanner(System.in);
    static SocieteInterface societeDAO = new SocieteImp();
    static   Societe societe = new Societe() ;

    public static Societe login(){
        int count = 0 ;
        String email , password;
        Societe loggedSociete = new Societe();
        try{
            do {
                System.out.print("enter email : ");
                email = scanner.next();
                loggedSociete = societeDAO.findByEmail(email);
                if(loggedSociete != null) {
                    System.out.print("Enter your password: ");
                    password =  scanner.next();
                    //if (BCrypt.checkpw(agentCNSS.getPassword(), loggedInAgent.getPassword())) {
                    if (loggedSociete.getPassword().equals(password)) {
                        //GMailer.send();
                        int randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
                        try {
                            boolean emailSent = new GMailer().sendEmail(String.valueOf(randomNumber), "email confirmation", loggedSociete.getEmail());
                            if (emailSent) {
                                int userInput = PmScanner.takeIntInputValue("Enter verification code: ");
                                if (userInput == randomNumber) {
                                    System.out.println("Connected successfully");
                                    return loggedSociete;
                                } else {
                                    System.out.println(TextColor.yellowText("Wrong verification code?!"));
                                }
                            } else {
                                System.out.println(TextColor.yellowText("Error sending the email, check your network!"));
                            }
                        } catch (Exception ex) {
                            System.out.println("Error sending the email, check your network!");
                        }
                    }else {
                        System.out.println("password not correct");
                        count++ ;
                    }
                }else {
                    System.out.println("not found");
                }
            } while (count != 3);
    
        }catch (Exception ex){
            System.out.println("message error : "+ex.getMessage());
            return null;
        }

        return null ;
    }
    public static  void register(){
        societe.setLabel(PmScanner.takeStringInputValue("label : "));
        societe.setEmail(PmScanner.takeStringInputValue("Email : "));
        societe.setPassword(PmScanner.takeStringInputValue("Password : "));

        try{
            if (societeDAO.create(societe)){
                System.out.println("created");
            }else {
                System.out.println("something went wrong");
            }

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
