/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import capstone.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Potential", //
		uniqueConstraints = { //
		})
public class Potential extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Xưng hô
	 */
	@Column(name = "vocative", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String vocative;

	/**
	 * Họ và đệm
	 */
	@Column(name = "last_name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String lastName;
	
	/**
	 * Tên: name.
	 * In {@link NamedEntity}
	 */
	
	/**
	 * Phòng ban
	 */
	@Column(name = "department", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String department;
	
	/**
	 * Chức danh
	 */
	@Column(name = "position", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String position;
	
	/**
	 * Phone
	 */
	@Column(name = "phone", unique = true, nullable = false)
	private String phone;

	/**
	 * Nguồn gốc
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_id")
	private Source source;
	
	/**
	 * Email
	 */
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	/**
	 * Mã số thuế
	 */
	@Column(name = "tax_code")
	private String taxCode;
	
	/**
	 * Địa chỉ
	 */
	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String address;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param vocative
	 * @param lastName
	 * @param department
	 * @param position
	 * @param phone
	 * @param source
	 * @param email
	 * @param taxCode
	 * @param address
	 */
	@Builder(toBuilder = true)
	public Potential(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, String name,
			String vocative, String lastName, String department, String position, String phone, Source source,
			String email, String taxCode, String address) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.vocative = vocative;
		this.lastName = lastName;
		this.department = department;
		this.position = position;
		this.phone = phone;
		this.source = source;
		this.email = email;
		this.taxCode = taxCode;
		this.address = address;
	}

	/**
	 * @param name
	 */
	public Potential(@NonNull String name) {
		super(name);
	}

}
