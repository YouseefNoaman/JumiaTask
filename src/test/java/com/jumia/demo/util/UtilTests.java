package com.jumia.demo.util;

import org.junit.jupiter.api.Test;

import com.jumia.demo.utils.Country;
import com.jumia.demo.utils.CountryCodeMatcher;

import static org.junit.jupiter.api.Assertions.*;

class UtilTests {

	@Test
	public void shouldReturnNullForNoCountryCodeMatch() {
		assertNull(CountryCodeMatcher.matchCountryCode("123"));
	}

	@Test
	public void shouldReturnCountryForCountryCodeMatch() {
		assertEquals(Country.MOROCCO, CountryCodeMatcher.matchCountryCode("(212) 6617344445"));
	}

	@Test
	public void shouldReturnNullForPhoneMatch() {
		assertNull(CountryCodeMatcher.matchPhone("123"));
	}

	@Test
	public void shouldReturnCountryForPhoneMatch() {
		assertEquals(Country.MOROCCO, CountryCodeMatcher.matchPhone("(212) 698054317"));
	}
}
