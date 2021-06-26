/**
 * 
 */
package capstone.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Named Base Entity
 * @author Tuna
 *
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class NamedEntity<ID extends Serializable> extends BaseEntity<ID> {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name", nullable = false)
	private String name;
	
}
