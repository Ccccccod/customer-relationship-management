/**
 * 
 */
package capstone.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import capstone.entity.Role;
import capstone.repository.UserRepository;

/**
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
		capstone.entity.User user = this.userRepository.findByName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User " + userName + " not found!"));
        
        // Roles
        Set<Role> roles = user.getRoles();
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                grantList.add(authority);
            }
        }
 
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
