/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.request.deserializer.IdDeserializer;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.model.Coded;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * OrderDto
 * Đơn hàng Dto
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends BaseDto<Long> implements Coded {

	/**
	 * Mã đơn hàng
	 */
	private String code;
	
	@NotNull
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate orderDate;

	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("customer")
	private Long customerId;

	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("contact")
	private Long contactId;

	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("opportunity")
	private Long opportunityId;

	/**
	 * Diễn giải
	 */
	private String explanation;

	/**
	 * Hạn thanh toán
	 */
	@NotNull
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate liquidationDeadline;

	/**
	 * Hạn giao hàng
	 */
	@NotNull
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate deliveryDeadline;

	/**
	 * Thực thu
	 */
	private Long receivedMoney;
	
	/**
	 * Thông tin hàng hóa
	 */
	@JsonAlias("productInfos")
	private Set<ProductInfoDto> productInfoDtos;
	
	// Address information
	// Thông tin địa chỉ
	
	/**
	 * Quốc gia 
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("country")
	private Long countryId;
	
	/**
	 * Tỉnh
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("province")
	private Long provinceId;
	
	/**
	 * Huyện
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("district")
	private Long districtId;
	
	/**
	 * Xã, Phường
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("ward")
	private Long wardId;
	
	/**
	 * Địa chỉ
	 */
	private String address;

	/**
	 * @param id
	 * @param code
	 * @param orderDate
	 * @param customerId
	 * @param contactId
	 * @param opportunityId
	 * @param explanation
	 * @param liquidationDeadline
	 * @param deliveryDeadline
	 * @param receivedMoney
	 * @param productInfoDtos
	 * @param countryId
	 * @param provinceId
	 * @param districtId
	 * @param wardId
	 * @param address
	 */
	public OrderDto(Long id, String code, @NotNull LocalDate orderDate, Long customerId, Long contactId,
			Long opportunityId, String explanation, @NotNull LocalDate liquidationDeadline,
			@NotNull LocalDate deliveryDeadline, Long receivedMoney, Set<ProductInfoDto> productInfoDtos,
			Long countryId, Long provinceId, Long districtId, Long wardId, String address) {
		super(id);
		this.code = code;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.contactId = contactId;
		this.opportunityId = opportunityId;
		this.explanation = explanation;
		this.liquidationDeadline = liquidationDeadline;
		this.deliveryDeadline = deliveryDeadline;
		this.receivedMoney = receivedMoney;
		this.productInfoDtos = productInfoDtos;
		this.countryId = countryId;
		this.provinceId = provinceId;
		this.districtId = districtId;
		this.wardId = wardId;
		this.address = address;
	}

}
