package com.demo.util;

import java.util.Arrays;

public enum SERVICE_PROVIDER_MONTH {
	JANUARY("Jan"), FEBRUARY("Feb"), MARCH("March"), APRIL("April"), MAY("May"), JUNE("June"), JULY("July"),
	AUGUST("Aug"), SEPTEMBER("Sept"), OCTOBER("Oct"), NOVEMBER("Nov"), DECEMBER("Dec");
	private String text;

	SERVICE_PROVIDER_MONTH(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static SERVICE_PROVIDER_MONTH getEnumFromString(String string) {
		return Arrays.stream(SERVICE_PROVIDER_MONTH.values())
        .filter(v -> v.getText().equals(string))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("unknown value: " + string));
	}
}
