/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.request.deserializer.IdDeserializable;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.model.Coded;
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Cơ hội dto
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OpportunityDto extends BaseDto<Long> implements Coded, Named {

	/**
	 * Mã cơ hội
	 */
	private String code;

	/**
	 * Tên cơ hội
	 */
	private String name;
	
	/**
	 * Khách hàng
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("customer")
	private Long customerId;
	
	/**
	 * Liên hệ
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("contact")
	private Long contactId;

	/**
	 * Gian đoạn
	 */
	@NotNull
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("opportunityPhase")
	private Long opportunityPhaseId;

	/**
	 * Tỷ lệ thành công
	 */
	@NotNull
	@javax.validation.constraints.Min(value = 0)
	@javax.validation.constraints.Max(value = 100)
	private Integer successRate;

	/**
	 * Ngày kỳ vọng kết thúc
	 */
	@NotNull
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate expectedEndDate;
	
	/**
	 * Nguồn gốc
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("source")
	private Long sourceId;
	
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
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("country")
	private Long countryId;
	
	/**
	 * Tỉnh
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("province")
	private Long provinceId;
	
	/**
	 * Huyện
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("district")
	private Long districtId;
	
	/**
	 * Xã, Phường
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("ward")
	private Long wardId;
	
	/**
	 * Địa chỉ
	 */
	private String address;

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param customerId
	 * @param contactId
	 * @param opportunityPhaseId
	 * @param successRate
	 * @param expectedEndDate
	 * @param sourceId
	 * @param productInfoDtos
	 */
	public OpportunityDto(Long id, String code, String name, Long customerId, Long contactId,
			@NotNull Long opportunityPhaseId, @NotNull @Min(0) @Max(100) Integer successRate,
			@NotNull LocalDate expectedEndDate, Long sourceId, Set<ProductInfoDto> productInfoDtos) {
		super(id);
		this.code = code;
		this.name = name;
		this.customerId = customerId;
		this.contactId = contactId;
		this.opportunityPhaseId = opportunityPhaseId;
		this.successRate = successRate;
		this.expectedEndDate = expectedEndDate;
		this.sourceId = sourceId;
		this.productInfoDtos = productInfoDtos;
	}

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param customerId
	 * @param contactId
	 * @param opportunityPhaseId
	 * @param successRate
	 * @param expectedEndDate
	 * @param sourceId
	 * @param productInfoDtos
	 * @param countryId
	 * @param provinceId
	 * @param districtId
	 * @param wardId
	 * @param address
	 */
	public OpportunityDto(Long id, String code, String name, Long customerId, Long contactId, Long opportunityPhaseId,
			Integer successRate, LocalDate expectedEndDate, Long sourceId, Set<ProductInfoDto> productInfoDtos,
			Long countryId, Long provinceId, Long districtId, Long wardId, String address) {
		super(id);
		this.code = code;
		this.name = name;
		this.customerId = customerId;
		this.contactId = contactId;
		this.opportunityPhaseId = opportunityPhaseId;
		this.successRate = successRate;
		this.expectedEndDate = expectedEndDate;
		this.sourceId = sourceId;
		this.productInfoDtos = productInfoDtos;
		this.countryId = countryId;
		this.provinceId = provinceId;
		this.districtId = districtId;
		this.wardId = wardId;
		this.address = address;
	}

}
