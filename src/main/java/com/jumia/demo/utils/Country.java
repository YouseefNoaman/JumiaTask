package com.jumia.demo.utils;

public enum Country {

	CAMEROON("Cameroon", "+237", "\\(237\\)\\ ?[2368]\\d{7,8}$", "\\(237\\)\\ "),
	ETHIOPIA("Ethiopia", "+251", "\\(251\\)\\ ?[1-59]\\d{8}$", "\\(251\\)\\ "),
	MOROCCO("Morocco", "+212", "\\(212\\)\\ ?[5-9]\\d{8}$", "\\(212\\)\\ "),
	MOZAMBIQUE("Mozambique", "+258", "\\(258\\)\\ ?[28]\\d{7,8}$", "\\(258\\)\\ "),
	UGANDA("Uganda", "+256", "\\(256\\)\\ ?\\d{9}$", "\\(256\\)\\ ");

	private final String name;
	private final String code;
	private final String phoneRegex;
	private final String countryCodeRegex;

	Country(String name, String code, String phoneRegex, String countryCodeRegex) {
		this.name = name;
		this.code = code;
		this.phoneRegex = phoneRegex;
		this.countryCodeRegex = countryCodeRegex;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getPhoneRegex() {
		return phoneRegex;
	}

	public String getCountryCodeRegex() {
		return countryCodeRegex;
	}
}
