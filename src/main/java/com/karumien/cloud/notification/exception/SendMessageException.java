/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from 
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Send Message Exception - {@link HttpStatus#UNPROCESSABLE_ENTITY}.
 *
 * @author <a href="miroslav.svoboda@karumien.com">Miroslav Svoboda</a>
 * @since 1.0, 10. 6. 2019 13:56:31
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class SendMessageException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public SendMessageException(String message) {
        super(message);
    }
    
}



