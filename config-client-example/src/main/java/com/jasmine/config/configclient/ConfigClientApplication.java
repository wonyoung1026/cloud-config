package com.jasmine.config.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RefreshScope
@RestController
public class ConfigClientApplication {

	@Value("${api.1.key}")
	private String api_1_key;

	@Value("${api.2.key}")
	private String api_2_key;

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@RequestMapping(
			value = "/api/{api_code}",
			method = RequestMethod.GET,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String user(@PathVariable("api_code") String api_code) {
		String api_key;
		if(api_code.equals("1"))
			api_key = api_1_key;
		else if(api_code.equals("2"))
			api_key = api_2_key;
		else
			api_key = "api " + api_code +" not found!";

		return String.format("Api code : %s \n Api key : %s...\n", api_code, api_key);
	}
}