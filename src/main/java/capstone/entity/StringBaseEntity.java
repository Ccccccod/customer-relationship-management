/**
 * 
 */
package capstone.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Base Entity with auto generated String id
 * @author Tuna
 *
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@MappedSuperclass
public abstract class StringBaseEntity extends BaseEntity<String> {
	private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(
        name = "assigned-sequence",
        strategy = "capstone.utils.StringSequenceIdentifierGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(
                    name = "sequence_name", value = "hibernate_sequence"),
//            @org.hibernate.annotations.Parameter(
//                    name = "sequence_prefix", value = "BE"),
        }
    )
    @GeneratedValue(generator = "assigned-sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private String id;
	
}
