/**
 * 
 */
package capstone.security.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import capstone.entity.Role;
import capstone.model.Permission;
import capstone.repository.UserRepository;

/**
 * UserDetailsServiceImpl
 * @author Tuna
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		capstone.entity.User user;
		try {
			user = this.userRepository.findByName(userName).get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException("User " + userName + " not found!");
		}
        
        // Add GrantedAuthorities from Roles
		List<GrantedAuthority> grantList = user.getRoles().stream()
				.filter(Objects::nonNull)
				.map(Role::getPermissions)
				.filter(Objects::nonNull)
				.flatMap(Set::stream)
				.filter(Objects::nonNull)
				.map(Permission::getValue)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
 
        return new UserDetailsImpl(user.getId(), user.getName(), user.getEmail(), user.getPassword(), grantList);
//		return new User(user.getName(), user.getPassword(), grantList);
	}
	
//	private List<String> getRoleNamesByUserId(Long id) {
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<String> query = builder.createQuery(String.class);
//		Root<UserRole> root = query.from(UserRole.class);
//		query.select(root.get(UserRole_.role).get(Role_.NAME))
//				.where(builder.equal(root.get(UserRole_.user).get(User_.ID), id));
//		List<String> results = em.createQuery(query).getResultList();
//		return results;
//		return Arrays.asList();
//	}
}
