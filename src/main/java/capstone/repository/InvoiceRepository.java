/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Invoice;

/**
 * InvoiceRepository
 * @author Tuna
 *
 */
@Repository
public interface InvoiceRepository extends NamedJpaRepository<Invoice, Long>{

}
