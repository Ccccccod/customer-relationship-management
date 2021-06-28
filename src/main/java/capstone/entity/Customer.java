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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Khách hàng
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
@Table(name = "Customer", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "CUSTOMER_UK", columnNames = "email"), //
				@UniqueConstraint(name = "CUSTOMER_UK", columnNames = "tax_code") //
		})
public class Customer extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Column(name = "short_name", columnDefinition = "nvarchar(255)")
	private String shortName;

	@Column(name = "tax_code")
	private String taxCode;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_id")
	private Source source;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "customers")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Classification> classifications;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "customers")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Field> fields;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private Type type;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "customers")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Career> careers;

	@Column(name = "address", columnDefinition = "nvarchar(255)")
	private String address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contacts;

}
