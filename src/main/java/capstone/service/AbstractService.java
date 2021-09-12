/**
 * 
 */
package capstone.service;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import capstone.common.Constant;
import capstone.dto.response.PageResponse;
import capstone.entity.BaseEntity;
import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;
import capstone.model.Identifiable;
import capstone.service.iservice.ICreateService;
import capstone.service.iservice.IDeleteService;
import capstone.service.iservice.IReadService;
import capstone.service.iservice.IUpdateService;
import capstone.utils.DtoUtils;
import capstone.utils.RepositoryUtils;
import lombok.Getter;

/**
 * Abstract Service
 * @author Tuna
 *
 * @param <CreateDto>
 * @param <UpdateDto>
 * @param <Response>
 * @param <Entity>
 * @param <Repository>
 * @param <ID>
 */
public abstract class AbstractService< //
		CreateDto extends Object & Identifiable<ID>, //
		UpdateDto extends Object & Identifiable<ID>, //
		Response extends Object & Identifiable<ID>, //
		Entity extends Object & Identifiable<ID>, //
		Repository extends JpaRepository<Entity, ID>, //
		ID extends Serializable //
> //
		implements IReadService<Response, ID>, //
		ICreateService<CreateDto, Response>, //
		IUpdateService<UpdateDto, Response, ID>, //
		IDeleteService<ID>
{
	
	protected abstract Class<Entity> entityClass();
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Getter
	@Autowired
	protected Repository repository;
	
	@Autowired
	protected BusinessTypeService businessTypeService;
	
	@Autowired
	protected CareerService careerService;
	
	@Autowired
	protected ClassificationService classificationService;
	
	@Autowired
	protected ContactService contactService;
	
	@Autowired
	protected CountryService countryService;
	
	@Autowired
	protected CustomerService customerService;
	
	@Autowired
	protected DepartmentService departmentService;
	
	@Autowired
	protected DistrictService districtService;
	
	@Autowired
	protected FieldService fieldService;
	
	@Autowired
	protected GenderService genderService;
	
	@Autowired
	protected IncomeService incomeService;
	
	@Autowired
	protected InvoiceService invoiceService;
	
	@Autowired
	protected MaritalStatusService maritalStatusService;
	
	@Autowired
	protected OpportunityPhaseService opportunityPhaseService;
	
	@Autowired
	protected OpportunityService opportunityService;
	
	@Autowired
	protected OrderService orderService;
	
	@Autowired
	protected PermissionFunctionActionService permissionFunctionActionService;
	
	@Autowired
	protected PhoneAreaCodeService phoneAreaCodeService;
	
	@Autowired
	protected PositionService positionService;
	
	@Autowired
	protected PotentialService potentialService;
	
	@Autowired
	protected ProductInfoService productInfoService;

	@Autowired
	protected ProductService productService;

	@Autowired
	protected ProductTypeService productTypeService;
	
	@Autowired
	protected ProvinceService provinceService;
	
	@Autowired
	protected RoleService roleService;
	
	@Autowired
	protected SourceService sourceService;

	@Autowired
	protected TypeService typeService;
	
	@Autowired
	protected UnitService unitService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected VocativeService vocativeService;
	
	@Autowired
	protected WardService wardService;
	
	@Autowired
    protected EntityManager entityManager;
	
	protected Session enableDeletedFilter(Boolean isDeleted) {
		Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(Constant.Hibernate.DELETED_FILTER);
        filter.setParameter("isDeleted", isDeleted);
        return session;
	}
	
	protected void disableDeletedFilter(Session session) {
		session.disableFilter(Constant.Hibernate.DELETED_FILTER);
		session.close();
	}
	
	@Override
	public List<Response> getAll() throws ResourceNotFoundException {
		Session session = enableDeletedFilter(false);
		try {
			List<Response> response = this.repository.findAll().stream() //
					.map(this::entityToResponse) //
					.collect(Collectors.toList());
			return response;
		} finally {
			disableDeletedFilter(session);
		}
	}
	
	public PageResponse<Response> getAll(int page, int size) {
		Session session = enableDeletedFilter(false);
		try {
			Pageable pageable = PageRequest.of(page, size);
			Page<Entity> p = this.repository.findAll(pageable);
			// map to response
			List<Response> content = p.getContent().stream() //
					.map(this::entityToResponse) //
					.collect(Collectors.toList());

			PageResponse<Response> result = PageResponse.<Response>builder() //
					.list(content) //
					.currentPage(p.getNumber()) //
					.totalElements(p.getTotalElements()) //
					.totalPages(p.getTotalPages()) //
					.build();
			return result;
		} finally {
			disableDeletedFilter(session);
		}
	}
	
	@Override
	public Response getById(ID id) throws ResourceNotFoundException {
		Session session = enableDeletedFilter(false);
		try {
			Entity entity = this.repository.findById(id)
					.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(entityClass(), id));
			Response response = this.entityToResponse(entity);
			return response;
		} finally {
			disableDeletedFilter(session);
		}
	}
	
	@Override
	public Response create(CreateDto dto) throws ResourceNotFoundException, ResourceExistedException,
			IllegalArgumentException, IllegalAccessException, InstantiationException {
		this.logger.debug("create() with body {} of type {}", dto, dto.getClass());
        
		Entity entity = this.createDtoToEntity(dto, entityClass().newInstance());
		entity.setId(null);
		
		entity = saveEntity(entity);
		
		Response response = this.entityToResponse(entity);
		return response;
	}
	
	@Override
	public Response update(ID id, UpdateDto dto)
			throws ResourceNotFoundException, InstantiationException, IllegalAccessException, ResourceExistedException {
		logger.debug("update() of id#{} with body {}", id, dto);
		
		Entity entity = this.repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id));
		entity = this.updateDtoToEntity(dto, entity);
		entity.setId(id);
		
		RepositoryUtils.checkExistedFields(entity, id, repository, entityClass());
		entity = this.repository.save(entity);
		logger.debug("updated enitity: {}", entity);
		
		Response response = this.entityToResponse(entity);
		return response;
	}
	
	@Override
	public void delete(Iterable<ID> ids) {
		this.repository.deleteAllById(ids);
	}
	
	Entity getEntityById(ID id, Boolean isDeleted) throws ResourceNotFoundException {
		if (id == null)
			return null;
		Session session = enableDeletedFilter(false);
		try {
			Entity entity = this.repository.findById(id)
					.orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(entityClass(), id));
			return entity;
		} finally {
			disableDeletedFilter(session);
		}
	}

	Entity getEntityById(ID id) throws ResourceNotFoundException {
		return getEntityById(id, false);
	}
	
	Set<Entity> getEntitiesById(Iterable<ID> ids, Boolean isDeleted) throws ResourceNotFoundException {
		if (Objects.isNull(ids)) {
			return null;
		}
		Set<Entity> entities = new LinkedHashSet<Entity>();
		for (ID id : ids) {
			if (Objects.nonNull(ids))
				entities.add(getEntityById(id, isDeleted));
		}
		return entities;
	}
	
	Set<Entity> getEntitiesById(Iterable<ID> ids) throws ResourceNotFoundException {
		return getEntitiesById(ids, false);
	}
	
	List<Entity> getAllEntities() throws ResourceNotFoundException {
		Session session = enableDeletedFilter(false);
		try {
			return this.repository.findAll();
		} finally {
			disableDeletedFilter(session);
		}
	}

	Entity saveEntity(Entity entity) throws InstantiationException, IllegalAccessException, ResourceExistedException {
		RepositoryUtils.checkExistedFields(entity, this.repository, entityClass());
		return this.repository.save(entity);
	}
	
	/**
	 * Entity to Response mapper
	 * @param entity
	 * @return
	 */
	protected abstract Response entityToResponse(Entity entity);
	
	/**
	 * Dto to Entity mapper
	 * @param createDto dto to map to entity
	 * @param entity
	 * @return
	 * @throws ResourceNotFoundException if Dto's fields contain id of no resources
	 */
	protected abstract Entity createDtoToEntity(CreateDto createDto, Entity entity) throws ResourceNotFoundException;
	
	/**
	 * Dto to Entity mapper
	 * @param createDto dto to map to entity
	 * @param entity
	 * @return
	 * @throws ResourceNotFoundException if Dto's fields contain id of no resources
	 */
	protected abstract Entity updateDtoToEntity(UpdateDto updateDto, Entity entity) throws ResourceNotFoundException;

	/**
	 * Method to quickly map to a BaseEntity from its id
	 * @param <T>
	 * @param <ID>
	 * @param repository T's repository
	 * @param id         T's id
	 * @param class1     T's class
	 * @return T
	 * @throws ResourceNotFoundException if no T is found for the id
	 */
	public static <T extends BaseEntity<ID>, ID extends Serializable> T findEntityById(JpaRepository<T, ID> repository, ID id,
			Class<T> class1) throws ResourceNotFoundException {
		if (Objects.isNull(id)) {
			return null;
		}
		return repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(class1, id));
	};
	
	/**
	 * Method to quickly map to a {@link Set} of BaseEntity from a {@link Set} of
	 * its id
	 * @param <T>
	 * @param <ID>
	 * @param repository T's repository
	 * @param ids        {@link Set} of T's id
	 * @param class1     T's class
	 * @return {@link Set} of T
	 * @throws ResourceNotFoundException
	 */
	@Deprecated
	public static <T extends BaseEntity<ID>, ID extends Serializable> Set<T> findEntitiesByIds(JpaRepository<T, ID> repository,
			Set<ID> ids, Class<T> class1) throws ResourceNotFoundException {
		if (Objects.isNull(ids)) {
			return null;
		}
		Set<T> ts = new LinkedHashSet<>();
		for (ID id : ids) {
			if (Objects.isNull(ids)) {
				continue;
			}
			ts.add(findEntityById(repository, id, class1));
		}
		return ts;
	}
	
	/**
	 * Method to quickly map to a {@link List} of BaseEntity from a {@link List} of
	 * its id
	 * @param <T>
	 * @param <ID>
	 * @param repository T's repository
	 * @param ids        {@link List} of T's id
	 * @param class1     T's class
	 * @return {@link List} of T
	 * @throws ResourceNotFoundException
	 */
	@Deprecated
	public static <T extends BaseEntity<ID>, ID extends Serializable> List<T> findEntitiesByIds(JpaRepository<T, ID> repository,
			List<ID> ids, Class<T> class1) throws ResourceNotFoundException {
		if (Objects.isNull(ids)) {
			return null;
		}
		List<T> ts = new LinkedList<>();
		for (ID id : ids) {
			if (Objects.isNull(ids)) {
				continue;
			}
			ts.add(findEntityById(repository, id, class1));
		}
		return ts;
	}
	
}
