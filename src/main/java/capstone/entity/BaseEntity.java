/**
 * 
 */
package capstone.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import capstone.model.Identifiable;
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
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(value = { "createdAt", "updatedAt", "createdBy", "updatedBy" }, allowGetters = true)

@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Identifiable<ID>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	protected ID id;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
//	@JsonSerialize(using = ShortDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
	protected LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", updatable = false)
//	@JsonSerialize(using = ShortDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
	protected LocalDateTime updatedAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", nullable = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	protected User createdBy;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by", nullable = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	protected User updatedBy;
	
	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}

}
