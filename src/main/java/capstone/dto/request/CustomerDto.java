/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.request.deserializer.IdDeserializable;
import capstone.dto.request.deserializer.IdSetDeserializable;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.dto.validatation.annotation.Email;
import capstone.model.Coded;
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Khách hàng dto
 * @author Tuna
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends BaseDto<Long> implements Coded, Named {
	
	/**
	 * Mã khách hàng
	 */
	private String code;
	
	/**
	 * Tên viết tắt
	 */
	private String shortName;
	
	/**
	 * Tên
	 */
	private String name;

	/**
	 * Mã số thuế
	 */
	@NotNull
	private String taxCode;
	
	/**
	 * Điện thoại
	 */
	private String phone;

	/**
	 * Email
	 */
	@NotNull
	@Email
	private String email;
	
	/**
	 * Nguồn gốc
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("source")
	private Long sourceId;
	
	/**
	 * Phân loại khách hàng
	 */
	@JsonDeserialize(using = IdSetDeserializable.class)
	@JsonAlias("classifications")
	private Set<Long> classificationIds;
	
	/**
	 * Lĩnh vực
	 */
	@JsonDeserialize(using = IdSetDeserializable.class)
	@JsonAlias("fields")
	private Set<Long> fieldIds;
	
	/**
	 * Loại hình
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("type")
	private Long typeId;

	/**
	 * Ngành nghề
	 */
	@JsonDeserialize(using = IdSetDeserializable.class)
	@JsonAlias("careers")
	private Set<Long> careerIds;
	
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
	
	// Thong tin to chuc

	/**
	 * Tài khoản ngân hàng
	 */
	private String bankAccount;
	
	/**
	 * Mở tại ngân hàng
	 */
	private String bank;
	
	/**
	 * Ngày thành lập
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate foundedDate;
	
	/**
	 * Là khách hàng từ
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate customerSince;
	
	/**
	 * Thu nhập
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("income")
	private Long incomeId;
	
	/**
	 * Website
	 */
	private String website;

	/**
	 * @param id
	 * @param code
	 * @param shortName
	 * @param name
	 * @param taxCode
	 * @param phone
	 * @param email
	 * @param sourceId
	 * @param classificationIds
	 * @param fieldIds
	 * @param typeId
	 * @param careerIds
	 * @param countryId
	 * @param provinceId
	 * @param districtId
	 * @param wardId
	 * @param address
	 * @param bankAccount
	 * @param bank
	 * @param foundedDate
	 * @param customerSince
	 * @param incomeId
	 * @param website
	 */
	@Builder(toBuilder = true)
	public CustomerDto(Long id, String code, String shortName, String name, @NotNull String taxCode, String phone,
			@NotNull String email, Long sourceId, Set<Long> classificationIds, Set<Long> fieldIds, Long typeId,
			Set<Long> careerIds, Long countryId, Long provinceId, Long districtId, Long wardId, String address,
			String bankAccount, String bank, LocalDate foundedDate, LocalDate customerSince, Long incomeId,
			String website) {
		super(id);
		this.code = code;
		this.shortName = shortName;
		this.name = name;
		this.taxCode = taxCode;
		this.phone = phone;
		this.email = email;
		this.sourceId = sourceId;
		this.classificationIds = classificationIds;
		this.fieldIds = fieldIds;
		this.typeId = typeId;
		this.careerIds = careerIds;
		this.countryId = countryId;
		this.provinceId = provinceId;
		this.districtId = districtId;
		this.wardId = wardId;
		this.address = address;
		this.bankAccount = bankAccount;
		this.bank = bank;
		this.foundedDate = foundedDate;
		this.customerSince = customerSince;
		this.incomeId = incomeId;
		this.website = website;
	}
	
}
