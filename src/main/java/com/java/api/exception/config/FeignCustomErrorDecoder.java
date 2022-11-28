package com.java.api.exception.config;

import com.java.api.exception.config.GlobalExceptionHandler;
import com.java.api.exception.FeignClientException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder implements ErrorDecoder {
    @Override public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                //handle exception
                return new FeignClientException("Bad Request Through Feign", GlobalErrorCode.ERROR_FEIGN_CLIENT);
            case 401:
                //handle exception
                return new FeignClientException("Unauthorized Request Through Feign", GlobalErrorCode.ERROR_FEIGN_CLIENT);
            case 404:
                //handle exception
                return new FeignClientException("Unidentified Request Through Feign", GlobalErrorCode.ERROR_FEIGN_CLIENT);
            default:
                //handle exception
                return new FeignClientException("Common Feign Exception", GlobalErrorCode.ERROR_FEIGN_CLIENT);
        }
    }
}