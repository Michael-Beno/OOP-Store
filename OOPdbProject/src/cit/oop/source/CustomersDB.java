
package cit.oop.source;


import java.util.ArrayList;



/**
 *
 * @author Mick
 */
public class CustomersDB {

    private ArrayList<Customer> arrCust;
    private DatabaseConnect dbc;
     
    public CustomersDB(){
        arrCust = new ArrayList<>();
        dbc = new DatabaseConnect();
    }
    
    public void add(Customer c) {
        arrCust = getCustomers();
       
        System.out.println(custID);
        if(custID<0){
            arrCust.add(c);
            dbc.addCustomer(c);
        }
    // System.out.println("clicked" + c.getName());
    }

    private ArrayList<Customer> getCustomers() {
        ArrayList<Customer> arr = dbc.getArrCustomers();
        return arr;
    }

    public void reloadList() {
        arrCust = getCustomers();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int custID = -1;
    public int getCustID(String name) {
       custID = -1;
        for (int i = 0; i < arrCust.size(); i++) {
            System.out.println(""+arrCust.get(i).getName()+"");
            
            if(name.toUpperCase()
                    .equals(arrCust.get(i).getName().toUpperCase()) )
                custID = i+1;
        }
        return custID;
    }

    public ArrayList<CustomerOrders> getOrders(String name) {
        return dbc.getOrdes(name);
   
    }

    public int getLastID(int custommerID) {
       return dbc.getLastId(custommerID); 
    }

    public DatabaseConnect getDBC() {
        return dbc;

    }
    
}
