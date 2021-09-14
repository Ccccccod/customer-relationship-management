/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.UserDto;
import capstone.dto.request.UserUpdateDto;
import capstone.entity.User;
import capstone.repository.UserRepository;
import capstone.service.UserService;

/**
 * User Controller
 * Người dùng Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends CRUDController<UserDto, UserUpdateDto, User, User, UserRepository, UserService, Long> {
	
}
