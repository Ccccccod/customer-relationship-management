/**
 * 
 */
package capstone.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "OpportunityPhase", //
		uniqueConstraints = { //
		})
public class OpportunityPhase extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "next_opportunity_phase", foreignKey = @ForeignKey(name = "fk_opportunity_phase"))
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private OpportunityPhase nextOpportunityPhase;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "nextOpportunityPhase", cascade = CascadeType.ALL)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private OpportunityPhase lastOpportunityPhase;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param nextOpportunityPhase
	 * @param lastOpportunityPhase
	 */
	@Builder
	public OpportunityPhase(Long id, Date createdAt, Date updatedAt, User createdBy, User updatedBy, String name,
			OpportunityPhase nextOpportunityPhase, OpportunityPhase lastOpportunityPhase) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.nextOpportunityPhase = nextOpportunityPhase;
		this.lastOpportunityPhase = lastOpportunityPhase;
	}

	/**
	 * @param name
	 */
	public OpportunityPhase(@NonNull String name) {
		super(name);
	}

}
