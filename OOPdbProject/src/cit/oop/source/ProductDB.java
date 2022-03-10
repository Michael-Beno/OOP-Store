package cit.oop.source;


import java.util.ArrayList;


/**ProductDB class is database of products</br>
 * The product numbers could be like 1001, 2011, 3021...
 * @author Michael Beno
 * @version 20/11/2017*/
public class ProductDB {

	private ArrayList<Product> aProduct;
	public DatabaseConnect dbc;
	/**Constructor of ProductDB <br>
	 * which stores all products*/
	public ProductDB() {
		aProduct = new ArrayList<Product>();
                  dbc = new DatabaseConnect();
	}	
	/**getter for all array*/
	public ArrayList<Product> getArrProduct() {
            aProduct = dbc.displayAllProducts();
            setArrProduct(aProduct);
            return aProduct;
        }
	/**setter for all array*/
	public void setArrProduct(ArrayList<Product> aProduct) {
            this.aProduct = aProduct;}

	/**adding single product to Array*/
	public void add(Product product) {
		aProduct.add(product);
                
     dbc.createProduct(product.getCategory(), product.getMake(), product.getModel(),
                 product.getSize(), product.getDescription(), product.getPrice());
	}
	/**finding Product from array by ID<br>
	 * return <b>null</b> if product not found
	 * @return Product*/
	public Product find(int productId) {
                getArrProduct();
		for (int i = 0; i < aProduct.size(); i++) {
			Product foa = aProduct.get(i);
			if(foa.getProductID() == productId)
				return foa;
		}
		return null;
	}
	/**removing Product from array by ID<br>
	 * return <b>false</b> if product not found and wasn't removed<br>
	 * Otherwise returns <b>true</b>
	 * @return boolean*/
	public boolean remove(int productId) {
		for (int i = 0; i < aProduct.size(); i++) {
			if(aProduct.get(i).getProductID() == productId) {
				aProduct.remove(i);
				return true;
			}
		}
		return false;
	}
	
}
