package shoe.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Manufacturer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long manufacturer_id;
	private String city;
	private String state;
	//private String store_id;
	
@EqualsAndHashCode.Exclude
@ToString.Exclude
@ManyToOne (cascade = CascadeType.PERSIST)
@JoinColumn(name = "shoe_store_id")
private ShoeStore shoeStore;
	
}
