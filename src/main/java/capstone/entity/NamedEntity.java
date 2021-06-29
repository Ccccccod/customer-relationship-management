/**
 * 
 */
package capstone.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class NamedEntity<ID extends Serializable> extends BaseEntity<ID> {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name", nullable = false, columnDefinition = "nvarchar(255)")
	private String name;

	public NamedEntity(ID id, Date createdAt, Date updatedAt, User createdBy, User updatedBy, String name) {
		super(id, createdAt, updatedAt, createdBy, updatedBy);
		this.name = name;
	}
	
}
