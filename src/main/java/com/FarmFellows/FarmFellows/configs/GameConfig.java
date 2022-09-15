package com.FarmFellows.FarmFellows.configs;

import com.FarmFellows.FarmFellows.models.Crop;
import com.FarmFellows.FarmFellows.models.Farmer;
import com.FarmFellows.FarmFellows.repositories.CropRepository;
import com.FarmFellows.FarmFellows.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Random;

@Configuration
@EnableScheduling
public class GameConfig {

    @Autowired
    CropRepository cropRepository;
    @Autowired
    FarmerRepository farmerRepository;

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void changePrices(){
        List<Crop> allCrops = cropRepository.findAll();
        for(Crop c : allCrops){
            priceChange(c);
        }
    }

    public void priceChange(Crop c){
        Random r = new Random();
        Double change = r.nextDouble();
        if (r.nextBoolean()){
            change = -1 * change;
        }

        Integer buyPrice = c.getSeedPrice();
        Double buyChange = Math.ceil((buyPrice * change)/10);
        Integer newBuyPrice = (int)(buyPrice + buyChange);
        if (newBuyPrice <= 0){
            newBuyPrice = r.nextInt(3) + 1;
        }
        c.setSeedPrice(newBuyPrice);

        Integer sellPrice = c.getCropSellPrice();
        Double sellChange = Math.ceil((sellPrice * change)/10);
        Integer newSellPrice= (int)(sellPrice + sellChange);
        if (newSellPrice <= newBuyPrice){
            newSellPrice = newBuyPrice + r.nextInt(3) + 1;
        }
        c.setCropSellPrice(newSellPrice);

        cropRepository.save(c);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void restoreDailyBoost(){
        List<Farmer> farmers = farmerRepository.findAll();
        for (Farmer f : farmers){
            f.setDailyBoost(true);
        }
    }
}
