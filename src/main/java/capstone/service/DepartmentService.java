/**
 * 
 */
package capstone.service;

import capstone.entity.Department;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.DepartmentRepository;

/**
 * DepartmentService
 * @author DELL
 */
public class DepartmentService extends AbstractService<Department, Department, Department, Department, DepartmentRepository, Long>{

	@Override
	protected Class<Department> entityClass() {
		return Department.class;
	}

	@Override
	protected Department entityToResponse(Department entity) {
		return entity;
	}

	@Override
	protected Department createDtoToEntity(Department d, Department entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Department updateDtoToEntity(Department updateDto, Department entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
