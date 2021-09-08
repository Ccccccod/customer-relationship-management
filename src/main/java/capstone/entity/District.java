/**
 * 
 */
package capstone.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import capstone.common.Constant;
import capstone.model.Identifiable;
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * District
 * @author Tuna
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "tbl_user_district", //
		uniqueConstraints = { //
		})
public class District implements Identifiable<Long>, Named {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_districtid", unique = true, nullable = false)
	protected Long id;
	
	@Column(name = "type", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	protected String type;
	
	@Column(name = "user_district_name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	protected String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_provinceid")
	protected Province province;
	
	// OneToMany
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Ward> wards;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentials;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contacts;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customers;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Opportunity> opportunities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Order> orders;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Invoice> invoices;

}
