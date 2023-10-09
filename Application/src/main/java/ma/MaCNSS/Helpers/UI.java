package ma.MaCNSS.Helpers;

public class UI {
    public static void MAIN_MENU() {
        System.out.println(
                TextColor.
                        yellowText("\t\t\t\t\tMA CNSS\n\n\n") +
                        "1-Admin\n" +
                        "2-Agent CNSS\n" +
                        "3-Patient\n" +
                        "4-Societe \n" +
                        "5-Employee \n" +
                        "0- Exit\n"
        );
    }

    public static void ADMIN_MENU() {
        System.out.println(
                TextColor.yellowText(
                        "\t1- Add agent\n" +
                                "\t0- Go back"));
    }

    public static void AGENT_MENU() {
        System.out.println(
                TextColor.yellowText(
                        "\t1- Submit a file\n" +
                                "\t0- Go back"));
    }

    public static void PATIENT_MENU() {
        System.out.println(
                TextColor.yellowText(
                        "\t1- Display my files\n" +
                                "\t0- Go back"));
    }
    public static void SOCIETE_MENU(){
        System.out.println(TextColor.yellowText(
                "\t1- Sign in\n" +
                        "\t2- Sign up\n" +
                        "\t0- Go back"));
    }
    public static void SOCIETE_MENU_AFTER_LOGIN(){
        System.out.println(TextColor.yellowText(
                "\t1- For person that already hase immatricule \n" +
                        "\t2- Add an Employee\n" +
                        "\t2- Show an Employee\n" +
                        "\t3 - resignation an employee\n"+
                        "\t0- Log out"));
    }

    public static void EMPLOYEE_MENU(){
        System.out.println(TextColor.yellowText(
                "\t1-Nombre of Work Days : \n" +
                        "\t2- check if can retire \n" +
                        "\t3- salary retirement \n" +
                        "\t0- Go back"));
    }



}
