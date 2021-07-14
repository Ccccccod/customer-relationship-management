/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.PermissionFunctionAction;

/**
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionFunctionActionController extends AbstractSimpleCRUDController<PermissionFunctionAction, Long> {

}