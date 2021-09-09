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
import capstone.dto.validatation.annotation.Password;
import capstone.dto.validatation.annotation.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * User Dto
 * @author Tuna
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto extends BaseDto<Long> {

	@JsonAlias({ "name" })
	@NotNull
	@Username
	private String username;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Password
	private String password;

	@JsonDeserialize(using = IdSetDeserializable.class)
	@JsonAlias("roles")
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
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("gender")
	private Long genderId;

	/**
	 * Địa chỉ
	 */
	private String address;

	/**
	 * @param id
	 * @param username
	 * @param email
	 * @param password
	 * @param roleIds
	 * @param lastName
	 * @param name
	 * @param phone
	 * @param dateOfBirth
	 * @param genderId
	 * @param address
	 */
	@Builder(toBuilder = true)
	public UserDto(Long id, @NotNull String username, @NotNull String email, @NotNull String password,
			Set<Long> roleIds, String lastName, String name, String phone, LocalDate dateOfBirth, Long genderId,
			String address) {
		super(id);
		this.username = username;
		this.email = email;
		this.password = password;
		this.roleIds = roleIds;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.genderId = genderId;
		this.address = address;
	}

}
