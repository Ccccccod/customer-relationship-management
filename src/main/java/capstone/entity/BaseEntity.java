/**
 * 
 */
package capstone.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.ShortDateSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity with basic fields to be extended
 * @author Tuna
 *
 * @param <ID> Type of ID field
 */
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Identifiable<ID>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private ID id;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@JsonSerialize(using = ShortDateSerializer.class)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@JsonSerialize(using = ShortDateSerializer.class)
//	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
	private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", nullable = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private User createdBy;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by", nullable = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private User updatedBy;
	
	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}

}
