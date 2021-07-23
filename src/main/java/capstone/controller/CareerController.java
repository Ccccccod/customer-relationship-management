/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Career;
import capstone.repository.CareerRepository;

/**
 * CareerController
 * Ngành nghề Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/career")
public class CareerController extends AbstractSimpleCRUDController<Career, CareerRepository, Long> {

}
