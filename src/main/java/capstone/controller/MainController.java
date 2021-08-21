/**
 * 
 */
package capstone.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import capstone.common.enums.OpportunityPhase;
import capstone.dto.response.OpportunityOverviewResponse;
import capstone.dto.response.OpportunityOverviewResponse.OpportunityOverviewResponseBuilder;
import capstone.dto.response.OrderOverviewResponse;
import capstone.entity.Opportunity;
import capstone.entity.Order;
import capstone.exception.ResourceNotFoundException;
import capstone.service.OpportunityService;
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
	
	@Autowired
	private OpportunityService opportunityService;

	/**
	 * Get overview of Orders
	 * @param from to get orders between
	 * @param to to get orders between
	 * @return ResponseEntity of overview of Orders {@link OrderOverviewResponse}
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/overview/order")
	public ResponseEntity<OrderOverviewResponse> orderOverview(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to)
			throws ResourceNotFoundException {
		List<Order> orders = orderService.findByOrderDateBetween(from, to);
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
				.from(from) //
				.to(to) //
				.build();
		return ResponseEntity.ok(overviewResponse);
	}

	/**
	 * Get overview of Opportunities
	 * @param from to get opportunities between
	 * @param to to get opportunities between
	 * @return ResponseEntity of overview of Orders {@link OpportunityOverviewResponse}
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/overview/opportunity")
	public ResponseEntity<OpportunityOverviewResponse> opportunityOverview(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) throws ResourceNotFoundException {
		List<Opportunity> opportunities = opportunityService.findByExpectedEndDateBetween(from, to);
		if (Objects.isNull(opportunities)) {
			throw new ResourceNotFoundException();
		}
		List<OpportunityPhase> inProgress = Arrays.asList(OpportunityPhase.BEGINNING,
				OpportunityPhase.CUSTOMER_INTEREST, OpportunityPhase.DEMO, OpportunityPhase.NEGOTIATION);
		OpportunityOverviewResponseBuilder opportunityOverviewResponseBuilder = OpportunityOverviewResponse.builder() //
				.numberOfOportunitiesInProgress( //
						opportunities.stream().filter(Objects::nonNull) //
								.map(Opportunity::getOpportunityPhase).filter(inProgress::contains) //
								.mapToInt(o -> 1) //
								.sum())
				.doneTurnOver( //
						opportunities.stream().filter(Objects::nonNull) //
								.filter(o -> Objects.equals(o.getOpportunityPhase(), OpportunityPhase.SUCCESS_FINISH)) //
								.mapToLong(Opportunity::totalMoney) //
								.sum()) //
				.expectedTurnOver( //
						opportunities.stream().filter(Objects::nonNull) //
								.mapToLong(o -> {
									Long totalMoney = Objects.nonNull(o.totalMoney()) ? o.totalMoney() : 0L;
									Integer successRate = Objects.nonNull(o.getSuccessRate()) ? o.getSuccessRate() : 0;
									return totalMoney * successRate / 100;
								}) //
								.sum())
				.opportunityWinRate( //
						opportunities.stream().filter(Objects::nonNull) //
								.mapToDouble(Opportunity::getSuccessRate) //
								.average() //
								.orElse(0))
				.from(from)
				.to(to);
		return ResponseEntity.ok(opportunityOverviewResponseBuilder.build());
	}
 
	/**
	 * Get monthly revenue by year
	 * @param year year
	 * @return An array of 12 months' revenue order by month
	 */
	@GetMapping("/revenueByYear")
	public ResponseEntity<List<Object>> monthlyRevenueByYear(@RequestParam int year) {
		LocalDate date = LocalDate.of(year, Month.JANUARY, 1);
		LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfYear());
		LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfYear());
		List<Opportunity> opportunities = opportunityService
				.findByOpportunityPhaseAndExpectedEndDateBetween(OpportunityPhase.SUCCESS_FINISH, firstDay, lastDay);
		Map<Month, List<Opportunity>> map = opportunities.stream() //
				// Group by months
				.collect(Collectors
						.groupingBy(item -> item.getExpectedEndDate().getMonth())); //
		// Making sure all Months are in the map by putting an empty array to months that
		// contains no opportunities
		Arrays.stream(Month.values()).filter(month -> Objects.isNull(map.get(month)))
				.forEach(month -> map.put(month, Arrays.asList()));
		@SuppressWarnings("unchecked")
		List<Object> list = map.entrySet().stream() //
				// Map to [month, sum]
				.map(e -> new Comparable[] { e.getKey(),
						e.getValue().stream().filter(Objects::nonNull).mapToLong(Opportunity::totalMoney).sum() }) //
				// sort by month
				.sorted((o1, o2) -> o1[0].compareTo(o2[0])) //
				.map(e -> e[1]) //
				.collect(Collectors.toList());
		return ResponseEntity.ok(list);
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
