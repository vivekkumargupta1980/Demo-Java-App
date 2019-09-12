package com.demo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.demo.model.ServiceProviderData;

public class ServiceUtils {
	
	public static List<String> getHighSpeedServiceProvidersEveryMonth(List<ServiceProviderData> serviceProviderDataList) {
		Map<SERVICE_PROVIDER_MONTH, List<Integer>> monthAndListMap = new TreeMap<>();
		serviceProviderDataList.forEach(serviceProviderData -> {
			Map<String, Map<SERVICE_PROVIDER_MONTH, Integer>> spDataMaps = serviceProviderData.getServiceProviderData();
			spDataMaps.entrySet().forEach(entry -> {
				Map<SERVICE_PROVIDER_MONTH, Integer> monthAndValueMap = entry.getValue();
				monthAndValueMap.entrySet().forEach(e -> {
					List<Integer> list = monthAndListMap.get(e.getKey());
					if (list == null) {
						list = new ArrayList<>();
						list.add(e.getValue());
					} else {
						list.add(e.getValue());
					}
					monthAndListMap.put(e.getKey(), list);
				});
			});
		});
		
		Map<SERVICE_PROVIDER_MONTH, Integer> monthAndMaxValue = new TreeMap<>();
		monthAndListMap.entrySet().forEach(sp -> {
			Integer maxValue = Collections.max(sp.getValue());
			monthAndMaxValue.putIfAbsent(sp.getKey(), maxValue);
		});
		
		List<String> providersNames = new ArrayList<>();
		serviceProviderDataList.forEach(serviceProviderData -> {
			Map<String, Map<SERVICE_PROVIDER_MONTH, Integer>> spDataMaps = serviceProviderData.getServiceProviderData();
			spDataMaps.entrySet().forEach(entry -> {
				Map<SERVICE_PROVIDER_MONTH, Integer> monthAndValueMap = entry.getValue();
				monthAndValueMap.entrySet().forEach(e -> {
					Integer value = e.getValue();
					Integer monthMaxValue = monthAndMaxValue.get(e.getKey());
					if (monthMaxValue.equals(value)) {
						providersNames.add(entry.getKey());
					}
				});
			});
		});
		Collections.sort(providersNames);
		return providersNames;
	}
}
