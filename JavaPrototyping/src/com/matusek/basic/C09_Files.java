package com.matusek.basic;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class C09_Files {

    public static void main(String[] args) {

        String fileInPathRaw = System.getProperty("user.dir") + "\\src\\com\\matusek\\basic\\file-in.txt";
        String fileOutPathRaw = System.getProperty("user.dir") + "\\src\\com\\matusek\\basic\\file-out.txt";
        Path fileInPath = Paths.get(fileInPathRaw);
        Path fileOutPath = Paths.get(fileOutPathRaw);

        if (Files.exists(fileInPath)) {
            System.out.println("File exists!");

            // No .close needed in finally block, if exception in code then resources are automatically closed
            try (Scanner scanner = new Scanner(fileInPath, StandardCharsets.UTF_8);
                 PrintWriter write = new PrintWriter(fileOutPath.toFile(), StandardCharsets.UTF_8)) {
                    String line;
                    while (scanner.hasNext()) {
                        write.append(scanner.nextLine());
                    }
            } catch (IOException e) {
                System.out.println(e);
            }

            // Own close implementation of database, database throw exception, all scanner, write, database are closed
            try (Scanner scanner = new Scanner(fileInPath, StandardCharsets.UTF_8);
                 PrintWriter write = new PrintWriter(fileOutPath.toFile(), StandardCharsets.UTF_8);
                 DatabaseConnection database = new DatabaseConnection()) {
                database.getResult();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

}

class DatabaseConnection implements Closeable {

    public DatabaseConnection() {
        System.out.println("Database init.");
    }

    @Override
    public void close() throws IOException {
        System.out.println("Closing database connection");
    }

    public String getResult() throws IOException {
        throw new IOException("Cannot read from DB");
    }
}
