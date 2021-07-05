/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Type;

/**
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/type")
public class TypeController extends AbstractSimpleCRUDController<Type, Long>{

}
