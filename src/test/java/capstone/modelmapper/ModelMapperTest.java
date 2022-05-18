/**
 * 
 */
package capstone.modelmapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ModelMapperTest
 * @author Tuna
 */
class ModelMapperTest {
	
	private ModelMapper m;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		m = new ModelMapper();
		m.typeMap(ModelMapperSource.class, ModelMapperDestination.class)
				.addMappings(mapper -> mapper.with(req -> new Destination()).map(ModelMapperSource::getSource,
						ModelMapperDestination::setDestination));
	}

	@Test
	void test() {
		Destination destination = m.map(new ModelMapperSource(new Source("1")), Destination.class);
		assertNotNull(destination.name);
		assertTrue(destination.name.equals("1"));
		// failed
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	static class ModelMapperSource {
		private Source source;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	static class ModelMapperDestination {
		private Destination destination;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	static class Source {
		private String name;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	static class Destination {
		private String name;
	}

}
