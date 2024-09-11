package shoe.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import shoe.store.entity.Manufacturer;
public interface ManufacturerDao extends JpaRepository<Manufacturer, Long> {
	



}