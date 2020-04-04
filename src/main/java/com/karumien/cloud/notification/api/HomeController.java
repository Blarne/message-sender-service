/*
 * Copyright (c) 2019-2029 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from 
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.notification.api;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;

/**
 * Home redirection to swagger api documentation.
 *
 * @author <a href="miroslav.svoboda@karumien.com">Miroslav Svoboda</a>
 * @since 1.0, 15. 7. 2019 21:27:49
 */
@Controller
@Api(value = "Info Service", description = "Home Rewrite and Application Informations", tags = { "Info Service" })
public class HomeController {
    

    @GetMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping(value = "/locale", produces = "text/plain")
    public ResponseEntity<Void> getLocale() {
        return new ResponseEntity(""+LocaleContextHolder.getLocale(), HttpStatus.OK);
    }
}
