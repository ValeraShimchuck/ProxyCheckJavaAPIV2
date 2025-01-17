package io.github.defiancecoding.proxycheck.examples;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.defiancecoding.proxycheck.api.proxycheck.check.ProxyCheck;
import io.github.defiancecoding.proxycheck.api.proxycheck.check.ProxyCheckSettings;
import io.github.defiancecoding.proxycheck.api.proxycheck.check.results.ProxyResults;

import java.io.IOException;

public class ProxyCheckExample {

    private static ProxyCheck proxyCheck = new ProxyCheck();
    private static ProxyCheckSettings settings = new ProxyCheckSettings();

    /**
     * Please note the settings below will throw a NPE , These settings are merely an example and you shouldn't need all
     * the settings anywho.
     *
     */

    private static void setupProxycheckSettings(){
        settings.setApiKey("APIKey");
        settings.setCheckVpn(true);
        settings.setCheckAsn(true);
        settings.setCheckNode(true);
        settings.setCheckTime(true);
        settings.setRiskLevel(2);
        settings.setCheckPort(true);
        settings.setCheckSeen(true);
        settings.setMaxDetectionDays(7);
        settings.setVer("Ver");
        settings.setTag("ProxyCheckJavaAPI");

    }

    public static void main(String args[]) throws IOException {
        setupProxycheckSettings();

        // Get reponse as a Json but in String format
        String jsonReponse = proxyCheck.getLookupResponse("1.1.1.1");

        // Get response from api as a JsonNode
        JsonNode rawJson = proxyCheck.getRawJsonNode("1.1.1.1");

        // Breakdown JsonNode into getters and setters
        ProxyResults results = proxyCheck.getAndMapResults("1.1.1.1");

        String ip = results.getIp();
        String asn = results.getAsn();
        String proxy = results.getProxy();
        //.... keep going

    }

}
