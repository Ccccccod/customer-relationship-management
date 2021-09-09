/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import capstone.dto.request.deserializer.IdSetDeserializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Vai trò Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends NamedDto<Long> {
	
	/**
	 * Mô tả
	 */
	private String description;
	
	/**
	 * Quyền
	 */
	@JsonDeserialize(using = IdSetDeserializable.class)
	@JsonAlias("permissionFunctionActions")
	private Set<Long> permissionFunctionActionIds;

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param permissionFunctionActionIds
	 */
	@Builder
	public RoleDto(Long id, String name, String description, Set<Long> permissionFunctionActionIds) {
		super(id, name);
		this.description = description;
		this.permissionFunctionActionIds = permissionFunctionActionIds;
	}

}
