package com.demo.model;

import java.util.Map;
import java.util.TreeMap;

import com.demo.util.SERVICE_PROVIDER_MONTH;

public class ServiceProviderData {
	private Map<String, Map<SERVICE_PROVIDER_MONTH, Integer>> serviceProviderData = new TreeMap<>();

	public ServiceProviderData() {
	}

	public Map<String, Map<SERVICE_PROVIDER_MONTH, Integer>> getServiceProviderData() {
		return serviceProviderData;
	}

	public void setServiceProviderData(Map<String, Map<SERVICE_PROVIDER_MONTH, Integer>> serviceProviderData) {
		this.serviceProviderData = serviceProviderData;
	}

	@Override
	public String toString() {
		return "ServiceProviderData [serviceProviderData=" + serviceProviderData + "]";
	}

}
