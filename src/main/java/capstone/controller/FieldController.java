/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Field;

/**
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/field")
public class FieldController extends AbstractSimpleCRUDController<Field, Long> {

}
