/**
 * 
 */
package capstone.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import capstone.entity.ProductInfo;

/**
 * ProductInfoed
 * @author Tuna
 *
 */
public interface ProductInfoed {
	
	/**
	 * Số lượng
	 * @return
	 */
	@JsonProperty
	default Integer amount() {
		if (Objects.nonNull(this.getProductInfos())) {
			return this.getProductInfos().stream().mapToInt(ProductInfo::getAmount).sum();
		}
		return null;
	}
	
	/**
	 * Thành tiền
	 * @return
	 */
	@JsonProperty
	default Long totalPrice() {
		if (Objects.nonNull(this.getProductInfos())) {
			return this.getProductInfos().stream().mapToLong(ProductInfo::totalPrice).sum();
		}
		return null;
	}
	
	/**
	 * Tiền chiết khấu
	 * @return
	 */
	@JsonProperty
	default Long discountMoney() {
		if (Objects.nonNull(this.getProductInfos())) {
			return this.getProductInfos().stream().mapToLong(ProductInfo::discountMoney).sum();
		}
		return null;
	}
	
	/**
	 * Tiền thuế
	 * @return
	 */
	@JsonProperty
	default Long vatMoney() {
		if (Objects.nonNull(this.getProductInfos())) {
			return this.getProductInfos().stream().mapToLong(ProductInfo::vatMoney).sum();
		}
		return null;
	}
	
	/**
	 * Tổng tiền
	 * @return
	 */
	@JsonProperty
	default Long totalMoney() {
		if (Objects.nonNull(this.getProductInfos())) {
			return this.getProductInfos().stream().mapToLong(ProductInfo::totalMoney).sum();
		}
		return null;
	}
	
	default Long getMoneyAmount() {
		return totalMoney();
	}
	
	Set<ProductInfo> getProductInfos();

	/**
	 * @param productInfos the productInfos to set
	 */
	void setProductInfos(Set<ProductInfo> productInfos);

	/**
	 * set productInfo's {@link ProductInfoed} to this
	 * @param productInfo
	 */
	void productInfoSetThis(ProductInfo productInfo);
	
	/**
	 * set productInfos' {@link ProductInfoed} to this
	 * @param productInfos
	 */
	default void productInfosSetThis(Collection<ProductInfo> productInfos) {
		if (Objects.nonNull(productInfos))
			productInfos.stream().filter(Objects::nonNull).forEach(this::productInfoSetThis);
	}
	
	/**
	 * set productInfos' {@link ProductInfoed} to this
	 * @param productInfos
	 */
	default void setToProductInfos(Set<ProductInfo> productInfos) {
		productInfosSetThis(productInfos);
		setProductInfos(productInfos);
	}
	
	/**
	 * Add a productInfo to this productInfos
	 * @param productInfo
	 */
	default void addToProductInfo(ProductInfo productInfo) {
		productInfoSetThis(productInfo);
		if (Objects.nonNull(getProductInfos())) {
			getProductInfos().add(productInfo);
		} else {
			setProductInfos(new LinkedHashSet<ProductInfo>(Arrays.asList(productInfo)));
		}
	}
	
	/**
	 * Add productInfos to this productInfos
	 * @param productInfos
	 */
	default void addToProductInfo(Collection<ProductInfo> productInfos) {
		productInfosSetThis(productInfos);
		if (Objects.nonNull(getProductInfos())) {
			getProductInfos().addAll(productInfos);
		} else {
			setProductInfos(new LinkedHashSet<ProductInfo>(productInfos));
		}
	};

}
