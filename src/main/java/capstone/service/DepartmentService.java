/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Department;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.DepartmentRepository;
import capstone.service.iservice.INamedService;

/**
 * DepartmentService
 * @author DELL
 * @author tuna
 */
@Service
public class DepartmentService
		extends AbstractService<Department, Department, Department, Department, DepartmentRepository, Long>
		implements INamedService<Department, DepartmentRepository, Long> {

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
