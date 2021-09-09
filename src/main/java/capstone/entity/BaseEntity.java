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
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.UserSerializer;
import capstone.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Entity with basic fields to be extended
 * @author Tuna
 *
 * @param <ID> Type of ID field
 */
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(value = { "createdAt", "updatedAt", "createdBy", "updatedBy" }, allowGetters = true)

@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")

@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Identifiable<ID>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	protected ID id;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = true)
//	@JsonSerialize(using = ShortDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
	protected LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", updatable = true)
//	@JsonSerialize(using = ShortDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
	protected LocalDateTime updatedAt;
	
	@CreatedBy
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", nullable = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonSerialize(using = UserSerializer.class)
	protected User createdBy;

	@LastModifiedBy
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updated_by", nullable = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonSerialize(using = UserSerializer.class)
	protected User updatedBy;

	@CreatedBy
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	protected User owner;
	
	@Column
	protected Boolean shared;
	
	@Builder.Default
	@Column
	protected Boolean deleted = Boolean.FALSE;
	
	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}

}
