package com.mv.ct.service;

import com.mv.ct.exception.NoOffersFoundException;
import com.mv.ct.model.CarOffer;
import com.mv.ct.model.type.FuelPolicy;
import com.mv.ct.repository.CarOfferRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;


@SpringBootTest
class CarOfferServiceTest {

    @Autowired
    private CarOfferService carOfferService;


    @Test
    void findAll_NoOffersFound() {

        CarOfferRepository carOfferRepository = Mockito.mock(CarOfferRepository.class);
        CarOfferService carOfferService = new CarOfferService(carOfferRepository);

        Assertions.assertThrows(NoOffersFoundException.class, () -> {
            Mockito.when(carOfferRepository.findAll()).thenReturn(null);
            Collection<CarOffer> offers = carOfferService.findAll();
        });
    }

    @Test
    void findAll_Success() {
        Collection<CarOffer> offers = carOfferService.findAll();


        assertThat(offers, Matchers.notNullValue());

        assertThat(offers, is(notNullValue()));
        assertThat(offers, is(not(empty())));
        assertThat(offers, hasSize(12));
    }

    @Test
    void findAllWithoutFullToFull() {

        Collection<CarOffer> allWithoutFullToFull = carOfferService.findAllWithoutFullToFull();

        assertThat(allWithoutFullToFull, not(contains(
                hasProperty("fuelPolicy", equalTo(FuelPolicy.FULL_FULL)),
                hasProperty("cost", greaterThan(30.0)))));
    }
}