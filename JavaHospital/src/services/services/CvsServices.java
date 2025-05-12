package services.services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CvsServices {
    private static final String FILE_NAME = "D:\\GitHubRepo\\JavaHospital\\JavaHospital\\src\\resources\\Actiuni.cvs";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String actiune) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // true = append mode
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.write(actiune + "," + timestamp + "\n");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișierul de log: " + e.getMessage());
        }
    }
}

