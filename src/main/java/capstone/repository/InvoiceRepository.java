/**
 * 
 */
package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Invoice;

/**
 * InvoiceRepository
 * @author Tuna
 */
@Repository
public interface InvoiceRepository
		extends JpaRepository<Invoice, Long>, CodedRepository<Invoice, Long>, BaseRepository<Invoice, Long> {

}
