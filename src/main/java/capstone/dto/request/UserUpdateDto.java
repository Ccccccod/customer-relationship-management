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

import capstone.dto.request.deserializer.IdDeserializer;
import capstone.dto.request.deserializer.IdSetDeserializer;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.dto.validatation.annotation.Email;
import capstone.dto.validatation.annotation.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * UserUpdateDto
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
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
	
	@JsonDeserialize(using = IdSetDeserializer.class)
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
	@JsonDeserialize(using = IdDeserializer.class)
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
	 * @param roleIds
	 * @param lastName
	 * @param name
	 * @param phone
	 * @param dateOfBirth
	 * @param genderId
	 * @param address
	 */
	public UserUpdateDto(Long id, @NotNull String username, @NotNull String email, Set<Long> roleIds, String lastName,
			String name, String phone, LocalDate dateOfBirth, Long genderId, String address) {
		super(id);
		this.username = username;
		this.email = email;
		this.roleIds = roleIds;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.genderId = genderId;
		this.address = address;
	}

}
