/**
 * 
 */
package capstone.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Lĩnh vực
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
@Table(name = "Field", //
		uniqueConstraints = { //
		})
public class Field extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "fields")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customers;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Career> careers;
	
	public Field(String name) {
		super(name);
	}

}
