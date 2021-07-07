package com.jumia.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jumia.demo.entities.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerServiceTests {

	private List<Customer> customers;

	@BeforeEach
	public void setUp() {
		customers = new ArrayList<>();
		customers.add(new Customer("Walid Hammadi", "(212) 6007989253", null, null, null, null));
		customers.add(new Customer("Yosaf Karrouch", "(212) 698054317", null, null, null, null));
		customers.add(new Customer("Edunildo Gomes Alberto", "(258) 847651504", null, null, null, null));
		customers.add(new Customer("Manoel da Silva", "(351) 926688222", null, null, null, null));
	}

	@Test
	public void shouldFormatCustomersList() {
		customers = CustomerService.formatCustomersList(customers);

		assertEquals("+212", customers.get(0).getCountryCode());
		assertEquals("Morocco", customers.get(0).getCountry());
		assertEquals("Not valid", customers.get(0).getState());
		assertEquals("6007989253", customers.get(0).getNumber());
		assertEquals("(212) 6007989253", customers.get(0).getPhone());

		assertEquals("+212", customers.get(1).getCountryCode());
		assertEquals("Morocco", customers.get(1).getCountry());
		assertEquals("Valid", customers.get(1).getState());
		assertEquals("698054317", customers.get(1).getNumber());
		assertEquals("(212) 698054317", customers.get(1).getPhone());

		assertEquals("+258", customers.get(2).getCountryCode());
		assertEquals("Mozambique", customers.get(2).getCountry());
		assertEquals("Valid", customers.get(2).getState());
		assertEquals("847651504", customers.get(2).getNumber());
		assertEquals("(258) 847651504", customers.get(2).getPhone());
	}

	@Test
	public void shouldReturnNullCountryIfPhoneDidNotMatch() {
		customers = CustomerService.formatCustomersList(customers);

		assertEquals("N/A", customers.get(3).getCountryCode());
		assertEquals("N/A", customers.get(3).getCountry());
		assertEquals("Not valid", customers.get(3).getState());
		assertEquals("(351) 926688222", customers.get(3).getPhone());
		assertNull(customers.get(3).getNumber());
	}

	@Test
	public void shouldFilterByCounrty() {
		customers = CustomerService.formatCustomersList(customers);
		customers = CustomerService.filterByCountry(customers, "morocco");

		assertEquals(2, customers.size());
	}

	@Test
	public void shouldReturnEmptyListIfCountrySearchFilterDidNotMatch() {
		customers = CustomerService.formatCustomersList(customers);
		customers = CustomerService.filterByCountry(customers, "portugal");

		assertEquals(0, customers.size());
	}

	@Test
	public void shouldFilterByState() {
		customers = CustomerService.formatCustomersList(customers);
		customers = CustomerService.filterByState(customers, "valid");

		assertEquals(2, customers.size());
	}

	@Test
	public void shouldReturnEmptyListIfStateSearchFilterDidNotMatch() {
		customers = CustomerService.formatCustomersList(customers);
		customers = CustomerService.filterByState(customers, "x");

		assertEquals(0, customers.size());
	}

}
