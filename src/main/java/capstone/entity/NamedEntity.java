/**
 * 
 */
package capstone.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Tuna
 *
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity{
	
	@Column(name = "Name", nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
}
