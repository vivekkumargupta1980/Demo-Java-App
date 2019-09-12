package com.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.platform.commons.util.StringUtils;
import com.demo.model.ServiceProviderData;
import com.demo.util.SERVICE_PROVIDER_MONTH;
import com.demo.util.ServiceUtils;

public class Solution {
	private static final String PATH_TO_CSV = "C:\\Users\\vivekkumar.gupta\\Downloads\\Supported_Data.csv";
	private List<ServiceProviderData> serviceProviderDataList = new ArrayList<>();

	public static void main(String[] args) {
		Solution solution = new Solution();
		try {
			solution.processCSVFile(null);
		} catch (IOException e) {
			System.out.println("Exception while closing file from path - " + PATH_TO_CSV);
			e.printStackTrace();
		}
		List<String> providersNames = ServiceUtils.getHighSpeedServiceProvidersEveryMonth(solution.serviceProviderDataList);
		System.out.println("1." + providersNames);
		
		Map<String, Long> providersNamesAndCount = new TreeMap<>();
		providersNamesAndCount.putAll(providersNames.stream().collect(Collectors.groupingBy(pn -> pn, Collectors.counting())));
		System.out.println("2." + providersNamesAndCount);
	}

	public void addServiceProvidersData(String serviceProviderName, List<String> monthsList, List<Integer> serviceProviderDataum) {
		if (monthsList.size() == serviceProviderDataum.size()) {
			Map<String, Map<SERVICE_PROVIDER_MONTH, Integer>> sMap = new TreeMap<>();
			Map<SERVICE_PROVIDER_MONTH, Integer> serviceProviderData = new TreeMap<>();
			for (int idx = 0; idx < monthsList.size(); idx++) {
				serviceProviderData.putIfAbsent(SERVICE_PROVIDER_MONTH.getEnumFromString(monthsList.get(idx)), serviceProviderDataum.get(idx));
			}
			sMap.putIfAbsent(serviceProviderName, serviceProviderData);
			ServiceProviderData serviceProvider = new ServiceProviderData();
			serviceProvider.setServiceProviderData(sMap);
			serviceProviderDataList.add(serviceProvider);
		}
	}

	public void processCSVFile(String pathToCSVFile) throws IOException {
		String row;
		int months = -1;
		BufferedReader csvReader = null;
		try {
			if (pathToCSVFile == null) {
				csvReader = new BufferedReader(new FileReader(PATH_TO_CSV));
			} else {
				csvReader = new BufferedReader(new FileReader(pathToCSVFile));
			}
			List<String> monthsList = new ArrayList<>();
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (months < 0) {
					for (String str : data) {
						if (StringUtils.isNotBlank(str)) {
							monthsList.add(str);
						}
					}
					months++;
				} else {
					String serviceProviderName = null;
					List<Integer> serviceProviderDataum = new ArrayList<Integer>();
					for (String str : data) {
						if (serviceProviderName == null) {
							serviceProviderName = str;
						} else {
							serviceProviderDataum.add(Integer.valueOf(str));
						}
					}
					addServiceProvidersData(serviceProviderName, monthsList, serviceProviderDataum);
				}
			}
		} catch (IOException ex) {
			System.out.println("Exception while reading file from path - " + PATH_TO_CSV);
			throw ex;
		} finally {
			if(csvReader != null) {
				csvReader.close();
			}
		}
	}
}
