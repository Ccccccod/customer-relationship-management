/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Department;

/**
 * DepartmentRepository
 * @author Tuna
 */
@Repository
public interface DepartmentRepository extends NamedJpaRepository<Department, Long> {

}
