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
 * OpportunityPhase
 * @author Tuna
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "OpportunityPhase", //
		uniqueConstraints = { //
		})
public class OpportunityPhase extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opportunityPhase")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Opportunity> opportunities;

	/**
	 * @param name
	 */
	public OpportunityPhase(@NonNull String name) {
		super(name);
	}

}
