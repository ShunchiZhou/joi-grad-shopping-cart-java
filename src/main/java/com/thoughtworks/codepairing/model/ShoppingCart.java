package com.thoughtworks.codepairing.model;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart {
    private List<Product> products;
    private Customer customer;

    public ShoppingCart(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Order checkout() {
        double totalPrice = 0;

        int loyaltyPointsEarned = 0;
        Map<String,prodcut> map=new HashSet<>();
        for (Product product : products) {
            double discount = 0;
            if (product.getProductCode().startsWith("DIS_10")) {
                discount = (product.getPrice() * 0.1);
                loyaltyPointsEarned += (product.getPrice() / 10);
            } else if (product.getProductCode().startsWith("DIS_15")) {
                discount = (product.getPrice() * 0.15);
                loyaltyPointsEarned += (product.getPrice() / 15);
            } else if(product.getProductCode().startsWith("DIS_20")){
            	discount = (product.getPrice() * 0.2);
                loyaltyPointsEarned += (product.getPrice() / 20);
            }else if(product.getProductCode().startsWith("BULK_BUY_2_GET_1")){
            	if(products.size()==2) {
            		discount = product.getPrice()*0.33;
            	}
            }else if(products.size()>=2) {        	
            	map.put(product.getProductCode(),product);
            }
            else {
                loyaltyPointsEarned += (product.getPrice() / 5);
            }
            for(Map.Entry<> a:map) {
            	
            }
            totalPrice += product.getPrice() - discount;
        }

        return new Order(totalPrice, loyaltyPointsEarned);
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + "\n" + "Bought:  \n" + products.stream().map(p -> "- " + p.getName()+ ", "+p.getPrice()).collect(Collectors.joining("\n"));
    }
}
