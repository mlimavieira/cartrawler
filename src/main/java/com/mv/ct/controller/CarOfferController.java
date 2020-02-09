package com.mv.ct.controller;


import com.mv.ct.exception.NoOffersFoundException;
import com.mv.ct.model.CarOffer;
import com.mv.ct.service.CarOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CarOfferController {

    private CarOfferService carOfferService;

    @Autowired
    public CarOfferController(CarOfferService carOfferService) {
        this.carOfferService = carOfferService;
    }

    @GetMapping("/caroffer")
    public Collection<CarOffer> list() {

        return carOfferService.findAll();
    }

    @GetMapping("/caroffer/all")
    public Collection<CarOffer> listAll(@RequestParam(value = "sorted", defaultValue = "true") boolean sorted) {

        return carOfferService.findAllWithDuplicates(sorted);
    }

    @GetMapping("/caroffer/median")
    public Collection<CarOffer> getListWithoutFull() {

        return carOfferService.findAllWithoutFullToFull();
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessageDto handleException() {

        return new ErrorMessageDto("500", "Internal error");
    }

    @ExceptionHandler(NoOffersFoundException.class)
    public ErrorMessageDto handleNoOffersFoundException() {

        return new ErrorMessageDto("404", "No offers found");
    }
}
