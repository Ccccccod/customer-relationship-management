/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Source;
import capstone.repository.SourceRepository;

/**
 * SourceController
 * Nguồn gốc Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/source")
public class SourceController extends AbstractSimpleCRUDController<Source, SourceRepository, Long> {

}
