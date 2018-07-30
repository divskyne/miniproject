
package eMarket.controller;
/**
 * (C) Artur Boronat, 2015
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eMarket.EMarketApp;
import eMarket.domain.Order;
import eMarket.domain.Product;

@Controller
@RequestMapping("/order")
public class OrderController {
	int lastOrderId=0;

    @RequestMapping("/")
    public String index(Model model) {
   
    	model.addAttribute("orderList", EMarketApp.getStore().getTotalOrders());
        return "form/allOrders";
    }
    @RequestMapping("/newOrder")
    public String newOrder(Model model) {
    	model.addAttribute("orderList", EMarketApp.getStore().getOrderList());
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-M-dd");
    	String date = sdf.format(new Date());
    	model.addAttribute("date",date);
    	model.addAttribute("id",lastOrderId);
        return "form/newOrder";
    }
    
    @RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
    public String orderDetail(Model model,@ModelAttribute("order") Order order, @RequestParam(value="orderId", required=false, defaultValue="-1") int orderId) {
    	if (orderId >= 0) {
    		// modify
    		Order od = EMarketApp.getStore().getOrderList().stream().filter(o -> (((Order)o).getId() == orderId)).findAny().get();
    		order.setId(od.getId());
    		if (od.getName().equals("")) 
    			throw new SpringException("Name is empty.");
    		order.setName(od.getName());
    		order.setAmount(od.getAmount());
    		if (od.getPrice() < 0.0) 
    			throw new SpringException("Value is negative.");
    		order.setPrice(od.getPrice());
    	} else {
    		// add
    		
    		order.setId();
    		
    	}
    	model.addAttribute("productName", EMarketApp.getStore().getProductNames());
    	return "form/orderDetail";
    }   
    
    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("order") Order order, Model model) {
    	if (order.getAmount() < 0.0) 
			throw new SpringException("Order Amount is negative.");
		if (order.getName().equals("")) 
			throw new SpringException("Name is empty.");    	
		Product product=EMarketApp.getStore().getProductByName(order.getName());
		order.setDescription(product.getDescription());
		order.setPrice(product.getPrice());
		order.setCost(product.getPrice()*order.getAmount());
		
    	EMarketApp.getStore().getOrderList().removeIf(o -> (o.getId() == order.getId()));
    	EMarketApp.getStore().getOrderList().add(order);
    	lastOrderId=order.getId();
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-M-dd");
    	String date = sdf.format(new Date());
    	if(EMarketApp.getStore().getAllOrders().containsKey(date)){
    		//key exist
    		String value=EMarketApp.getStore().getAllOrders().get(date);
    		value=value+", "+order.getName();
    		EMarketApp.getStore().getAllOrders().put(date, value);
    		
    	}else{
    		EMarketApp.getStore().getAllOrders().put(date, order.getName());
    	}
    	
    	model.addAttribute("date",date);
    	model.addAttribute("id",lastOrderId);
   		
    	model.addAttribute("orderList", EMarketApp.getStore().getOrderList());
    	
        return "form/newOrder";
    } 
    
    @RequestMapping(value = "/additem", method = RequestMethod.GET)
    public String getOrder(@ModelAttribute("order") Order order, Model model) {
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-M-dd");
    	String date = sdf.format(new Date());
    	model.addAttribute("date",date);
    	model.addAttribute("id",lastOrderId);
        return "form/newOrder";
    } 

    @RequestMapping(value = "/deleteitem", method = RequestMethod.GET)
    public String deleteItem(@RequestParam(value="orderId", required=false, defaultValue="-1") int orderId, Model model) {
    	EMarketApp.getStore().getOrderList().removeIf(o -> (o.getId() == orderId));
    	model.addAttribute("orderList", EMarketApp.getStore().getOrderList());
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-M-dd");
    	String date = sdf.format(new Date());
    	model.addAttribute("date",date);
    	model.addAttribute("id",lastOrderId);
    	return "form/newOrder";
    } 
    @RequestMapping(value = "/deleteorder", method = RequestMethod.GET)
    public String deleteOrder(@RequestParam(value="date" ) String date, Model model) {
    	
    	EMarketApp.getStore().getAllOrders().remove(date.trim());
    	model.addAttribute("orderList", EMarketApp.getStore().getTotalOrders());
    	return "form/allOrders";
    }
    
    
    
    
    
}
