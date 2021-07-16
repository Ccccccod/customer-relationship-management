/**
 * 
 */
package capstone.dto.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import capstone.dto.request.deserializer.DateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
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
public class OpportunityDto extends NamedDto<Long> {
	
	private Long customerId;
	
	private Long contactId;
	
	@NotNull
	private Long moneyAmount;

	@NotNull
	private Long opportunityPhaseId;

	@NotNull
	private Integer successRate;

	@NotNull
	@JsonDeserialize(using = DateDeserializer.class)
	private Date expectedEndDate;

	@NotNull
	private Long expectedTurnOver;
	
	private Long sourceId;

}
