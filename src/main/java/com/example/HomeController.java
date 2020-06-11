package com.example;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Status;
import io.micronaut.http.exceptions.HttpStatusException;

@Controller
public class HomeController {

    @Status(HttpStatus.OK)
    @Get
    public void index() {
        throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Item not found");
    }
}
