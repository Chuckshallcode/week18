package shoe.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import shoe.store.entity.Customer;
public interface CustomerDao extends JpaRepository<Customer, Long> {
	

}