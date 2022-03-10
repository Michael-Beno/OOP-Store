package cit.oop.gui;



import cit.oop.source.CustomersDB;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import cit.oop.source.Customer;
import cit.oop.source.CustomerOrders;
import cit.oop.source.Order;
import cit.oop.source.Product;
import cit.oop.source.ProductDB;


/**
 *
 * @author Michael Beno
 */
public class FXMLDocumentController implements Initializable {
    

    private ProductDB pdb;
    private CustomersDB cust;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfMake;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfSize;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button btnCreateSave;
    @FXML
    private VBox box;
    @FXML
    private Label lblCreateStatus;
    @FXML
    private TextField tfSearchId;
    @FXML
    private Label lblDisplayFound;
    @FXML
    private Label lblIdNotFound;
    @FXML
    private TextField tfCustName;
    @FXML
    private Label lbllOrder;
    @FXML
    private Label lblOrderPrcie;
    @FXML
    private TextField tfOrderId;
    @FXML
    private VBox boxOrder;
    @FXML
    private TextField tfQuantityOrder;
    @FXML
    private Label lblTotalPrice;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCreateCustomer;
    @FXML
    private Button btnPlaceOrder;
    @FXML
    private Button btnRemoveOrder;
    @FXML
    private TextField tfNameGetOrders;
    @FXML
    private VBox boxDisplay;
    @FXML
    private Label lblOrderPlaced;
    @FXML
    private Label lblNameTest;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pdb = new ProductDB();
        cust = new CustomersDB();
    }    

    //Create product
     @FXML
    private void onTabCreateProduct(Event event) {
        eraseFields();
    }
    
    @FXML
    private void onBtnCreateSave(ActionEvent event) {
          boolean isfilled = true;
          Product product = new Product();
          if(!tfCategory.getText().isEmpty()) {product.setCategory(tfCategory.getText());
          }else isfilled = false;
          if(!tfMake.getText().isEmpty()) {product.setMake(tfMake.getText());
          }else isfilled = false;
          if(!tfModel.getText().isEmpty()) {product.setModel(tfModel.getText());
          }else isfilled = false;
          if(!tfSize.getText().isEmpty()) {product.setSize(tfSize.getText());
          }
          if(!tfDescription.getText().isEmpty()) {product.setDescription(tfDescription.getText());
          }
          if(!tfPrice.getText().isEmpty()) {
              double price = 0;
              try {
                   price = Double.parseDouble(tfPrice.getText());
              } catch (Exception e) {
                  isfilled = false;
                  tfPrice.setText("");
              }
              product.setPrice( price );
          }else isfilled = false;
         if(isfilled){
             lblCreateStatus.setText("Product is created and saved.");
            pdb.add(product);
            btnCreateSave.setDisable(true);
         }else{
             lblCreateStatus.setText("All mandatory fields must be filled!");
         }
    }

    @FXML
    private void onBtnCleanFields(ActionEvent event) {
        eraseFields(); 
    }

    private void eraseFields() {
        tfCategory.setText("");
        tfMake.setText("");
       tfModel.setText("");
       tfSize.setText("");
       tfPrice.setText("");
       tfDescription.setText("");
       lblCreateStatus.setText(""); 
       btnCreateSave.setDisable(false);
    }
    
    
