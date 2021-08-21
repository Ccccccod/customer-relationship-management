/**
 * 
 */
package capstone.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.I18nEnumSerializer;
import capstone.i18n.I18nEnum;

/**
 * Gender
 * Giới tính
 * @author Tuna
 *
 */
@JsonSerialize(using = I18nEnumSerializer.class)
public enum Gender implements I18nEnum<Gender> {
	
	/** Nam */
	MALE,

	/** Nữ */
	FEMALE,

	/** Khác */
	OTHER;

}
