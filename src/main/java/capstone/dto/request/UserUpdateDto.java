/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.enums.Gender;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.dto.validatation.annotation.Email;
import capstone.dto.validatation.annotation.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * UserUpdateDto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class UserUpdateDto extends BaseDto<Long> {

	@NotNull
	@Username
	private String username;

	@NotNull
	@Email
	private String email;
	
	private Set<Long> roleIds;

	/**
	 * Họ và đệm
	 */
	private String lastName;

	/**
	 * Họ và đệm
	 */
	private String name;
	
	/**
	 * Điện thoại
	 */
	private String phone;
	
	/**
	 * Ngày sinh
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateOfBirth;

	/**
	 * Giới tính
	 */
	@JsonProperty("genderId") //
	@JsonAlias("genderId")
	private Gender gender;

	/**
	 * Địa chỉ
	 */
	private String address;

}
