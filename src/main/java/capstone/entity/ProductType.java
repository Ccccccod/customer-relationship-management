/**
 * 
 */
package capstone.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Loại hàng hóa
 * @author Tuna
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "ProductType", //
		uniqueConstraints = { //
		})
public class ProductType extends CodedNamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Loại hàng hóa
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_type_id")
	private ProductType productType;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productType")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<ProductType> productTypes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productType")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Product> products;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param code
	 * @param productType
	 * @param productTypes
	 * @param products
	 */
	@Builder
	public ProductType(Long id, Date createdAt, Date updatedAt, User createdBy, User updatedBy, String name,
			String code, ProductType productType, Set<ProductType> productTypes, Set<Product> products) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name, code);
		this.productType = productType;
		this.productTypes = productTypes;
		this.products = products;
	}

	/**
	 * @param name
	 * @param code
	 */
	public ProductType(String name, String code) {
		super(name, code);
	}

}
