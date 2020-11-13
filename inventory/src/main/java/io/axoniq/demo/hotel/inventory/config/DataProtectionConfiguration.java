package io.axoniq.demo.hotel.inventory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.axoniq.dataprotection.cryptoengine.CryptoEngine;
import io.axoniq.dataprotection.cryptoengine.vault.VaultCryptoEngine;
import okhttp3.OkHttpClient;

@Configuration
@ConfigurationProperties(prefix = "account.vault")
public class DataProtectionConfiguration {
    public String getToken() {
        return token;
    }

    private String token;
    private String uri;

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }

    @Bean
    public CryptoEngine cryptoEngine(OkHttpClient httpClient) {
        return new VaultCryptoEngine(httpClient, uri, token, "account");
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
