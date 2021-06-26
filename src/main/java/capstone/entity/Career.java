/**
 * 
 */
package capstone.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Ngành nghề
 * @author Tuna
 *
 */
@Getter
@Setter
// @NoArgsConstructor
// @AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Career", //
		uniqueConstraints = { //
		})
public class Career extends NamedEntity<Long>{
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "customer_career", //
			joinColumns = { @JoinColumn(name = "career_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "customer_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customers;
	
	public Career(String name) {
		super(name);
	}

}
