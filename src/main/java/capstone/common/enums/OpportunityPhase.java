/**
 * 
 */
package capstone.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.I18nEnumSerializer;
import capstone.i18n.I18nEnum;

/**
 * OpportunityPhase
 * Giai đoạn cơ hội
 * @author Tuna
 *
 */
@JsonSerialize(using = I18nEnumSerializer.class)
public enum OpportunityPhase implements I18nEnum<OpportunityPhase> {

	/** Mở đầu */
	BEGINNING,
	
	/** Khách hàng tự tìm kiếm */
	CUSTOMER_INTEREST,

	/** DEMO / giới thiệu */
	DEMO,

	/** Đàm phán, thương lượng */
	NEGOTIATION,

	/** Kết thúc thành công */
	SUCCESS_FINISH,

	/** Kết thúc thất bại */
	FAILURE_FINISH
}
