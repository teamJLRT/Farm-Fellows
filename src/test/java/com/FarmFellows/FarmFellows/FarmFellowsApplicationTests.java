package com.FarmFellows.FarmFellows;

import com.FarmFellows.FarmFellows.models.Crop;
import com.FarmFellows.FarmFellows.models.Farmer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class FarmFellowsApplicationTests {
	Crop crop;
	Farmer farmer;

	@Test
	void contextLoads() {
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

}
