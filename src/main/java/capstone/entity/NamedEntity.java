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
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
public class NamedEntity<ID extends Serializable> extends BaseEntity<ID> implements Named {
	private static final long serialVersionUID = 1L;
	
	@NonNull
	@NotNull // javax.validation.constraints.NotNull in case this object is used as request dto
	@NotBlank(message = "must not be empty")
	@Column(name = "name", nullable = false, columnDefinition = Constant.Hibernate.NVARCHAR_255)
	protected String name;

	public NamedEntity(ID id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, String name) {
		super(id, createdAt, updatedAt, createdBy, updatedBy);
		this.name = name;
	}
	
}
