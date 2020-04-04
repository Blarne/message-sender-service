/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.notification.service;

import javax.validation.Valid;

import com.karumien.cloud.sso.api.model.MessageRequest;

/**
 * Service provides notification target.
 *
 * @author <a href="viliam.litavec@karumien.com">Viliam Litavec</a>
 * @since 1.0, 4. 4. 2020 22:07:27
 */
public interface NotificationTargetService {
    
    void send(@Valid MessageRequest message);
    
}
