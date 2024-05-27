package com.fleet.booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.fleet.booking.api.ApiConstants.PATH_STATUS;

@RestController
@RequestMapping(PATH_STATUS)
public class StatusController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getStatus() {
    }
}
