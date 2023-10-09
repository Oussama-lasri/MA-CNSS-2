package ma.MaCNSS.Interfaces;

import java.time.LocalDate;

@FunctionalInterface
public interface DateFormat {
    LocalDate dateParse(String userInput) ;
}
