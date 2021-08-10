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
