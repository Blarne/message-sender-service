/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.karumien.cloud.notification.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import com.karumien.cloud.notification.exception.SendMessageException;
import com.karumien.cloud.sso.api.model.MessageRequest;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Implementation {@link NotificationTargetService} for Account Management.
 *
 * @author <a href="viliam.litavec@karumien.com">Viliam Litavec</a>
 * @since 1.0, 4. 4. 2020 18:59:57
 */
@Service
public class NotificationTargetServiceImpl implements NotificationTargetService {

    @Value("${message-sender-target.soap-server-url}")
    private String soapServerUrl;

    @Autowired
    private Configuration freemarkerConfig;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void send(@Valid MessageRequest message) {
        
        try {

            Template t = freemarkerConfig.getTemplate("message-request.ftl");
            t.setClassicCompatible(true);
            
            Map<String, Object> context = new HashMap<>();
            context.put("message", message);
            
            String body = FreeMarkerTemplateUtils.processTemplateIntoString(t, context);
            
            RestTemplate restTemplate =  new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept-Encoding", "gzip,deflate");
            headers.add("Content-Type", "text/xml;charset=UTF-8");
            headers.add("SOAPAction", "http://tempuri.org/IMessageSenderSoapService/InsertMessageRequest");

            HttpEntity<String> request = new HttpEntity<String>(body, headers);
            //String response = 
            restTemplate.postForObject(soapServerUrl, request, String.class);

        } catch (TemplateException e) {
            throw new IllegalStateException(e);
        } catch (IOException e) {
            throw new SendMessageException(e.getMessage());
        }
        
    }
    
}
