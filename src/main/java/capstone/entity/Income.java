/**
 * 
 */
package capstone.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Income
 * Thu nhập
 * @author Tuna
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Income", //
		uniqueConstraints = { //
				})
public class Income extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Tiềm năng
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "income")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentials;

	/**
	 * Tiềm năng
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "income")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customers;

	/**
	 * @param name
	 */
	public Income(@NonNull String name) {
		super(name);
	}
	
}
