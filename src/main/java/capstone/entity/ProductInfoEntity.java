/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Products Info
 * Thông tin các hàng hóa
 * @author Tuna
 *
 */

@SuperBuilder(toBuilder = true)
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
	@JsonProperty
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
	@JsonProperty
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
	@JsonProperty
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
	@JsonProperty
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
	@JsonProperty
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
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param name
	 * @param productInfos
	 */
	public ProductInfoEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted,
			@NonNull @NotNull @NotBlank(message = "must not be empty") String name, Set<ProductInfo> productInfos) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name);
		this.productInfos = productInfos;
	}

}
