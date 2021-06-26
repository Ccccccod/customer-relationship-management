/**
 * 
 */
package capstone.utils;

import java.io.Serializable;
import java.util.Optional;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import capstone.entity.Identifiable;

/**
 * StringSequenceIdentifierGenerator
 * to auto generate string ids
 * @author Tuna
 *
 */
public class StringSequenceIdentifierGenerator implements IdentifierGenerator, Configurable {

	public static final String SEQUENCE_PREFIX = "sequence_prefix";
	
	/**
	 * Default sequence prefix
	 */
	private static final String DEFAULT_SEQUENCE_PREFIX = "";
	
	/**
	 * Default number id length
	 */
	private static final int DEFAULT_NUMBER_ID_LENGTH = 5;

	private String sequencePrefix;

	private String sequenceCallSyntax;

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {

		final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService(JdbcEnvironment.class);

		final Dialect dialect = jdbcEnvironment.getDialect();

		final ConfigurationService configurationService = serviceRegistry.getService(ConfigurationService.class);

		String globalEntityIdentifierPrefix = configurationService.getSetting("entity.identifier.prefix", String.class,
				"SEQ_");

		sequencePrefix = ConfigurationHelper.getString(SEQUENCE_PREFIX, params, globalEntityIdentifierPrefix);

		final String sequencePerEntitySuffix = ConfigurationHelper.getString(
				SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, params,
				SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX);

		boolean preferSequencePerEntity = ConfigurationHelper
				.getBoolean(SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY, params, false);

		final String defaultSequenceName = preferSequencePerEntity
				? params.getProperty(JPA_ENTITY_NAME) + sequencePerEntitySuffix
				: SequenceStyleGenerator.DEF_SEQUENCE_NAME;

		sequenceCallSyntax = dialect.getSequenceNextValString(
				ConfigurationHelper.getString(SequenceStyleGenerator.SEQUENCE_PARAM, params, defaultSequenceName));
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		int numberIdLength = 10;
		
		if (object instanceof Identifiable) {
			Identifiable<?> identifiable = (Identifiable<?>) object;
			Serializable id = identifiable.getId();

			if (id != null) {
				return id;
			}
			
//			sequencePrefix = Optional.ofNullable(identifiable.sequencePrefix()).orElse(DEFAULT_SEQUENCE_PREFIX);
//			numberIdLength = identifiable.numberIdLength();
		}
		
		if (numberIdLength <= 0) {
			numberIdLength = DEFAULT_NUMBER_ID_LENGTH;
		}

		long seqValue = ((Number) Session.class.cast(session).createNativeQuery(sequenceCallSyntax).uniqueResult())
				.longValue();

		return sequencePrefix + String.format("%0" + numberIdLength + "d%s", 0, seqValue);
	}

}
