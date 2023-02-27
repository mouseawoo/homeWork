package com.example.homw;

import com.example.homw.service.CarService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class HomwApplicationTests {


	CarService carService;
	WebApplicationContext ctx;
	MockMvc mvc;
	@Autowired
	public HomwApplicationTests(CarService carService, WebApplicationContext ctx) {
		this.carService = carService;
		this.ctx = ctx;
		this.mvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
	}

	@Test
	@Sql(value = {"/script/before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(value = {"/script/after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	void ifCarDoesntExist() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/reserved?id=100"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
		Assertions.assertTrue(100 > carService.getListCars().size());
	}

	@Test
	@Sql(value = {"/script/before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(value = {"/script/after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	void ifCarIsReserved() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/reserved?id=10"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		Assertions.assertEquals(true, carService.carIsReserved(10L).isReserved());
	}

	@Test
	@Sql(value = {"/script/before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(value = {"/script/after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	void ifAllGood() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/reserved?id=3"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Assertions.assertEquals(false, carService.carIsReserved(3L).isReserved());
	}

}
