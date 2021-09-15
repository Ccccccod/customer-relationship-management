/**
 * 
 */
package capstone.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import capstone.common.Constant;
import capstone.model.IdAndName;
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Named Base Entity
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public class NamedEntity<ID extends Serializable> extends BaseEntity<ID> implements Named, IdAndName<ID> {
	private static final long serialVersionUID = 1L;
	
	@NotNull // javax.validation.constraints.NotNull in case this object is used as request dto
	@NotBlank
	@Column(name = "name", nullable = false, columnDefinition = Constant.Hibernate.NVARCHAR_255)
	protected String name;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param name
	 */
	public NamedEntity(ID id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String name) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.name = name;
	}
	
}
