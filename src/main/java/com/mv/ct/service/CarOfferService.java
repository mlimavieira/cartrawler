package com.mv.ct.service;


import com.mv.ct.exception.NoOffersFoundException;
import com.mv.ct.model.CarOffer;
import com.mv.ct.model.type.FuelPolicy;
import com.mv.ct.repository.CarOfferRepository;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.mv.ct.util.ComparatorUtil.*;

@Service
public class CarOfferService {

    private CarOfferRepository carOfferRepository;

    @Autowired
    public CarOfferService(CarOfferRepository repository) {
        this.carOfferRepository = repository;
    }

    private List<String> ENTERPRISE_SUPPLIERS = Arrays.asList("AVIS", "BUDGET", //
            "ENTERPRISE", "FIREFLY", //
            "HERTZ", "SIXT", "THRIFTY"); //

    public Collection<CarOffer> findAllWithDuplicates(boolean sorted) {
        return sorted ? sort(listAll()) : listAll();
    }

    public Collection<CarOffer> findAll() {

        Collection<CarOffer> allOffers = listAll();

        // Removing duplicates
        List<CarOffer> list = new ArrayList<>(new HashSet<>(allOffers));

        return sort(list);
    }

    public Collection<CarOffer> findAllWithoutFullToFull() {

        Collection<CarOffer> list = findAll();

        double[] prices = list.stream().mapToDouble(c -> c.getCost()).toArray();

        Median median = new Median();
        double value = median.evaluate(prices);

        list.removeIf(c -> c.getFuelPolicy().equals(FuelPolicy.FULL_FULL) && c.getCost() > value);

        return list;
    }

    private List<CarOffer> sort(Collection<CarOffer> list) {
        return list.stream()
                .sorted(getEnterpriseComparator()
                        .thenComparing(getGroupsComparator())
                        .thenComparing(getPriceComparator())).collect(Collectors.toList());
    }

    private Collection<CarOffer> listAll() {
        Collection<CarOffer> allOffers = carOfferRepository.findAll();

        if (allOffers == null) {
            throw new NoOffersFoundException("No offers found.");
        }
        return allOffers;
    }
}
