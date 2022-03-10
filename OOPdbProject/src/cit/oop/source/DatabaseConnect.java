package cit.oop.source;



import cit.oop.source.Customer;
import cit.oop.source.CustomerOrders;
import cit.oop.source.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Beno
 */
class DatabaseConnect {
    
    
    private Connection conn = null;
    //Constructor
    public DatabaseConnect() {
         try {
            // db parameters
            String url = "jdbc:sqlite:sample.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    
    //category  PHONE,TV...
    //make (brand) - Sony, Samsung
    //model experia, galaxy
    //size 50", 16GB,
    //description 3D capable
    void createProduct(String category, String make, String model,String size, String description, double price) {
          String sql = "INSERT INTO products(category,make,model,size,price,description) VALUES(?,?,?,?,?,?)";
            
             PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category);
            pstmt.setString(2, make);
            pstmt.setString(3, model);
            pstmt.setString(4, size);
            pstmt.setDouble(5, price);
            pstmt.setString(6, description);
            pstmt.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ArrayList<Product> displayAllProducts() {
    ArrayList<Product> ar = new ArrayList<Product>();
        try {
            String sql = "SELECT * FROM products";
            
            
//            // db parameters
            String url = "jdbc:sqlite:sample.db";
//            // create a connection to the database
            conn = DriverManager.getConnection(url);
//            
            System.out.println("Connection to SQLite has been established.");
            
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("category") + "\t" +
                        rs.getString("make") + "\t" +
                        rs.getString("model") + "\t" +
                        rs.getString("size") + "\t" +
                        rs.getString("description") + "\t" +
                        rs.getDouble("price"));
                
                Product p = new Product();
                p.setProductID(rs.getInt("id"));
                p.setCategory(rs.getString("category"));
                 p.setMake(rs.getString("make"));
                 p.setModel(rs.getString("model"));
                 p.setSize(rs.getString("size"));
                 p.setDescription(rs.getString("description"));
                 p.setPrice(rs.getDouble("price"));
                 
                ar.add(p);
            }
            
          
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return ar;
    }

    void addCustomer(Customer c) {
        
        System.out.println("  "+c.getName());
           String sql = "INSERT INTO customers(name,address) VALUES(?,?)";
            
             PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c.getName());
            pstmt.setString(2, c.getAddress());
            pstmt.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println(ex.toString());
           // Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    ArrayList<Customer> getArrCustomers() { 
        ArrayList<Customer> ar = new ArrayList<Customer>();
        try {
            String sql = "SELECT * FROM customers";
            
            
//            // db parameters
            String url = "jdbc:sqlite:sample.db";
//            // create a connection to the database
            conn = DriverManager.getConnection(url);
//            
            System.out.println("Connection to SQLite has been established.");
            
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                
                System.out.println(rs.getInt("custID") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("address"));
                
                Customer c = new Customer();
                        c.setCustID( rs.getInt("custID") );
                        c.setName(rs.getString("name") );
                        c.setAddress(rs.getString("address"));
                 
                ar.add(c);
            }
            
          
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return ar;
    }

    ArrayList<CustomerOrders> getOrdes(String name) {
          ArrayList<CustomerOrders> ar = new ArrayList<CustomerOrders>();
         
        try {
            String sql = "select orders.quantity, products.id, products.category, products.make, \n" +
                "products.model, products.size, products.description, (products.price * orders.quantity) as price, orders.orderNo\n" +
                "from customers, products, orders\n" +
                "where customers.custID = orders.custId\n" +
                "and orders.id = products.id\n" +
                "and name = '"+name+"'";
            
            
//            // db parameters
            String url = "jdbc:sqlite:sample.db";
//            // create a connection to the database
            conn = DriverManager.getConnection(url);
//            
            System.out.println("Connection to SQLite has been established.");
            
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                
                System.out.println(rs.getInt("quantity") +  "\t" +
                        rs.getInt("id") + "\t" +
                        rs.getString("category")+ "\t" +
                         rs.getString("make")+ "\t" +
                         rs.getString("model")+ "\t" +
                         rs.getString("size")+ "\t" +
                         rs.getString("description")+ "\t" +
                         rs.getDouble("price")+ "\t" +
                        rs.getInt("orderNo")
                
                );
                 CustomerOrders co = new CustomerOrders();
                        co.setQuantity(rs.getInt("quantity"));
                        co.setId(rs.getInt("id"));
                        co.setCategory(rs.getString("category"));
                        co.setMake(rs.getString("make"));
                        co.setModel(rs.getString("model"));
                        co.setSize(rs.getString("size"));
                        co.setDescription(rs.getString("description"));
                        co.setPrice(rs.getDouble("price"));
                        co.setOrderNo(rs.getInt("orderNo"));
               
               ar.add(co);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return ar; 
    }

    int getLastId(int custommerID) {
        int lastOrder = 0;
        try {
            String sql = "select orderNo\n" +
                         "from orders\n" +
                         "where custId = "+custommerID+" group by custId order by (orderNo) desc";
            
            
//            // db parameters
            String url = "jdbc:sqlite:sample.db";
//            // create a connection to the database
            conn = DriverManager.getConnection(url);
//            
            System.out.println("Connection to SQLite has been established.");
            
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                
                System.out.println(rs.getInt("orderNo"));
                 lastOrder = rs.getInt("orderNo");
                

            }
            
          
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return lastOrder;   
    }

    void putOrders(int productID, int quantity, int custID, int orderNo) {
        String sql = "INSERT INTO orders(id,quantity,custId,orderNo) VALUES(?,?,?,?)";
            
             PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productID);
            pstmt.setInt(2, quantity);
            pstmt.setInt(3, custID);
            pstmt.setInt(4, orderNo);
            pstmt.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
  
}
