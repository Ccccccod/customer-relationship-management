/**
 * 
 */
package capstone.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * PageResponse
 * @author Tuna
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PageResponse<T> {
	
	private List<T> list;
	
	private int currentPage;
	
	private long totalElements;
	
	private int totalPages;

}
