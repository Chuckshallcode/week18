package shoe.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shoe.store.controller.model.ShoeStoreData;
import shoe.store.controller.model.ShoeStoreData.CustomerData;
import shoe.store.controller.model.ShoeStoreData.ManufacturerData;
import shoe.store.dao.CustomerDao;
import shoe.store.dao.ManufacturerDao;
import shoe.store.dao.ShoeStoreDao;
import shoe.store.entity.Customer;
import shoe.store.entity.Manufacturer;
import shoe.store.entity.ShoeStore;

@Service
public class ShoeStoreService {
	
	@Autowired
	private ShoeStoreDao shoeStoreDao;
	
	@Transactional(readOnly = false)
	public ShoeStoreData saveShoeStore (ShoeStoreData shoeStoreData) {
		Long shoe_store_id = shoeStoreData.getStore_id();
		ShoeStore shoeStore = findOrCreateShoeStore(shoe_store_id);
		
		copyShoeStoreFields(shoeStore, shoeStoreData);
		shoeStoreDao.save(shoeStore);
		return new ShoeStoreData(shoeStore);
	}
	
	public void copyShoeStoreFields(ShoeStore shoeStore, ShoeStoreData shoeStoreData) {
		shoeStore.setStore_id(shoeStoreData.getStore_id());;
		shoeStore.setStore_name(shoeStoreData.getStore_name());
		shoeStore.setEmail(shoeStoreData.getEmail());
	}
	
	private ShoeStore findShoeStoreById(Long shoe_store_id) {
		return shoeStoreDao.findById(shoe_store_id).orElseThrow(() -> new NoSuchElementException("Shoe store with ID=" + shoe_store_id + " wasn't found.")); 
	}
	
	@Transactional
	public ShoeStoreData retrieveShoeStoreById(Long shoe_store_id) {
		ShoeStore shoeStore = findShoeStoreById(shoe_store_id);
		return new ShoeStoreData(shoeStore);
	}
	
	private ShoeStore findOrCreateShoeStore (Long shoe_store_id) {
		ShoeStore shoeStore;
		
		if (Objects.isNull(shoe_store_id)) {
			shoeStore = new ShoeStore();
		} else {
			shoeStore = findShoeStoreById(shoe_store_id);
		}
		return shoeStore;
	}
	
	public List<ShoeStoreData> retrieveAllShoeStores() {
		List<ShoeStore> shoeStores = shoeStoreDao.findAll();
		List<ShoeStoreData> response = new LinkedList<>();
		
		for(ShoeStore shoeStore : shoeStores) {
			response.add(new ShoeStoreData(shoeStore));
		}
		return response;
	}
	
	@Transactional(readOnly = false)
	public void deleteShoeStore(long shoe_store_id) {
		ShoeStore shoeStore = findShoeStoreById(shoe_store_id);
		shoeStoreDao.delete(shoeStore);
	}
	// end of Shoe Store service

@Autowired
public CustomerDao customerDao;
	
	@Transactional(readOnly = false)
	public CustomerData saveCustomer (Long shoe_store_id, CustomerData customerData) {
		ShoeStore shoeStore = findShoeStoreById(shoe_store_id);
		Long customer_id = customerData.getCustomer_id();
		Customer customer = findOrCreateCustomer(customer_id, shoe_store_id);
		copyCustomerFields(customer, customerData);
		customer.getShoeStores().add(shoeStore);
		shoeStore.getCustomers().add(customer);
		customerDao.save(customer);
		return new CustomerData(customer);
	}
	
	private void copyCustomerFields(Customer customer, CustomerData customerData) {
		customer.setCustomer_id(customerData.getCustomer_id());
		customer.setFoot_size(customerData.getFoot_size());
		customer.setPhone(customerData.getPhone());
		customer.setCustomer_name(customerData.getCustomer_name());;
	}
	
	private Customer findOrCreateCustomer (Long customer_id, Long shoe_store_id) {
		Customer customer;
		
		if (Objects.isNull(customer_id)) {
			customer = new Customer();
		} else {
			customer = findCustomerById(customer_id, shoe_store_id);
			}
			return customer;
		}

