/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Income;

/**
 * IncomeRepository
 * @author Tuna
 */
@Repository
public interface IncomeRepository extends NamedJpaRepository<Income, Long>, BaseRepository<Income, Long> {

}
