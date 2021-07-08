/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.UserDto;
import capstone.dto.response.UserResponse;
import capstone.entity.User;

/**
 * User Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/role")
public class UserController extends AbstractCRUDController<UserDto, UserResponse, User, Long> {

}
