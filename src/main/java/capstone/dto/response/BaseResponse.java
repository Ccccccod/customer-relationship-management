/**
 * 
 */
package capstone.dto.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import capstone.entity.User;
import capstone.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * BaseResponse
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<ID extends Serializable> implements Identifiable<ID>{
	
	protected ID id;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	protected LocalDateTime createdAt;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	protected LocalDateTime updatedAt;
	
	protected User createdBy;

	protected User updatedBy;
	
	protected User owner;
	
	protected Boolean shared;
	
	@JsonProperty
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getCreatedDate() {
		if (createdAt == null) 
			return null;
		return createdAt.toLocalDate();
	}

	@JsonProperty
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getUpdatedDate() {
		if (updatedAt == null) 
			return null;
		return updatedAt.toLocalDate();
	}

	@JsonProperty
	@JsonSerialize(using = LocalTimeSerializer.class)
	public LocalTime getCreatedTime() {
		if (createdAt == null) 
			return null;
		return createdAt.toLocalTime();
	}

	@JsonProperty
	@JsonSerialize(using = LocalTimeSerializer.class)
	public LocalTime getUpdatedTime() {
		if (updatedAt == null) 
			return null;
		return updatedAt.toLocalTime();
	}

}
