/**
 * 
 */
package capstone.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.Constant;
import capstone.common.annotation.UniqueOrNull;
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
 * Customer
 * Khách hàng
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Customer", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "CUSTOMER_UK", columnNames = "email"), //
//				@UniqueConstraint(name = "CUSTOMER_UK", columnNames = "tax_code") //
		})
public class Customer extends BaseEntity<Long> implements Coded, Named {
	private static final long serialVersionUID = 1L;

	/**
	 * Mã khách hàng
	 */
	@Column(name = "code", nullable = false)
	private String code;

	/**
	 * Tên viết tắt
	 */
	@Column(name = "short_name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String shortName;

	/**
	 * Tên
	 */
	@Column(name = "name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String name;

	/**
	 * Mã số thuế
	 */
	@UniqueOrNull
	@Column(name = "tax_code")
	private String taxCode;

	/**
	 * Điện thoại
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
	 * Phân loại khách hàng
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "customer_classification", //
			joinColumns = { @JoinColumn(name = "customer_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "classification_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Classification> classifications;

	/**
	 * Lĩnh vực
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "customer_field", //
			joinColumns = { @JoinColumn(name = "customer_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "field_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Field> fields;

	/**
	 * Loại hình
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private Type type;

	/**
	 * Ngành nghề
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "customer_career", //
			joinColumns = { @JoinColumn(name = "customer_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "career_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Career> careers;
	
	// Thông tin địa chỉ
	
	/**
	 * Quốc gia 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;
	
	/**
	 * Tỉnh
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "province_id")
	private Province province;
	
	/**
	 * Huyện
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id")
	private District district;
	
	/**
	 * Xã, Phường
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ward_id")
	private Ward ward;

	/**
	 * Địa chỉ
	 */
	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String address;
	
	// Other information
	// Thông tin khac

	/**
	 * Tài khoản ngân hàng
	 */
	@Column(name = "bank_account")
	private String bankAccount;
	
	/**
	 * Mở tại ngân hàng
	 */
	@Column(name = "bank", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String bank;
	
	/**
	 * Ngày thành lập
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "founded_date")
	private LocalDate foundedDate;
	
	/**
	 * Là khách hàng từ
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "customer_since")
	private LocalDate customerSince;
	
	/**
	 * Thu nhập
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "income_id")
	private Income income;
	
	/**
	 * Website
	 */
	@Column(name = "website", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String website;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contacts;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Opportunity> opportunities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Order> orders;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Invoice> invoices;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentials;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param code
	 * @param shortName
	 * @param name
	 * @param taxCode
	 * @param phone
	 * @param email
	 * @param source
	 * @param classifications
	 * @param fields
	 * @param type
	 * @param careers
	 * @param country
	 * @param province
	 * @param district
	 * @param ward
	 * @param address
	 * @param bankAccount
	 * @param bank
	 * @param foundedDate
	 * @param customerSince
	 * @param income
	 * @param website
	 * @param contacts
	 * @param opportunities
	 * @param orders
	 * @param invoices
	 * @param potentials
	 */
	public Customer(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String code, String shortName, String name, String taxCode,
			String phone, String email, Source source, Set<Classification> classifications, Set<Field> fields,
			Type type, Set<Career> careers, Country country, Province province, District district, Ward ward,
			String address, String bankAccount, String bank, LocalDate foundedDate, LocalDate customerSince,
			Income income, String website, Set<Contact> contacts, Set<Opportunity> opportunities, Set<Order> orders,
			Set<Invoice> invoices, Set<Potential> potentials) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.code = code;
		this.shortName = shortName;
		this.name = name;
		this.taxCode = taxCode;
		this.phone = phone;
		this.email = email;
		this.source = source;
		this.classifications = classifications;
		this.fields = fields;
		this.type = type;
		this.careers = careers;
		this.country = country;
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.address = address;
		this.bankAccount = bankAccount;
		this.bank = bank;
		this.foundedDate = foundedDate;
		this.customerSince = customerSince;
		this.income = income;
		this.website = website;
		this.contacts = contacts;
		this.opportunities = opportunities;
		this.orders = orders;
		this.invoices = invoices;
		this.potentials = potentials;
	}

	/**
	 * @param name
	 */
	public Customer(String name) {
		this.name = name;
	}

}
