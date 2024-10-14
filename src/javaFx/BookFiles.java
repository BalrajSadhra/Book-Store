/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookFiles {

    File fileB = new File("books.txt");
    ObservableList<Book> bookList = FXCollections.observableArrayList();

    public ObservableList<Book> createBookList() {
        bookList.clear();
        try {
            Scanner scan = new Scanner(fileB);

            while (scan.hasNextLine()) {

                String[] data = scan.nextLine().split("~");

                bookList.add(new Book(data[0], Double.parseDouble(data[1])));
            }
            scan.close();

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("File Error");
            e.printStackTrace();

        }

        return bookList;
    }

    public void addBook(String name, String price) {
        try {
            
            Scanner scan = new Scanner(fileB);

            while (scan.hasNextLine()) {

                String[] data = scan.nextLine().split("~");

                if(data[0].equals(name)){
                    System.out.println("Invalid, this book name is already taken. Book name will not be saved");
                 
                    return;
                }
                
                
            }
            scan.close();
            
            FileWriter writer = new FileWriter(fileB, true);

            writer.write(name + "~" + price + "\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();

        }
    }

    public void delBook(String book, double price) {
        try {
            File temp = new File("tempB.txt");

            BufferedReader read = new BufferedReader(new FileReader(fileB));
            BufferedWriter write = new BufferedWriter(new FileWriter(temp));

            String remove = book + "~" + price;
            String curr;

            while ((curr = read.readLine()) != null) {
                String trimLine = curr.trim();
                String[] test = trimLine.split("~");
                if (test[0].equals(book)) {
                    //System.out.println("tester");
                    continue;
                }
                write.write(curr);
                //write.write(curr + System.getProperty("line.seperator"));
                write.newLine();
            }
            write.close();
            read.close();

            fileB.delete();
            temp.renameTo(fileB);
        } catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }

    }

}
