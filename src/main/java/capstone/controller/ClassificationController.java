/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Classification;
import capstone.repository.ClassificationRepository;

/**
 * ClassificationController
 * Phân loại khách hàng Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/classification")
public class ClassificationController extends AbstractSimpleCRUDController<Classification, ClassificationRepository, Long> {

}
