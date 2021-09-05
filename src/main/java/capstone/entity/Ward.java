/**
 * 
 */
package capstone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import capstone.common.Constant;
import capstone.model.Identifiable;
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Ward
 * @author Tuna
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "tbl_user_ward", //
		uniqueConstraints = { //
		})
public class Ward implements Identifiable<Long>, Named {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_wardid", unique = true, nullable = false)
	protected Long id;
	
	@Column(name = "type", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	protected String type;
	
	@Column(name = "user_ward_name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	protected String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "districtid")
	protected District district;

}
