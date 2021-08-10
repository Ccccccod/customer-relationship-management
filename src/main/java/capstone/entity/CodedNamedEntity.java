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

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@MappedSuperclass
public class CodedNamedEntity<ID extends Serializable> extends NamedEntity<ID> {
	private static final long serialVersionUID = 1L;

	@NonNull
	@NotNull(message = "must not be null")
	@NotBlank(message = "must not be blank")
	@Column(name = "code", unique = true, nullable = false)
	protected String code;

	public CodedNamedEntity(ID id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, String name,
			String code) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.code = code;
	}

	public CodedNamedEntity(String name, String code) {
		super(name);
		this.code = code;
	}

}
