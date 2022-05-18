/**
 * 
 */
package capstone.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.dto.response.serializer.UserSerializer;
import capstone.entity.Gender;
import capstone.entity.Role;
import capstone.entity.User;
import capstone.model.Identifiable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * User Response
 * @author Tuna
 *
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
//@AllArgsConstructor
public class UserResponse implements Identifiable<Long> {
	
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
	private LocalDateTime createdAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
	private LocalDateTime updatedAt;
	
	@JsonSerialize(using = UserSerializer.class)
	private User createdBy;

	@JsonSerialize(using = UserSerializer.class)
	private User updatedBy;
	
	private String username;
	
	private String email;
	
	/**
	 * Vai trò
	 */
	private Set<Role> roles;

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
	private Gender gender;

	/**
	 * Địa chỉ
	 */
	private String address;
	
	/**
	 * @return Họ và tên
	 */
	public String getFullName() {
		return (this.lastName != null ? this.lastName : "") + ' ' + (this.name != null ? this.name : "");
	}

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param username
	 * @param email
	 * @param roles
	 * @param lastName
	 * @param name
	 * @param phone
	 * @param dateOfBirth
	 * @param gender
	 * @param address
	 */
	public UserResponse(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String username, String email, Set<Role> roles, String lastName, String name, String phone,
			LocalDate dateOfBirth, Gender gender, String address) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
	}

}