//Dispaly All product
     @FXML
    private void onTabDisplayAll(Event event) {
        box.getChildren().clear();
        HBox vbox;

        for (int i = 0; i < pdb.getArrProduct().size(); i++) {
            Product p = pdb.getArrProduct().get(i);
      
            vbox = new HBox();   
            Label lb = new Label(p.getProductID()+"");
            lb.setMinWidth(50);
            lb.setFont(new Font("Consolas", 14));
            vbox.getChildren().add(lb);
            Label lb1 = new Label(p.getCategory());
            lb1.setMinWidth(80);
            lb1.setFont(new Font("Consolas", 14));
            vbox.getChildren().add(lb1);
            Label lb2 = new Label(p.getMake()+" " + p.getModel()+" "+p.getSize()+" "+ p.getDescription());
            lb2.setMinWidth(480);
            lb2.setFont(new Font("Consolas", 14));
            vbox.getChildren().add(lb2);
            Label lb3 = new Label(String.format("€%10.2f", p.getPrice()));
            lb3.setMinWidth(100);
            lb3.setFont(new Font("Consolas", 14));
            vbox.getChildren().add(lb3);   
            box.getChildren().add(vbox);  

        }
    }

    //Search Product Id
    @FXML
    private void onTextFieldClicked(MouseEvent event) {
        tfSearchId.setText("");
        lblIdNotFound.setText("");
        lblDisplayFound.setText("");
    }
    
    @FXML
    private void onBtnSearch(ActionEvent event) {
        int id = -1;
        Product p = null;
        if(tfSearchId.getText().isEmpty()){
            lblIdNotFound.setText("FIELD IS EMPTY");
        }else{
            try {
               id = Integer.parseInt(tfSearchId.getText());
                
               p = pdb.find(id);
           
               if(p == null){
                lblIdNotFound.setText("PRODUCT NOT FOUND");
                }else{
                    lblDisplayFound.setText("Product ID : "+p.getProductID()
                                         +"\nCategory   : "+p.getCategory()
                                         +"\nMake       : "+p.getMake()
                                         +"\nModel      : "+p.getModel()
                                         +"\nSize       : "+p.getSize()
                                         +"\nDescription: "+p.getDescription()
                                         +"\nPrice      : €"+String.format("%10.2f", p.getPrice() ) );
                }
            } catch (Exception e) {
                lblIdNotFound.setText("INVALID ID");
            }
          
        }
    }
    

// Order Product
    @FXML
    private void onCustomNameChanged(KeyEvent event) {
        int custID = cust.getCustID(""+tfCustName.getText());

        if(custID > 0) btnCreateCustomer.setText("Existing Customer");
        else btnCreateCustomer.setText("Create Customer");
        
        if(tfCustName.getText().isEmpty()){
            tfOrderId.setDisable(true);
            tfQuantityOrder.setDisable(true);
            btnAdd.setDisable(true);
            btnPlaceOrder.setDisable(true);
            btnRemoveOrder.setDisable(true);
            btnCreateCustomer.setDisable(true);
        }else{
            btnCreateCustomer.setDisable(false);
        }
//        removeOrders();
    }

    @FXML
    private void onTabOrderProduct(Event event) {
        cust.reloadList();
    }
    private Product p = null;
    @FXML
    private void onQuantityClick(MouseEvent event) {
         int id = -1;
        
        if(tfOrderId.getText().isEmpty()){
            lbllOrder.setText("FIELD IS EMPTY");
            lblOrderPrcie.setText("");
        }else{
            try {
               id = Integer.parseInt(tfOrderId.getText());
               p = pdb.find(id);
               if(p == null){
                lbllOrder.setText("PRODUCT NOT FOUND");
                lblOrderPrcie.setText("");
                }else{
               lbllOrder.setText(p.getCategory() +" "+p.getMake() +" "+p.getModel() +" "+p.getSize() +" "+p.getDescription() );
               lblOrderPrcie.setText(String.format("€%8.2f", p.getPrice() ));
               }
            } catch (Exception e) {
                lbllOrder.setText("INVALID ID");
            }
          
        }
        
    }

    @FXML
    private void onIdClicked(MouseEvent event) {
        tfOrderId.setText("");
        tfQuantityOrder.setText("");
        lbllOrder.setText("");
        lblOrderPrcie.setText("");
        btnAdd.setDisable(true);
        
    }

      @FXML
    private void btnRemoveOrders(ActionEvent event) {
      removeOrders();
    }
    
     private void removeOrders() {
        
        if(boxOrder.getChildren().size()>0){
        boxOrder.getChildren().clear();
        order.getAorders().clear();
        }
        lblTotalPrice.setText("");
        totalPrice = 0;
        System.out.println("size before "+order.getAorders().size());
        
        System.out.println("size after "+order.getAorders().size());
        
          btnPlaceOrder.setDisable(true);
          tfOrderId.setText("");
          tfQuantityOrder.setText("");
          lbllOrder.setText("");
          lblOrderPrcie.setText("");
          btnRemoveOrder.setDisable(true);
          btnAdd.setDisable(true);
          lblOrderPlaced.setText("");
    }
    
    @FXML
    private void onQuantityReleased(KeyEvent event) {
       int d = 0;
        try {
            d = Integer.parseInt(tfQuantityOrder.getText());
        } catch (Exception e) {
             btnAdd.setDisable(true);
        }
        if(d>0)btnAdd.setDisable(false);
        if(p == null) btnAdd.setDisable(true);
    }

    double totalPrice = 0;
    @FXML
    private void onBtnOrderProduct(ActionEvent event) {

       int quantity = Integer.parseInt(tfQuantityOrder.getText());
        
        double price = p.getPrice()*quantity;
        totalPrice += price;
        lblTotalPrice.setText("Total: €" + String.format("%10.2f", totalPrice ));
        
         //Product p = pdb.getArrProduct().get(i);
        HBox vbox = new HBox();   
        Label lb = new Label(tfOrderId.getText());
        lb.setMinWidth(70);
        lb.setFont(new Font("Consolas", 14));
        vbox.getChildren().add(lb);
        Label lb1 = new Label(tfQuantityOrder.getText());
        lb1.setMinWidth(50);
        lb1.setFont(new Font("Consolas", 14));
        vbox.getChildren().add(lb1);
        Label lb2 = new Label(lbllOrder.getText());
        lb2.setMinWidth(410);
        lb2.setFont(new Font("Consolas", 14));
        vbox.getChildren().add(lb2);
        Label lb3 = new Label(String.format("€%10.2f", price));
        lb3.setMinWidth(100);
        lb3.setFont(new Font("Consolas", 14));
        vbox.getChildren().add(lb3);   
        boxOrder.getChildren().add(vbox);  
        
        order.add(p, quantity);
        btnRemoveOrder.setDisable(false);
        btnPlaceOrder.setDisable(false);
      
        //clean both and both labels disable button
    }
    
   
    Customer customer;
    Order order;
    @FXML
    private void btnOnCreateCustomer(ActionEvent event) {
       
        if(!tfCustName.getText().isEmpty()){
        order = new Order();
        customer = new Customer(tfCustName.getText().toUpperCase(), "");
        cust.add(customer);
        tfOrderId.setDisable(false);
        tfQuantityOrder.setDisable(false);
       }
    }

    @FXML
    private void btnOnPlaceOrder(ActionEvent event) {
        customer.setCustID(cust.getCustID(tfCustName.getText()));
        customer.setLastOrder(cust.getLastID(cust.getCustID(tfCustName.getText())));
        customer.addOrder(order, cust.getDBC());
        removeOrders();
        tfCustName.setText("");
        lblOrderPlaced.setText("Order was placed...");
        
    }
    
