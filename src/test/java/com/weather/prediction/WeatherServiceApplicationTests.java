package com.weather.prediction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


@SpringBootTest
class WeatherServiceApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {

		String appName = context.getApplicationName();
		Assert.notNull(context, appName);

	}

}
