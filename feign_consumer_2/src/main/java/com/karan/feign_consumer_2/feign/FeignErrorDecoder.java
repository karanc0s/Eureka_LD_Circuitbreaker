package com.karan.feign_consumer_2.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if(response.status() == 500) {
            return new RuntimeException("Error 500");
        }else if(response.status() == 404) {
            return new NotFoundException("Error 404");
        }
        return null;
    }
}
