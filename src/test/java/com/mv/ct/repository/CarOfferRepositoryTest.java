package com.mv.ct.repository;

import com.mv.ct.model.CarOffer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;

@SpringBootTest
class CarOfferRepositoryTest {

    @Autowired
    private CarOfferRepository carOfferRepository;

    @Test
    void findAll_Success() {

        Collection<CarOffer> allOffers = carOfferRepository.findAll();

        assertThat(allOffers, Matchers.notNullValue());

        assertThat(allOffers, is(notNullValue()));
        assertThat(allOffers, is(not(empty())));
    }
}