//Display All Order
    @FXML
    private void onGetOrders(ActionEvent event) {
        ArrayList<CustomerOrders> ar = cust.getOrders(tfNameGetOrders.getText().toUpperCase());

        int ono = 1;
        boxDisplay.getChildren().clear();
        for (int i = 0; i < ar.size(); i++) {
            CustomerOrders co = ar.get(i);
            
            if(co.getOrderNo() == ono){
                boxDisplay.getChildren().add(new Label("Order: "+ono++));
            }
            boxDisplay.getChildren().add(new Label(co.getQuantity()+"x"
                    + "  "+co.getId()+" "
                    + "  "+co.getMake()+" "
                    + "  "+co.getModel()+" "
                    + "  "+co.getSize()+" "
                    + "  "+co.getDescription()+" "
                    + "  €"+String.format("%10.2f", co.getPrice() )));
           
        }
        if(ar.size() == 0)
        boxDisplay.getChildren().add(new Label("Customer "+ tfNameGetOrders.getText().toUpperCase()
        + " has NO ORDERS!!"));    
    }

    @FXML
    private void onCustomerNameCLick(MouseEvent event) { 
        if(!tfCustName.getText().isEmpty())
            removeOrders();
        tfCustName.setText("");
    }

    @FXML
    private void onCategory(ActionEvent event) {
    }

    @FXML
    private void onMake(ActionEvent event) {
    }

    @FXML
    private void onModel(ActionEvent event) {
    }

    @FXML
    private void onSize(ActionEvent event) {
    }

    @FXML
    private void onPrice(ActionEvent event) {
    }

    @FXML
    private void onDescription(ActionEvent event) {
    }

}
