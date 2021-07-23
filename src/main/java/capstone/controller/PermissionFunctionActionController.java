/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.PermissionFunctionAction;
import capstone.repository.PermissionFunctionActionRepository;

/**
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionFunctionActionController
		extends AbstractSimpleCRUDController<PermissionFunctionAction, PermissionFunctionActionRepository, Long> {

}
