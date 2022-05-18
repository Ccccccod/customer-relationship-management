/**
 * 
 */
package capstone.controller;

import java.util.LinkedHashMap;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ChangePasswordRequest;
import capstone.entity.User;
import capstone.exception.InvalidOldPasswordException;
import capstone.repository.UserRepository;
import capstone.service.UserService;

/**
 * Security Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/security")
public class SecurityController {

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@Autowired
	protected UserService userService;

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request) throws Exception {
		User currentUser = userService.getCurrentUser();
		if (Objects.isNull(currentUser))
			throw new Exception();
		String oldPassword = request.getOldPassword();
		String newPassword = request.getNewPassword();
		
		if (oldPassword.equals(newPassword))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		if (!passwordEncoder.matches(oldPassword, currentUser.getPassword()))
			throw new InvalidOldPasswordException("Old password is not valid: " + oldPassword);
		
		currentUser.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(currentUser);
		
		// Success
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("changed", true);
		return ResponseEntity.ok(map);
	}

}
