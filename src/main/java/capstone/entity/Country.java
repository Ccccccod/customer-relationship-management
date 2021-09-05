/**
 * 
 */
package capstone.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 * Country
 * @author Tuna
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "tbl_user_country", //
		uniqueConstraints = { //
		})
public class Country implements Identifiable<Long>, Named {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_countryid", unique = true, nullable = false)
	protected Long id;
	
	@Column(name = "user_country_code", nullable = false)
	protected String code;
	
	@Column(name = "user_country_name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	protected String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Province> provinces;

}