	private Customer findCustomerById(Long customer_id, Long shoe_store_id) {
		Customer customer = customerDao.findById(customer_id).orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customer_id + " wasn't found.")); 
		
		boolean found = false;
		
		for (ShoeStore shoeStore : customer.getShoeStores()) {
			if (shoeStore.getStore_id() == shoe_store_id) {
				found = true;
				break;
			}
		}
		
		if (!found) {
			throw new IllegalArgumentException("The customer with ID= " + customer_id + " was not found.");
		}
		return customer;
	}
	
	
	
	
	public List<CustomerData> retrieveAllCustomers() {
		List<Customer> customerEntities = customerDao.findAll();
		List<CustomerData> customerDtos = new LinkedList<>();
		
		for(Customer customer : customerEntities) {
			CustomerData customerData = new CustomerData(customer);
			customerDtos.add(customerData);
		}
		return customerDtos;
	}
	
/*	@Transactional(readOnly = false)
	public void deleteCustomer(long customer_id) {
		Customer customer = findCustomerById(customer_id);
		customerDao.delete(customer);
	} 
*/	
	//end of customer service
	
	@Autowired
	private ManufacturerDao manufacturerDao;
	
	@Transactional(readOnly = false)
	public ManufacturerData saveManufacturer (Long shoe_store_id, ManufacturerData manufacturerData) {
		ShoeStore shoeStore = findShoeStoreById(shoe_store_id);
		Long manufacturer_id = manufacturerData.getManufacturer_id();
		Manufacturer manufacturer = findOrCreateManufacturer(manufacturer_id, shoe_store_id);
		
		copyManufacturerFields(manufacturer, manufacturerData);
		manufacturer.setShoeStore(shoeStore);
		shoeStore.getManufacturers().add(manufacturer);
		manufacturerDao.save(manufacturer);
		return new ManufacturerData(manufacturer);
}
	private Manufacturer findOrCreateManufacturer(Long manufacturer_id, Long shoe_store_id) {
		Manufacturer manufacturer;
		
		if (Objects.isNull(manufacturer_id)) {
			manufacturer = new Manufacturer();
		} else {
			manufacturer = findManufacturerById(manufacturer_id, shoe_store_id);
		}
		return manufacturer;
	}

	private void copyManufacturerFields(Manufacturer manufacturer, ManufacturerData manufacturerData) {
		manufacturer.setManufacturer_id(manufacturerData.getManufacturer_id());
		manufacturer.setCity(manufacturerData.getCity());
		manufacturer.setState(manufacturerData.getState());
	}

/*	@Transactional
	public ManufacturerData retrieveManufacturerById(Long manufacturer_id) {
		Manufacturer manufacturer = findManufacturerById(manufacturer_id);
		return new ManufacturerData(manufacturer);
	}
*/	
	private Manufacturer findManufacturerById(Long manufacturer_id, Long shoe_store_id) { // make like findEmployeeById
		Manufacturer manufacturer = manufacturerDao.findById(manufacturer_id).orElseThrow(() -> new NoSuchElementException("Manufacturer with ID=" + manufacturer_id + " wasn't found.")); 
		if (manufacturer.getShoeStore().getStore_id() != shoe_store_id) {
			throw new IllegalArgumentException("manufacturer with this ID " + manufacturer_id + " does not work with this store.");
		}
		return manufacturer;
	}
/*	
	public List<ManufacturerData> retrieveAllManufacturers() {
		List<Manufacturer> manufacturerEntities = manufacturerDao.findAll();
		List<ManufacturerData> manufacturerDtos = new LinkedList<>();
		
		for(Manufacturer manufacturer : manufacturerEntities) {
			ManufacturerData manufacturerData = new ManufacturerData(manufacturer);
			manufacturerDtos.add(manufacturerData);
		}
		return manufacturerDtos;
	}

	@Transactional(readOnly = false)
	public void deleteManufacturer(long manufacturer_id) {
		Manufacturer manufacturer = findManufacturerById(manufacturer_id);
		manufacturerDao.delete(manufacturer);
	}
*/
}
