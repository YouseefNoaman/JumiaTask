package com.jumia.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jumia.demo.entities.Customer;
import com.jumia.demo.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // since we’re just working locally
public class customerController {

	private CustomerService customerService;

	@Autowired
	customerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/info")
	public String info() {
		return "THIS IS WORKING";
	}

	@GetMapping("/all")
	public List<Customer> all() {
		return customerService.searchCustomers("", "");
	}

	@GetMapping("/error")
	public String error() {
		return "THIS IS error";
	}

//	@GetMapping("/search")
//	public List<Customer> getAll(@RequestParam Map<String, String> customQuery) {
////		return ("countrySearch = " + countrySearch + " stateSearch = " + stateSearch);
//		return customerService.searchCustomers(
//				customQuery.containsKey("countrySearch") ? customQuery.get("customQuery") : "",
//				customQuery.containsKey("stateSearch") ? customQuery.get("stateSearch") : "");
//	}
//
//	@GetMapping("/search/{countrySearch}/{stateSearch}")
//	public List<Customer> getAllPath(@PathVariable(required = false) final String countrySearch,
//			@PathVariable(required = false) String stateSearch) {
//
////		return ("countrySearch = " + countrySearch + " stateSearch = " + stateSearch);
//		return customerService.searchCustomers(countrySearch, stateSearch);
//	}

	@GetMapping("/")
	public List<Customer> getAll(@RequestParam(required = false, defaultValue = "") final String countrySearch,
			@RequestParam(required = false, defaultValue = "") final String stateSearch) {

		return customerService.searchCustomers(countrySearch, stateSearch);
	}

	@GetMapping("/sorted/")
	public List<Customer> getAllSorted(@RequestParam(required = false, defaultValue = "") final String countrySearch,
			@RequestParam(required = false, defaultValue = "") final String stateSearch,
			@RequestParam(required = false, defaultValue = "") final String pageNo,
			@RequestParam(required = false, defaultValue = "") final String pageSize,
			@RequestParam(required = false, defaultValue = "") final String sortBy) {
		// Integer pageNo, Integer pageSize, String sortBy(column name)
		return customerService.sortCustomers(countrySearch, stateSearch, pageNo, pageSize, sortBy);
	}

}
