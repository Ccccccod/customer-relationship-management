/**
 * 
 */
package capstone.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * BaseRepositoryTest
 * @author Tuna
 */
class BaseRepositoryTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		List<Number> ids = Arrays.asList(1, 2, 3, 5);
		StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
		ids.forEach(id -> stringJoiner.add(id.toString()));
		System.out.println(stringJoiner.toString());
		assertEquals("(1,2,3,5)", stringJoiner.toString());
	}

}
