package shoe.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class ShoeStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long store_id;
	private String store_name;
	private String email;


@ToString.Exclude
@EqualsAndHashCode.Exclude
@ManyToMany(cascade = CascadeType.PERSIST)
@JoinTable(name = "customers_shoe_store", joinColumns = @JoinColumn(name = "store_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
private Set<Customer> customers = new HashSet<>();

@ToString.Exclude
@EqualsAndHashCode.Exclude
@OneToMany(mappedBy = "shoeStore", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<Manufacturer> manufacturers = new HashSet<>();

}