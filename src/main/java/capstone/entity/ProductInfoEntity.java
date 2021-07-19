/**
 * 
 */
package capstone.entity;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.MappedSuperclass;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty;

=======
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Products Info
 * Thông tin các hàng hóa
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@MappedSuperclass
public abstract class ProductInfoEntity extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Thông tin từng hàng hóa
	 */
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productInfoEntity")
	protected Set<ProductInfo> productInfos;
	
	/**
	 * Số lượng
	 * @return
	 */
<<<<<<< HEAD
	@JsonProperty
=======
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
	public Integer amount() {
		if (Objects.nonNull(this.productInfos)) {
			this.productInfos.stream().map(ProductInfo::getAmount);
			return this.productInfos.stream().mapToInt(ProductInfo::getAmount).sum();
		}
		return null;
	}
	
	/**
	 * Thành tiền
	 * @return
	 */
<<<<<<< HEAD
	@JsonProperty
=======
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
	public Long totalPrice() {
		if (Objects.nonNull(this.productInfos)) {
			return this.productInfos.stream().mapToLong(ProductInfo::totalPrice).sum();
		}
		return null;
	}
	
	/**
	 * Tiền chiết khấu
	 * @return
	 */
<<<<<<< HEAD
	@JsonProperty
=======
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
	public Long discountMoney() {
		if (Objects.nonNull(this.productInfos)) {
			return this.productInfos.stream().mapToLong(ProductInfo::discountMoney).sum();
		}
		return null;
	}
	
	/**
	 * Tiền thuế
	 * @return
	 */
<<<<<<< HEAD
	@JsonProperty
=======
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
	public Long vatMoney() {
		if (Objects.nonNull(this.productInfos)) {
			return this.productInfos.stream().mapToLong(ProductInfo::vatMoney).sum();
		}
		return null;
	}
	
	/**
	 * Tổng tiền
	 * @return
	 */
<<<<<<< HEAD
	@JsonProperty
=======
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
	public Long totalMoney() {
		if (Objects.nonNull(this.productInfos)) {
			return this.productInfos.stream().mapToLong(ProductInfo::totalMoney).sum();
		}
		return null;
	}

	/**
	 * @param productInfos the productInfos to set
	 */
	public abstract void setProductInfos(Set<ProductInfo> productInfos);
	
	public abstract void addToProductInfos(ProductInfo productInfo);

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param productInfos
	 */
	public ProductInfoEntity(Long id, Date createdAt, Date updatedAt, User createdBy, User updatedBy, String name,
			Set<ProductInfo> productInfos) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.productInfos = productInfos;
	}

}
