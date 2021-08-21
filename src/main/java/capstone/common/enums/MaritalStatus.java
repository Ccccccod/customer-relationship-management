/**
 * 
 */
package capstone.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.I18nEnumSerializer;
import capstone.i18n.I18nEnum;

/**
 * MaritalStatus
 * Tình trạng hôn nhân
 * @author Tuna
 *
 */
@JsonSerialize(using = I18nEnumSerializer.class)
public enum MaritalStatus implements I18nEnum<MaritalStatus> {
	
	/** Chưa kết hôn */
	NOT_MARRIED,

	/** Đã kết hôn */
	MARRIED,

	/** Đã ly hôn */
	DIVORCED,

	/** Khác */
	OTHER

}
