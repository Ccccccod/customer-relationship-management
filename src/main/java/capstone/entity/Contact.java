/**
 * 
 */
package capstone.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import capstone.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Contact
 * Liên hệ
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Contact", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "CONTACT_UK", columnNames = "email") //
		})
public class Contact extends NamedEntity<Long> {
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
	 * Chức danh
	 */
	@Column(name = "position", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String position;
	
	/**
	 * Phòng ban
	 */
	@Column(name = "department", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String department;
	
	/**
	 * Tổ chức
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	/**
	 * Phân loại khách hàng
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "contacts")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Classification> classifications;
	
	/**
	 * Phone
	 */
	@Column(name = "phone")
	private String phone;
	
	/**
	 * Email
	 */
	@Column(name = "email")
	private String email;

	/**
	 * Nguồn gốc
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_id")
	private Source source;
	
	/**
	 * Địa chỉ
	 */
	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String address;
	
	/**
	 * @param name
	 */
	@Builder
	public Contact(String name) {
		super(name);
	}

}
