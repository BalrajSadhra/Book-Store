/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Dipen Patel
 */
public class updatedproject extends Application {

    Button loginbutton = new Button("Login");
    Button booksbutton = new Button("Books");
    Button customersbutton = new Button("Customers");
    Button logoutbutton = new Button("Logout");
    TextField userTextField = new TextField();
    TextField passTextField = new TextField();
    Button backButton = new Button("Back");
    BookFiles B = new BookFiles();
    CustomerFiles C = new CustomerFiles();
    int custIndex;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BookStore App");
        primaryStage.setScene(new Scene(Login(), 500, 200));
        primaryStage.show();

        loginbutton.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Login Button button clicked");
                //actions you want after the login button has been pressed
                System.out.println("Username typed: " + userTextField.getText());
                System.out.println("Password typed: " + passTextField.getText());
                if (passTextField.getText().equals("admin") && userTextField.getText().equals("admin")) {
                    userTextField.clear();
                    passTextField.clear();
                    primaryStage.setScene(new Scene(ownerstartscreen(), 500, 250));
                } else {
                    String[] custdata = C.checkCust(userTextField.getText(), passTextField.getText());//runs the method to check the user and password. still needs to be completed.
                    if (custdata == null) {
                        System.out.println("invalid credentials");
                    } else {
                        CustomerStartScreen(primaryStage, custdata);
                        //you can also use custdata[2] to get the points of the person.

                    }
                }
            }
        }
        );

        booksbutton.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //actions you want after the login button has been pressed
                primaryStage.close();
                OwnerBooksScreen(primaryStage);
            }
        }
        );

        customersbutton.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Customer Button button clicked");
                //actions you want after the customers button has been pressed
                primaryStage.setTitle("OwnerCustomerScreen");
                primaryStage.setScene(new Scene(ownercustomerscreen(), 600, 500));

            }
        }
        );

        logoutbutton.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Logout Button button clicked");
                //actions you want after the logout button has been pressed
                primaryStage.setScene(new Scene(Login(), 500, 200));

            }
        }
        );

        //back button pressed
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(new Scene(ownerstartscreen(), 500, 250));

            }
        });

    }

    //login screen method
    public GridPane Login() {
        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(5);

        // Place nodes in the pane
        pane.add(new Label("Welcome to the BookStore App"), 1, 0);
        pane.add(new Label("Username:"), 1, 2);
        pane.add(new Label("Password:"), 1, 3);
        pane.add(loginbutton, 2, 4);
        pane.add(userTextField, 2, 2);
        pane.add(passTextField, 2, 3);
        return pane;
    }

    //method for ownerstart screen
    public GridPane ownerstartscreen() {
        GridPane pane = new GridPane();
        pane.setHgap(15);
        pane.setVgap(25);
        pane.setAlignment(Pos.CENTER);
        // Place nodes in the pane
        pane.add(booksbutton, 0, 0);
        pane.add(customersbutton, 0, 1);
        pane.add(logoutbutton, 0, 2);

        return pane;
    }

    //method to get customers for the ownercustomerscreen
    /*public static ObservableList<Customer> getcustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        //add code here to add the customers. preferablly from an arraylist or something
        customers.add(new Customer("Dipen", "password1"));

        return customers;
    }*/
    //method for the owner customer screen.
    public VBox ownercustomerscreen() {
        //name column
        TableView<Customer> table;
        TextField addname, addpass;
        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //password column
        TableColumn<Customer, String> passColumn = new TableColumn<>("Password");
        passColumn.setMinWidth(200);
        passColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        //points column
        TableColumn<Customer, Integer> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setMinWidth(200);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("points"));

        //System.out.println(C.createCustList().get(2).getPoints());
        //create the table
        table = new TableView<Customer>();
        table.setItems(C.createCustList());
        table.getColumns().addAll(nameColumn, passColumn, pointsColumn);

        //name textField
        addname = new TextField();
        addname.setPromptText("Name");
        addname.setMinWidth(100);

        //password textfield
        addpass = new TextField();
        addpass.setPromptText("Password");

        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");

        //add button actions
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Customer cust = new Customer("", "", 0);
                cust.setName(addname.getText());
                cust.setPassword(addpass.getText());
                C.addCust(addname.getText(), addpass.getText(), 0);
                addname.clear();
                addpass.clear();
                table.getItems().add(cust);

            }
        });

        //delete button actions
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                C.delCust(table.getSelectionModel().getSelectedItem().getName(), table.getSelectionModel().getSelectedItem().getPassword(), table.getSelectionModel().getSelectedItem().getPoints());
                table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            }
        });
        //hbox
        HBox hb2 = new HBox();
        HBox hb = new HBox();
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(addname, addpass, addButton, deleteButton);
        hb2.setPadding(new Insets(10, 10, 10, 10));
        hb2.setSpacing(10);
        hb2.getChildren().addAll(backButton);

        //vbox
        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, hb, hb2);
        //stage
        return vbox;

    }

    public void OwnerBooksScreen(Stage OwnerBooksScreen) {       //START OF OWNER-BOOKS-SCREEN***************************************************************************
        TableView<Book> table;

//                   new Book("The Fault in our Stars", "25.00"),
//                   new Book("Attack on Titan: Chapter 139", "30.00"),
//                   new Book("The Help", "35.00"));
//                
        final HBox hb = new HBox();
        final HBox hb2 = new HBox();
        final Label label = new Label("List of Books:");
        label.setFont(new Font("Arial", 20));

        TableColumn<Book, String> bookNameCol = new TableColumn<>("Book Name");
        bookNameCol.setMinWidth(500);
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));

        TableColumn<Book, Double> bookPriceCol = new TableColumn<>("Book Price");
        bookPriceCol.setMinWidth(110);
        bookPriceCol.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));

        final TextField addBookName = new TextField();
        addBookName.setPromptText("Book Name");
        addBookName.setMaxWidth(250);

        final TextField addBookPrice = new TextField();
        addBookPrice.setMaxWidth(100);
        addBookPrice.setPromptText("Book Price");

        final Button addButton = new Button("Add");
        final Button deleteButton = new Button("Delete");

        table = new TableView<>();

        table.setItems(B.createBookList());

        table.getColumns().addAll(bookNameCol, bookPriceCol);

        hb.getChildren().addAll(addBookName, addBookPrice);
        hb.setSpacing(3);
        hb2.getChildren().addAll(addButton, deleteButton, backButton);
        hb2.setSpacing(3);
        final VBox vbox2 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setPadding(new Insets(10, 0, 0, 10));
        vbox2.getChildren().addAll(label, table, hb, hb2);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Book book = new Book("", 0);
                book.setBookName(addBookName.getText());
                book.setBookPrice(Double.parseDouble(addBookPrice.getText()));
                B.addBook(addBookName.getText(), addBookPrice.getText());
                addBookName.clear();
                addBookPrice.clear();

                table.getItems().add(book);

            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                B.delBook(table.getSelectionModel().getSelectedItem().getBookName(), table.getSelectionModel().getSelectedItem().getBookPrice());
                table.getItems().removeAll(table.getSelectionModel().getSelectedItem());

                //B.createBookList();
                //System.out.println(table.getSelectionModel().getSelectedItem().getBookName());
                //System.out.println(table.getSelectionModel().getSelectedItem().getBookPrice());
            }
        });

        Scene scene = new Scene(vbox2);
        OwnerBooksScreen.setScene(scene);
        OwnerBooksScreen.show();

        //((Group) scene.getRoot()).getChildren().addAll(vbox2);//PROBLEM MIGHT BE HERE       
    }  //END OF OWNER-BOOKS-SCREEN***************************************************************************

    public void CustomerCheckout(Stage OwnerBookScreen, double TC) {

    }

    public void CustomerStartScreen(Stage OwnerBooksScreen, String[] custdata) {       //START OF CUSTOMER-START-SCREEN***************************************************************************
        TableView<Book> table;

        C.createCustList();
        for (int i = 0; i < C.custList.size(); i++) {
            if (custdata[0].equals(C.custList.get(i).getName())) {
                custIndex = i;
                break;
            }
        }

        System.out.println("Cust Index: " + custIndex);

        ObservableList<Book> dataListAdd = FXCollections.observableArrayList();

        final HBox hb = new HBox();
        final HBox hb2 = new HBox();
        final Label label = new Label("Welcome, " + custdata[0] + "! You have " + custdata[2] + " and your status is " + "GOLD");
        label.setFont(new Font("Arial", 20));

        TableColumn<Book, String> bookNameCol = new TableColumn<>("Book Name");
        bookNameCol.setMinWidth(500);
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));

        TableColumn<Book, Double> bookPriceCol = new TableColumn<>("Book Price");
        bookPriceCol.setMinWidth(110);
        bookPriceCol.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));

        TableColumn<Book, String> selectCol = new TableColumn("Select");
        selectCol.setMinWidth(110);
        selectCol.setCellValueFactory(new PropertyValueFactory<>("select"));

        table = new TableView<>();
        table.setItems(B.createBookList());
        table.getColumns().addAll(bookNameCol, bookPriceCol, selectCol);

