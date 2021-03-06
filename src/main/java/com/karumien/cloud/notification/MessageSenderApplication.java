/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from
 * unauthorized changes to the source code.
 */
/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.notification;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * Message Sender Service Application.
 *
 * @author <a href="miroslav.svoboda@karumien.com">Miroslav Svoboda</a>
 * @since 1.0, 4. 4. 2020 13:53:43
 */
@SpringBootApplication
// @EnableDiscoveryClient
// @EnableHystrix
// @EnableHystrixDashboard
// @EnableFeignClients
@EnableAspectJAutoProxy
@Slf4j
@EnableAutoConfiguration(exclude = { FreeMarkerAutoConfiguration.class })
public class MessageSenderApplication {

    public static void main(String[] args) {

        disableCertificatesValidation();
        SpringApplication.run(MessageSenderApplication.class, args);

        MDC.put("group", "Message Service API");
        log.info("Message Service API started");

    }

    public static void disableCertificatesValidation() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
        }
    }

}
