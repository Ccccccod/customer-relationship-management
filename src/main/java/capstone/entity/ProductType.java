/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.ProductTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Loại hàng hóa
 * @author Tuna
 */

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	@JsonSerialize(using = ProductTypeSerializer.class)
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
	
	public Long getParentId() {
		if (null == productType)
			return null;
		return productType.id;
	}

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param name
	 * @param code
	 * @param productType
	 * @param productTypes
	 * @param products
	 */
	public ProductType(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted,
			@NonNull @NotNull @NotBlank(message = "must not be empty") String name,
			@NonNull @NotNull(message = "must not be null") @NotBlank(message = "must not be blank") String code,
			ProductType productType, Set<ProductType> productTypes, Set<Product> products) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name, code);
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
