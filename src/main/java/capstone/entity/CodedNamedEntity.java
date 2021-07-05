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
 * @author Tuna
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@MappedSuperclass
public class CodedNamedEntity<ID extends Serializable> extends NamedEntity<ID> {
	private static final long serialVersionUID = 1L;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	public CodedNamedEntity(ID id, Date createdAt, Date updatedAt, User createdBy, User updatedBy,
			String name) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
	}

	public CodedNamedEntity(String name, String code) {
		super(name);
		this.code = code;
	}

	@Builder
	public CodedNamedEntity(ID id, Date createdAt, Date updatedAt, User createdBy, User updatedBy, String name,
			String code) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.code = code;
	}

}
