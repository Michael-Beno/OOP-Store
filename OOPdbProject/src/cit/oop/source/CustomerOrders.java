/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cit.oop.source;

/**
 *
 * @author Mick
 */
public class CustomerOrders{
        private int quantity;
        private int id;
        private String category;
        private String make;
        private String model;
        private String description;
        private double price;
        private int orderNo;
        private String size;
      
        public void setQuantity(int aInt) { quantity = aInt;}
        public int getQuantity(){return quantity;}
        public void setId(int aInt) { id = aInt;}
        public int getId() { return id;}
        public void setCategory(String string) { category = string;}
        public String getCategory(){return category;}
        public void setMake(String string) { make = string;}
        public String getMake(){return make;}
        public void setModel(String string) { model = string;}
        public String getModel(){return model;}
        public void setSize(String string) { size = string; }
        public String getSize(){return size;}
        public void setDescription(String string) { description = string;}
        public String getDescription(){return description;}
        public void setPrice(double aDouble) { price = aDouble; }
        public double getPrice(){return price;}
        public void setOrderNo(int aInt) { orderNo = aInt;}
        public int getOrderNo(){return orderNo;}

  }  
