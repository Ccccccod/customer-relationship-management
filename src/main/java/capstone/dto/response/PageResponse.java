/**
 * 
 */
package capstone.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * PageResponse
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
public class PageResponse<T> {
	
	private List<T> list;
	
	private int currentPage;
	
	private long totalElements;
	
	private int totalPages;

}
