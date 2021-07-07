/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Role;

/**
 * Role Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/role")
public class RoleController extends AbstractSimpleCRUDController<Role, Long> {

}
