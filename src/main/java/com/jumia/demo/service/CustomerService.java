package com.jumia.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jumia.demo.entities.Customer;
import com.jumia.demo.repositories.CustomerRepository;
import com.jumia.demo.utils.Country;
import com.jumia.demo.utils.CountryCodeMatcher;

@Service
public class CustomerService {
	private static final String VALID_STATE = "Valid";
	private static final String INVALID_STATE = "Not valid";

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	public List<Customer> sortCustomers(String countrySearch, String stateSearch, String pageNo, String pageSize, String sortBy) {
		// pageNo, pageSize, sortBy(column name)
		Pageable paging = PageRequest.of(Integer.valueOf(pageNo), Integer.valueOf(pageSize), Sort.by(sortBy));

		Page<Customer> pagedResult = customerRepository.findAll(paging);

		if (pagedResult.hasContent()) {
			System.out.println(pagedResult.getContent());
			List<Customer> customers = pagedResult.getContent();
			customers = formatCustomersList(customers);

			if (StringUtils.hasLength(countrySearch)) {
				customers = filterByCountry(customers, countrySearch);
			}

			if (StringUtils.hasLength(stateSearch)) {
				customers = filterByState(customers, stateSearch);
			}
			return customers;
		} else {
			return new ArrayList<Customer>();
		}
	}

	public List<Customer> searchCustomers(String countrySearch, String stateSearch) {
		List<Customer> customers = customerRepository.findAll();
		customers = formatCustomersList(customers);

		if (StringUtils.hasLength(countrySearch)) {
			customers = filterByCountry(customers, countrySearch);
		}

		if (StringUtils.hasLength(stateSearch)) {
			customers = filterByState(customers, stateSearch);
		}
		return customers;
	}

	protected static List<Customer> formatCustomersList(List<Customer> customers) {
		customers.forEach(customer -> {
			Country country = CountryCodeMatcher.matchPhone(customer.getPhone());

			if (Objects.isNull(country)) {
				country = CountryCodeMatcher.matchCountryCode(customer.getPhone());
				customer.setState(INVALID_STATE);
			} else {
				customer.setState(VALID_STATE);
			}

			if (Objects.isNull(country)) {
				customer.setCountry("N/A");
				customer.setCountryCode("N/A");
			} else {
				customer.setCountry(country.getName());
				customer.setCountryCode(country.getCode());
				customer.setNumber(customer.getPhone().replaceAll(country.getCountryCodeRegex(), ""));
			}

		});
		return customers;
	}

	protected static List<Customer> filterByCountry(List<Customer> customers, final String countrySearch) {
		return customers.stream().parallel().filter(customer -> countrySearch.equalsIgnoreCase(customer.getCountry()))
				.collect(Collectors.toList());
	}

	protected static List<Customer> filterByState(List<Customer> customers, final String stateSearch) {
		return customers.stream().parallel().filter(customer -> stateSearch.equalsIgnoreCase(customer.getState()))
				.collect(Collectors.toList());
	}

}
