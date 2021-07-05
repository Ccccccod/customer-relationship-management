/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Career;

/**
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/career")
public class CareerController extends AbstractSimpleCRUDController<Career, Long>{

}
