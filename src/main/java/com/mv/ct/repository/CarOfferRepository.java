package com.mv.ct.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mv.ct.model.CarOffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Repository
public class CarOfferRepository {

    private Gson gson = new Gson();

    private List<CarOffer> carOffers;

    @PostConstruct
    private void loadData() {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("car_data_with_duplicates.json").toURI());
            String contents = new String(Files.readAllBytes(path));

            carOffers = gson.fromJson(contents, new TypeToken<List<CarOffer>>() {
            }.getType());

        } catch (Exception e) {
            log.error("Error to read car_data_original.json", e);
        }
    }

    public Collection<CarOffer> findAll() {
        return new ArrayList<>(carOffers);
    }
}
