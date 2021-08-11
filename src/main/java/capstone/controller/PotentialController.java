/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.PotentialDto;
import capstone.entity.Potential;
import capstone.entity.Source;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.PotentialRepository;
import capstone.repository.SourceRepository;
import capstone.service.AbstractService;

/**
 * PotentialController
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/potential")
public class PotentialController
		extends AbstractDtoEntityController<PotentialDto, Potential, PotentialRepository, Long> {
	
	@Autowired
	protected SourceRepository sourceRepository;

	@Override
	protected Class<capstone.entity.Potential> entityClass() {
		return Potential.class;
	}

	@Override
	protected capstone.entity.Potential dtoToEntity(PotentialDto dto, capstone.entity.Potential entity)
			throws ResourceNotFoundException {
		return entity.toBuilder()
				.id(dto.getId())
				.vocative(dto.getVocative())
				.name(dto.getName())
				.lastName(dto.getLastName())
				.department(dto.getDepartment())
				.position(dto.getPosition())
				.phone(dto.getPhone())
				.source(AbstractService.findEntityById(sourceRepository, dto.getSourceId(), Source.class))
				.email(dto.getEmail())
				.taxCode(dto.getTaxCode())
				.address(dto.getAddress())
				.build();
	}

}
