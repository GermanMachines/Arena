package edu.arena.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.Order;
import edu.arena.services.OrderCRUD;
import edu.arena.services.OrderSrvc;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Foura
 */
public class OrdersController implements Initializable {
    
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> productCol;
    @FXML
    private TableColumn<Order, String> userCol;
    @FXML
    private TableColumn<Order, Integer> qtyCol;
    @FXML
    private TableColumn<Order, Date> dateCol;
    
    ObservableList<Order> OrdersList = FXCollections.observableArrayList();
    @FXML
    private Label orderNumberLbl;
    @FXML
    private JFXTextField searchFld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }
    
    private void refreshTable() {
        try {
            OrdersList.clear();
            OrderCRUD ocrud = new OrderCRUD();
            
            List<Order> orders = ocrud.showAllOrders();
            System.out.print(orders);
            OrdersList.setAll(orders);
            ordersTable.setItems(OrdersList);

            if (orders.size() == 1) {
                orderNumberLbl.setText(Integer.toString(orders.size()) + " order");
            } else {
                orderNumberLbl.setText(Integer.toString(orders.size()) + " orders");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void loadData() {
        refreshTable();
        
        productCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("productQty"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
    }

    @FXML
    private void search(KeyEvent event) {
        FilteredList<Order> filteredData = new FilteredList<>(OrdersList, b -> true);
        searchFld.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(o -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (o.getProductName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (o.getUserName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });

        });
        SortedList<Order> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ordersTable.comparatorProperty());
        ordersTable.setItems(sortedData);
    }

    @FXML
    private void exportTable(ActionEvent event) throws SQLException, FileNotFoundException {
        OrderSrvc os = new OrderSrvc();
        os.exportTable();
    }
}
