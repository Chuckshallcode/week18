package shoe.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import shoe.store.controller.model.ShoeStoreData;
import shoe.store.controller.model.ShoeStoreData.CustomerData;
import shoe.store.controller.model.ShoeStoreData.ManufacturerData;
import shoe.store.service.ShoeStoreService;

@RestController
@RequestMapping("/shoe_store")
@Slf4j
public class ShoeStoreController {
	@Autowired
	private ShoeStoreService shoeStoreService;
	
	@PostMapping("/shoeStore")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ShoeStoreData insertShoeStore (@RequestBody ShoeStoreData shoeStoreData ) {
		log.info("creating store {}", shoeStoreData);
		return shoeStoreService.saveShoeStore(shoeStoreData);
	}
	
	
	@PutMapping("/shoeStore/{shoe_store_id}")
	public ShoeStoreData updateShoeStore(@PathVariable Long shoe_store_id, @RequestBody ShoeStoreData shoeStoreData) {
		shoeStoreData.setStore_id(shoe_store_id);
		log.info("Updating store {}", shoeStoreData);
		return shoeStoreService.saveShoeStore(shoeStoreData);
}
	
	@GetMapping("/{shoe_store_id}")
	public ShoeStoreData retrieveShoeStoreById(@PathVariable Long shoe_store_id) {
		log.info("retrieving store with ID = {}", shoe_store_id);
		return shoeStoreService.retrieveShoeStoreById(shoe_store_id);
	}
	
	@GetMapping
	public List<ShoeStoreData> retrieveAllShoeStores () {
		log.info("Retrieving all stores");
		return shoeStoreService.retrieveAllShoeStores();
	}
	
	@DeleteMapping ("/shoeStore/{shoe_store_id}")
	public Map<String, String> deleteShoeStore(@PathVariable Long shoe_store_id) {
		log.info("Deleting shoe store with ID = " + shoe_store_id + ".");
		shoeStoreService.deleteShoeStore(shoe_store_id);
		
		return Map.of("message", "Store with Id =" + shoe_store_id + " was deleted.");
	}
	
	@DeleteMapping("/shoeStore")
	public void deleteAllShoeStores() {
		log.info("Attempting to delete all shoe stores");
		throw new UnsupportedOperationException("Deleting all shoe stores is not allowed.");
	}
	// End of Shoe Store controller
	
//	private ShoeStoreService customerService;
	
//	@PostMapping("/customer/{shoe_store_id}")
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public CustomerData createCustomer (@PathVariable Long shoe_store_id, @RequestBody CustomerData customerData) {
//		log.info("creating customer {} for shoe store {}", shoe_store_id, customerData);
//		return shoeStoreService.saveCustomer(customerData, shoe_store_id);
//	}
	
	// end of Shoe Store controller
	
	@PostMapping("/shoeStore/{shoe_store_id}/manufacturer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ManufacturerData insertManufacturer (@PathVariable Long shoe_store_id, @RequestBody ManufacturerData manufacturerData) {
		log.info("Creating manufacturer {} for shoe store with ID = {}", shoe_store_id, manufacturerData);
		return shoeStoreService.saveManufacturer(shoe_store_id, manufacturerData);
	}
	
	@PostMapping("/shoeStore/{shoe_store_id}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerData insertCustomer (@PathVariable Long shoe_store_id, @RequestBody CustomerData customerData) {
		log.info("Creating customer {} for shoe store with ID = {}", shoe_store_id, customerData);
		return shoeStoreService.saveCustomer(shoe_store_id, customerData);
		}
/*	
	@PutMapping("/customer/{customer_id}")
	public CustomerData updateCustomer(@PathVariable Long customer_id, Long shoe_store_id, @RequestBody CustomerData customerData) {
		customerData.setCustomer_id(customer_id);
		log.info("Updating customer {} for shoe store {}", shoe_store_id, customerData);
		return shoeStoreService.saveCustomer(customerData);
}
	
	@GetMapping("/{customer_id}")
	public CustomerData retrieveCustomer(@PathVariable Long customer_id, Long shoe_store_id) {
		log.info("retrieving customer with ID = {} for shoe store {}", customer_id);
		return customerService.retrieveCustomerById(customer_id);
	}
	
	@GetMapping
	public List<CustomerData> retrieveAllCustomers () {
		log.info("Retrieving all customers");
		return customerService.retrieveAllCustomers();
	}
	
	@DeleteMapping ("/customer/{customer_id}")
	public Map<String, String> deleteCustomer(@PathVariable Long customer_id) {
		log.info("Deleting customer with ID = " + customer_id + ".");
		customerService.deleteCustomer(customer_id);
		
		return Map.of("message", "customer Id =" + customer_id + " was deleted.");
	}
	// end of customer controller
	
	private ShoeStoreService manufacturerService;
	@PostMapping("/manufacturer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ManufacturerData createManufacturer (@RequestBody ManufacturerData manufacturerData ) {
		log.info("creating manufacturer {}", manufacturerData);
		return manufacturerService.saveManufacturer(manufacturerData);
	}
	
	@PutMapping("/manufacturer/{manufacturer_id}")
	public ManufacturerData updateManufacturer(@PathVariable Long manufacturer_id, @RequestBody ManufacturerData manufacturerData) {
		manufacturerData.setManufacturer_id(manufacturer_id);
		log.info("Updating manufacturer {}", manufacturerData);
		return manufacturerService.saveManufacturer(manufacturerData);
  }
	@GetMapping("/{manufacturer_id}")
	public ManufacturerData retrieveManufacturer(@PathVariable Long manufacturer_id) {
		log.info("manufacturer store with ID = {}", manufacturer_id);
		return manufacturerService.retrieveManufacturerById(manufacturer_id);
	}
	
	@GetMapping
	public List<ManufacturerData> retrieveAllManufacturer () {
		log.info("Retrieving all manufacturers");
		return manufacturerService.retrieveAllManufacturers();
	}
	
	@DeleteMapping ("/shoeStore/{shoe_store_id}")
	public Map<String, String> deleteManufacturer(@PathVariable Long manufacturer_id) {
		log.info("Deleting manufacturer with ID = " + manufacturer_id + ".");
		manufacturerService.deleteManufacturer(manufacturer_id);
		return Map.of("message", "Manufacturer with Id =" + manufacturer_id + " was deleted.");
	}
	*/
}
