/**
 * 
 */
package capstone.model;

import java.util.Objects;
import java.util.Set;

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
	default Integer amount() {
		if (Objects.nonNull(this.getProductInfos())) {
			this.getProductInfos().stream().map(ProductInfo::getAmount);
			return this.getProductInfos().stream().mapToInt(ProductInfo::getAmount).sum();
		}
		return null;
	}
	
	/**
	 * Thành tiền
	 * @return
	 */
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
	default Long totalMoney() {
		if (Objects.nonNull(this.getProductInfos())) {
			return this.getProductInfos().stream().mapToLong(ProductInfo::totalMoney).sum();
		}
		return null;
	}
	
	Set<ProductInfo> getProductInfos();

	/**
	 * @param productInfos the productInfos to set
	 */
	void setProductInfos(Set<ProductInfo> productInfos);
	
	void addToProductInfos(ProductInfo productInfo);

}