//        boolean isSelected = select.isSelected();
//        select.setAllowIndeterminate(false);
//        boolean isIndeterminate = select.isIndeterminate();
//        
        final Button buyButton = new Button("Buy");
        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                for (int i = 0; i < B.bookList.size(); i++) {
                    if (B.bookList.get(i).getSelect().isSelected()) {
                        dataListAdd.add(B.bookList.get(i));
                        B.delBook(B.bookList.get(i).getBookName(), B.bookList.get(i).getBookPrice());
                    }
                }

                double TC = 0;

                for (int k = 0; k < dataListAdd.size(); k++) {
                    TC += dataListAdd.get(k).getBookPrice();
                }

                C.custList.get(custIndex).accPoints((int) TC);
                //System.out.println(C.custList.get(custIndex).getPoints());
                C.overFile(C.custList.get(custIndex).getName(), C.custList.get(custIndex).getPassword(), C.custList.get(custIndex).getPoints());

                //System.out.println("Test Price: " + TC);
                //System.out.println("Test points: " + C.custList.get(custIndex).getPoints());
                //Fix this if necessary cuz idk how to transition screens xd
                OwnerBooksScreen.close();
                CustomerCheckout(OwnerBooksScreen, TC);
            }
        });

        final Button RedeemAndBuyButton = new Button("Redeem Points and Buy");
        RedeemAndBuyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                for (int i = 0; i < B.bookList.size(); i++) {
                    if (B.bookList.get(i).getSelect().isSelected()) {
                        dataListAdd.add(B.bookList.get(i));
                        B.delBook(B.bookList.get(i).getBookName(), B.bookList.get(i).getBookPrice());
                    }
                }

                double TC = 0;

                for (int i = 0; i < dataListAdd.size(); i++) {
                    TC += dataListAdd.get(i).getBookPrice();
                }

                TC = C.custList.get(custIndex).redeemPoints(TC);
                System.out.println(C.custList.get(custIndex).getPoints());
                C.overFile(C.custList.get(custIndex).getName(), C.custList.get(custIndex).getPassword(), C.custList.get(custIndex).getPoints());

                //Fix this if necessary cuz idk how to transition screens xd
                OwnerBooksScreen.close();
                CustomerCheckout(OwnerBooksScreen, TC);

            }
        });

        hb.getChildren().addAll(buyButton, RedeemAndBuyButton);
        hb.setSpacing(3);
        hb2.getChildren().addAll(logoutbutton);
        hb2.setSpacing(3);
        final VBox vbox2 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setPadding(new Insets(10, 0, 0, 10));
        vbox2.getChildren().addAll(label, table, hb, hb2);

        Scene scene = new Scene(vbox2);
        OwnerBooksScreen.setScene(scene);
        OwnerBooksScreen.show();

    }  //END OF CUSTOMER-START-SCREEN***************************************************************************
} //END OF CODE**********************************************************************************
