package com.FarmFellows.FarmFellows;

import com.FarmFellows.FarmFellows.controllers.FarmerController;
import com.FarmFellows.FarmFellows.models.Crop;
import com.FarmFellows.FarmFellows.models.Farmer;
import com.FarmFellows.FarmFellows.models.Planting;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc

class FarmFellowsApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private FarmerController farmerController;
	Crop crop;
	Farmer farmer;
	Planting planting;

	@Test
	void contextLoads() {
	}
	@Test
	public void hasHomePagePresent() throws Exception{
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect((content().string(containsString("Rexie"))));
	}

	@Test
	public void testCropConstructor() {
		crop = new Crop("bean", 10, 15, 100);
		assertEquals(false, crop.getCropName().isEmpty());
	}

	@Test
	public void testCropGrowTime() {
		crop = new Crop("bean", 10, 15, 100);
		assertEquals(100, crop.getCropGrowTime());
	}

	@Test
	public void testCropSeedPrice() {
		crop = new Crop("bean", 10, 15, 100);
		assertEquals(10, crop.getSeedPrice());
	}

	@Test
	public void testCropSellPrice() {
		crop = new Crop("bean", 10, 15, 100);
		assertEquals(15, crop.getCropSellPrice());
	}

	@Test
	public void testFarmerFullName() {
		farmer = new Farmer("Lucy Gelderloos", "LuLu");
		assertFalse(false, farmer.getFullName());
	}

	@Test
	public void testFarmerUserName() {
		farmer = new Farmer("Lucy Gelderloos", "LuLu");
		assertEquals("LuLu", farmer.getUserName());

	}

	@Test
	public void testFarmerCropConstructor() {
		farmer = new Farmer("Lucy Gelderloos", "LuLu");
		crop = new Crop("Coffee Bean", 10, 15, 100);
		LocalDateTime now = LocalDateTime.now();
		planting = new Planting(farmer, crop, now, 12);
		assertEquals("Lucy Gelderloos", planting.getFarmer().getFullName());
		assertEquals("Coffee Bean", crop.getCropName());
		System.out.println(planting.getFarmer().getFullName().toString());

	}

}
