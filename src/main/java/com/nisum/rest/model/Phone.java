package com.nisum.rest.model;

import javax.persistence.Embeddable;

@Embeddable
public class Phone {

	private String number;
	private String citycode;
	private String countrycode;

	public Phone() {
	}

	public Phone(String number, String citycode, String countrycode) {
		this.number = number;
		this.citycode = citycode;
		this.countrycode = countrycode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

}
