/**
 * 
 */
package capstone.dao.specification;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author Tuna
 *
 */
public final class UserRoleSpecification {
	
	public static Specification<String> test(Long userId) {
		return (root, query, cb) -> null;
	}
	
}
