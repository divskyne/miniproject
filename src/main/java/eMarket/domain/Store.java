/**
 * (C) Artur Boronat, 2016
 */
package eMarket.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
	List<Product> productList = new ArrayList<>();
	List<Order> orderList = new ArrayList<>();
	Map<String,String> allOrders=new HashMap<>();
	List<AllOrder> totalOrders = new ArrayList<>();
	
	public void init() {
		productList = new ArrayList<>();
		orderList = new ArrayList<>();
		Product.lastId=0;
		Order.lastId=0;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public List<Product> getProductList() {
		return productList;
	}
	public List<String> getProductNames() {
		ArrayList<String> productNames=new ArrayList<>();
		for(Product product:productList){
			productNames.add(product.getName());
		}
		return productNames;
	}
	
	
	
	public List<AllOrder> getTotalOrders(){
		totalOrders.clear();
		
		if(getAllOrders().size()>0){
			for(String date:getAllOrders().keySet()){
				AllOrder allorder=new AllOrder();
				allorder.setId();
				allorder.setDate(date);
				allorder.setDescription(getAllOrders().get(date));
				String []names=getAllOrders().get(date).split(" ");
				double sumCost=0;
				for (String order:names){
					Order ord=getOrderByName(order);
					if(ord!=null){
						sumCost+=ord.getCost();	
					}
					
				}
				allorder.setCost(sumCost);
				totalOrders.add(allorder);
			}
		}
		
		return totalOrders;
	}
	
	
	public Product getProductByName(String name) {
		
		for(Product product:productList){
			if(product.getName().equals(name)){
				return product;
			}
		}
		return null;
	}
	
   public Order getOrderByName(String name) {
		
		for(Order order:orderList){
			if(order.getName().equals(name)){
				return order;
			}
		}
		return null;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Map<String, String> getAllOrders() {
		return allOrders;
	}

	public void setAllOrders(Map<String, String> allOrders) {
		this.allOrders = allOrders;
	}
	
}
