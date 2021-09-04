/**
 * 
 */
package capstone.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Phân loại khách hàng
 * @author Tuna
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Classification", //
		uniqueConstraints = { //
		})
public class Classification extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Tổ chức
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "classifications")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customers;

	/**
	 * Liên hệ
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "classifications")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contacts;

	/**
	 * Tiềm năng
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "classifications")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentials;

	public Classification(String name) {
		super(name);
	}

}