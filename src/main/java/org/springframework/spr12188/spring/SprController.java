package org.springframework.spr12188.spring;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/")
public class SprController {

    @RequestMapping
    @ResponseStatus(OK)
    public Map<String, ?> success() {

        return singletonMap("success", true);
    }

    @RequestMapping("/fail")
    public void fail() {

        throw new Spr12188Exception();
    }

    @ExceptionHandler
    @ResponseStatus(GONE)
    public Map<String, ?> handleException(Spr12188Exception e) {

        return singletonMap("success", false);
    }

    private static class Spr12188Exception extends RuntimeException {

        private Spr12188Exception() {
            super("This exception should be handled.");
        }
    }
}
