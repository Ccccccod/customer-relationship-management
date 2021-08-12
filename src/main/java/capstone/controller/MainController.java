/**
 * 
 */
package capstone.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.DateFromToDto;
import capstone.dto.response.OrderOverviewResponse;
import capstone.entity.Order;
import capstone.exception.ResourceNotFoundException;
import capstone.service.OrderService;
import capstone.utils.WebUtils;

/**
 * MainController
 * @author Tuna
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/main")
public class MainController {
	
	@Autowired
	private OrderService orderService;

	/**
	 * Get overview of Orders
	 * @param dateFromToDto contains 2 dates (from, to) to get orders between
	 * @return ResponseEntity of overview of Orders {@link OrderOverviewResponse}
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/overview/order")
	public ResponseEntity<OrderOverviewResponse> overview(@Valid @RequestBody DateFromToDto dateFromToDto)
			throws ResourceNotFoundException {
		if (Objects.isNull(dateFromToDto.getFrom())) {
			dateFromToDto.setFrom(LocalDate.MIN);
		}
		if (Objects.isNull(dateFromToDto.getTo())) {
			dateFromToDto.setTo(LocalDate.MAX);
		}
		List<Order> orders = orderService.findByOrderDateBetween(dateFromToDto.getFrom(), dateFromToDto.getTo());
		if (Objects.isNull(orders)) {
			throw new ResourceNotFoundException();
		}
		Integer quantity = Math.toIntExact(orders.stream().count());
		Long turnOver = orders.stream().mapToLong(Order::totalMoney).sum();
		Integer recordedQuantity = Math.toIntExact(orders.stream().filter(Order::getPaid).count());
		Long recordedTurnOver = orders.stream().filter(Order::getPaid).mapToLong(Order::totalMoney).sum();
		
		OrderOverviewResponse overviewResponse = OrderOverviewResponse.builder() //
				.quantity(quantity) //
				.turnOver(turnOver) //
				.recordedQuantity(recordedQuantity) //
				.recordedTurnOver(recordedTurnOver) //
				.build();
		return ResponseEntity.ok(overviewResponse);
	}
 
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "loginPage";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
 
        return "userInfoPage";
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }

}
