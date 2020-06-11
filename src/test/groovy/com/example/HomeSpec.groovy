package com.example

import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.hateoas.JsonError
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class HomeSpec extends Specification {
    @Inject
    AppClient client

    void 'test binding JsonError works'() {
        when:
        client.index()

        then:
        HttpClientResponseException e = thrown()
        e.status == HttpStatus.BAD_REQUEST

        when:
        Optional<JsonError> jsonErrorOptional = e.response.getBody(JsonError)

        then:
        jsonErrorOptional.isPresent()
        jsonErrorOptional.get().message == "Item not found"
    }

    @Client(value = "/", errorType = JsonError)
    static interface AppClient {
        @Get(uri = "/", consumes = MediaType.APPLICATION_JSON)
        HttpStatus index()
    }
}