package shoe.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import shoe.store.entity.Customer;
import shoe.store.entity.Manufacturer;
import shoe.store.entity.ShoeStore;

@Data
@NoArgsConstructor
public class ShoeStoreData {
		private Long store_id;
		private String store_name;
		private String email;
		private Set<CustomerData> customers = new HashSet<>();
		private Set<ManufacturerData> manufacturers = new HashSet<>();
		
public ShoeStoreData(ShoeStore shoeStore) {
		this.store_id = shoeStore.getStore_id();
		this.store_name = shoeStore.getStore_name();
		this.email = shoeStore.getEmail();
		
		for (Customer customer : shoeStore.getCustomers()) {
			this.customers.add(new CustomerData(customer));
		}
}

public ShoeStoreData(Long store_id, String store_name, String email) {
	this.store_id = store_id;
	this.store_name = store_name;
	this.email = email;
}

public ShoeStore toShoeStore () {
	ShoeStore shoeStore = new ShoeStore();
	
	shoeStore.setStore_id(store_id);
	shoeStore.setStore_name(store_name);
	shoeStore.setEmail(email);
	
	for(CustomerData customerData: customers) {
		shoeStore.getCustomers().add(customerData.toCustomers());	}
	return shoeStore;
}	
	

@Data
@NoArgsConstructor
public static class CustomerData {
	private Long customer_id;
	private String foot_size;
	private String phone;
	private String customer_name;


public CustomerData (Customer customer) {
	this.customer_id = customer.getCustomer_id();
	this.foot_size = customer.getFoot_size();
	this.phone = customer.getPhone();
	this.customer_name = customer.getCustomer_name();
}

public CustomerData (Long customerId, String footSize, String phone, String customerName) {
	this.customer_id = customerId;
	this.foot_size = footSize;
	this.phone = phone;
	this.customer_name = customerName;
	
}

public Customer toCustomers() {
	Customer customer = new Customer();
	
	customer.setCustomer_id(customer_id);
	customer.setFoot_size(foot_size);
	customer.setPhone(phone);
	customer.setCustomer_name(customer_name);
	
	return customer;
	}
}

@Data
@NoArgsConstructor
public static class ManufacturerData {
	private Long manufacturer_id;
	private String city;
	private String state;


public ManufacturerData (Manufacturer manufacturer) {
	this.manufacturer_id = manufacturer.getManufacturer_id();
	this.city = manufacturer.getCity();
	this.state = manufacturer.getState();
		}

public ManufacturerData (Long manufacturer_id, String city, String state) {
	this.manufacturer_id = manufacturer_id;
	this.city = city;
	this.state = state;
}

public Manufacturer toManufacturer() {
	Manufacturer manufacturer = new Manufacturer();
	
	manufacturer.setManufacturer_id(manufacturer_id);
	manufacturer.setCity(city);
	manufacturer.setState(state);
	return manufacturer;
}
}
}
