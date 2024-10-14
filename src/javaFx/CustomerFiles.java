package javaFx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerFiles {

    ObservableList<Customer> custList = FXCollections.observableArrayList();

    public ObservableList<Customer> createCustList() {

        custList.clear();
        try {
            File custFile = new File("customers.txt");
            Scanner scan = new Scanner(custFile);

            while (scan.hasNextLine()) {

                String[] data = scan.nextLine().split("~");
                //System.out.println("Data" + Integer.parseInt(data[2]));
                custList.add(new Customer(data[0], data[1], Integer.parseInt(data[2])));
            }
            scan.close();

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error when reading");
            e.printStackTrace();

        }

        return custList;
    }

    public void addCust(String username, String password, int points) {

        try {
            File custFile = new File("customers.txt");
            Scanner scan = new Scanner(custFile);

            while (scan.hasNextLine()) {

                String[] data = scan.nextLine().split("~");

                if (data[0].equals(username)) {
                    System.out.println("Invalid, this username is already taken. Username will not be saved");

                    return;
                }

            }

            scan.close();

            FileWriter writer = new FileWriter(custFile, true);

            writer.write(username + "~" + password + "~" + points + "\n");
            writer.close();

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error when adding");
            e.printStackTrace();

        }
    }

    public void delCust(String username, String password, int points) {

        try {
            File temp = new File("tempC.txt");
            File custFile = new File("customers.txt");
            BufferedReader read = new BufferedReader(new FileReader(custFile));
            BufferedWriter write = new BufferedWriter(new FileWriter(temp));

            String remove = username + "~" + password + "~" + points;
            String curr;

            while ((curr = read.readLine()) != null) {
                String trimLine = curr.trim();
                if (trimLine.equals(remove)) {
                    continue;
                }

                write.write(curr);
                write.newLine();
            }
            write.close();
            read.close();
            custFile.delete();
            temp.renameTo(custFile);

        } catch (IOException e) {
            System.out.println("Error when deleting");
            e.printStackTrace();
        }

    }

    //this is to check the input in the login screen. still needs to be modified
    public String[] checkCust(String username, String password) {

        try {
            File custFile = new File("customers.txt");
            Scanner scan = new Scanner(custFile);

            while (scan.hasNextLine()) {

                String[] data = scan.nextLine().split("~");

                if (data[0].equals(username) && data[1].equals(password)) {
                    return data;

                }

            }

            scan.close();

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error when reading");
            e.printStackTrace();

        }
        return null;

    }

    public void overFile(String username, String password, int points) {

        try {
            File temp2 = new File("tempCC.txt");
            File custFile = new File("customers.txt");
            //File del = new File("delTest.txt");
            int test;
            BufferedReader read = new BufferedReader(new FileReader(custFile));
            BufferedWriter write = new BufferedWriter(new FileWriter(temp2));

            String replace = username + "~" + password + "~" + points;
            String curr;

            while ((curr = read.readLine()) != null) {
                String trimLine = curr.trim();
                String[] space = curr.split(",");
                String arr[] = trimLine.split("~");
                if (arr[0].equals(username)) {
                    test = Integer.parseInt(arr[2]) + points;
                    arr[2] = Integer.toString(test);
                }
                System.out.println(String.join("~", arr));
                write.write(String.join("~", arr));
                write.newLine();
            }

            read.close();
            write.close();

            Files.delete(custFile.toPath());
            Files.move(temp2.toPath(), custFile.toPath());

            System.out.println("Testing Del");

        } catch (IOException e) {
            System.out.println("Error when deleting");
            e.printStackTrace();
        }

    }

}
