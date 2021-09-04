/**
 * 
 */
package capstone.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Tuna
 *
 * @param <ID> Type of ID
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class CodedEntity<ID extends Serializable> extends BaseEntity<ID> {
	private static final long serialVersionUID = 1L;

	@GenericGenerator( //
			name = "assigned-sequence", //
			strategy = "capstone.utils.StringSequenceIdentifierGenerator", //
			parameters = { //
					@org.hibernate.annotations.Parameter( //
							name = "sequence_name", value = "hibernate_sequence"), //
//                @org.hibernate.annotations.Parameter(
//                        name = "sequence_prefix", value = "BE"),
			})
	@GeneratedValue(generator = "assigned-sequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "code", unique = true, nullable = false)
	private String code;

	/**
	 * Sequence at the start of code EX: KH00001
	 * @return
	 */
	public abstract String sequencePrefix();

	/**
	 * Length of number in code EX: KH00001 -> length = 5
	 * @return length of number in code
	 */
	public abstract int numberIdLength();

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param code
	 */
	public CodedEntity(ID id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String code) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.code = code;
	}

	/**
	 * @author Tuna
	 *
	 */
	@EqualsAndHashCode
	@ToString
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor

//	@Entity
//	@Table(name = "code", //
//			uniqueConstraints = { //
//			})
	public static class Code<T extends CodedEntity<ID>, ID extends Serializable> {

		@Id
		@GenericGenerator( //
				name = "assigned-sequence", //
				strategy = "capstone.utils.StringSequenceIdentifierGenerator", //
				parameters = { //
						@org.hibernate.annotations.Parameter( //
								name = "sequence_name", value = "hibernate_sequence"), //
//	                @org.hibernate.annotations.Parameter(
//	                        name = "sequence_prefix", value = "BE"),
				})
		@GeneratedValue(generator = "assigned-sequence", strategy = GenerationType.SEQUENCE)
		@Column(name = "id", unique = true, nullable = false)
		private String id;

	}

}
