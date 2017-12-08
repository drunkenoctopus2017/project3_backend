package com.revature.octo.story;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OctoStoryServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@SuppressWarnings("deprecation")
	@Test
	public void forceOpen() {
		Assert.assertEquals(true, hystrixWorks());
		ConfigurationManager.getConfigInstance().getProperty("hystrix.command.FakeCommand.circuitBreaker.forceOpen");
		Assert.assertEquals(true, hystrixWorks());
	}
	
	@HystrixCommand(fallbackMethod = "reliable")
	public boolean hystrixWorks() {
		return true;
	}
	
	public boolean reliable() {
		return false;
	}
}
