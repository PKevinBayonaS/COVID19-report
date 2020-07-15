package com.COVID19.estadisticascoronavirus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class DataService {

	private static final String VDATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<CovidDAO> registers = new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void getData() throws IOException, InterruptedException, ParseException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(VDATA_URL);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

				
		StringReader csvData = new StringReader(responseBody);

		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvData);
		
		List<CovidDAO> newStats = new ArrayList<>();
		for (CSVRecord record : records) {
			CovidDAO covidTemp = new CovidDAO();
		    covidTemp.setState(record.get("Province/State"));
		    covidTemp.setContry(record.get("Country/Region"));
		    int latestCases= Integer.parseInt(record.get(record.size()-1));
		    int prevCases = Integer.parseInt(record.get(record.size()-2));
		    covidTemp.setLatestCase(latestCases);
		    covidTemp.setDiffCase(latestCases-prevCases);
		    
		    newStats.add(covidTemp);

		}
		
		this.registers=newStats;
	}

	/**
	 * @return the registers
	 */
	public List<CovidDAO> getRegisters() {
		return registers;
	}
}
