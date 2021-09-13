/**
 * 
 */
package capstone.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import capstone.common.annotation.UniqueOrNull;
import capstone.model.Coded;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * CodedNamedEntity
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@MappedSuperclass
public class CodedNamedEntity<ID extends Serializable> extends NamedEntity<ID> implements Coded {
	private static final long serialVersionUID = 1L;

	@UniqueOrNull
	@Column(name = "code", nullable = false)
	protected String code;

	public CodedNamedEntity(ID id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String name, String code) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name);
		this.code = code;
	}

	public CodedNamedEntity(String name, String code) {
		super(name);
		this.code = code;
	}

}
