package com.jumia.demo.utils;

import java.util.regex.Pattern;

public class CountryCodeMatcher {

	public static Country matchPhone(final String phone) {
		for (Country country : Country.values()) {
			Pattern r = Pattern.compile(country.getPhoneRegex());
			if (r.matcher(phone).matches()) {
				return country;
			}
		}
		return null;
	}

	public static Country matchCountryCode(final String phone) {
		for (Country country : Country.values()) {
			Pattern r = Pattern.compile(country.getCountryCodeRegex());
			if (r.matcher(phone).find()) {
				return country;
			}
		}
		return null;
	}

}
