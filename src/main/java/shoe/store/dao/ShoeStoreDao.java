package shoe.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import shoe.store.entity.ShoeStore;
public interface ShoeStoreDao extends JpaRepository<ShoeStore, Long> {
	



}