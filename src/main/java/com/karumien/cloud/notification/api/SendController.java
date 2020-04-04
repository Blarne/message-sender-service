/*
 * Copyright (c) 2019-2029 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.notification.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.karumien.cloud.notification.service.NotificationTargetService;
import com.karumien.cloud.sso.api.handler.SendApi;
import com.karumien.cloud.sso.api.model.MessageRequest;

import io.swagger.annotations.Api;

/**
 * REST Controller for Identity Service (API).
 * 
 * @author <a href="miroslav.svoboda@karumien.com">Miroslav Svoboda</a>
 * @since 1.0, 18. 7. 2019 11:15:51
 */
@RestController
@Api(value = "Notification Service", description = "Service for sending messages", tags = { "Notification Service" })
public class SendController implements SendApi {

    @Autowired
    private NotificationTargetService notificationTargetService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> sendMessage(@Valid MessageRequest message) {
        notificationTargetService.send(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
