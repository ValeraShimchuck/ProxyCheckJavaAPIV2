package io.github.defiancecoding.proxycheck.api.proxycheck.check;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import io.github.defiancecoding.proxycheck.api.proxycheck.check.results.ProxyResults;
import io.github.defiancecoding.proxycheck.api.webconnection.HTTPQuery;

public class ProxyCheck
{
  private final ProxyCheckSettings settings;
  private final HTTPQuery httpQuery = new HTTPQuery();

  public ProxyCheck(ProxyCheckSettings settings) {
    this.settings = settings;
  }

  public ProxyCheck() {
    this(new ProxyCheckSettings());
  }

  /**
   * Builds the API URL based on selected settings
   *
   * @param ip IP address you want to query
   * @return jsonString response from HTTPQuery
   */
  private String urlBuilder(String ip) {
    String baseURL = "http://proxycheck.io/v2/" + ip;
    baseURL = baseURL + "?key=" + this.settings.getApiKey();
    baseURL = baseURL + "&vpn=" + (this.settings.isCheckVpn() ? 1 : 0);
    baseURL = baseURL + "&asn=" + (this.settings.isCheckAsn() ? 1 : 0);
    baseURL = baseURL + "&node=" + (this.settings.isCheckNode() ? 1 : 0);
    baseURL = baseURL + "&time=" + (this.settings.isCheckPort() ? 1 : 0);
    baseURL = baseURL + "&risk=" + this.settings.getRiskLevel();
    baseURL = baseURL + "&port=" + (this.settings.isCheckPort() ? 1 : 0);
    baseURL = baseURL + "&seen=" + (this.settings.isCheckSeen() ? 1 : 0);
    baseURL = baseURL + "&time=" + (this.settings.isCheckTime() ? 1 : 0);
    baseURL = baseURL + "&days=" + this.settings.getMaxDetectionDays();
    if (this.settings.getVer() != null) {
      baseURL = baseURL + "&ver=" + this.settings.getVer();
    }
    baseURL = baseURL + "&tag=" + this.settings.getTag();
    return baseURL;
  }

  public ProxyCheckSettings getSettings() {
    return settings;
  }

  /**
   * Builds the API URL based on selected settings
   *
   * @param ip IP address you want to query
   * @return jsonString response from HTTPQuery in STRING value
   */
  public String getLookupResponse(String ip) {
    return this.httpQuery.sendGet(urlBuilder(ip));
  }


  /**
   * Builds the API URL based on selected settings
   *
   * @param ip IP address you want to query
   * @return jsonString response from HTTPQuery in JsonNode value
   * @throws IOException when jsonSource isnt valid
   */
  public JsonNode getRawJsonNode(String ip) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readTree(getLookupResponse(ip));
  }


  /**
   * Maps the results from the provided IP provided from the API to a class with getters and setters
   * @param ip IP address you want to query and map
   * @return ProxyResults Object with built-in getters and setters
   * @see ProxyResults
   */
  public ProxyResults getAndMapResults(String ip) {
    ProxyResults result = new ProxyResults();
    
    try {
      JsonNode rawNode = getRawJsonNode(ip);
      
      result.setStatus(rawNode.get("status").asText());
      if (rawNode.get("message") != null) result.setStatusMessage(rawNode.get("message").asText());
      result.setNode(rawNode.get("node").asText());
      result.setQueryTime(rawNode.get("query time").asText());
      
      ObjectMapper mapper = new ObjectMapper();
      JsonNode subNode = mapper.readTree(String.valueOf(rawNode.get(ip)));
      
      result.setIp(ip);
      if (subNode.get("provider") != null) result.setProvider(subNode.get("provider").asText()); 
      if (subNode.get("continent") != null) result.setContinent(subNode.get("continent").asText()); 
      if (subNode.get("country") != null) result.setCountry(subNode.get("country").asText()); 
      if (subNode.get("city") != null) result.setCity(subNode.get("city").asText()); 
      if (subNode.get("region") != null) result.setRegion(subNode.get("region").asText()); 
      if (subNode.get("regioncode") != null) result.setRegionCode(subNode.get("regioncode").asText()); 
      if (subNode.get("latitude") != null) result.setLatitude(subNode.get("latitude").asText()); 
      if (subNode.get("longitude") != null) result.setLongitude(subNode.get("longitude").asText()); 
      if (subNode.get("isocode") != null) result.setIsoCode(subNode.get("isocode").asText()); 
      if (subNode.get("proxy") != null) result.setProxy(subNode.get("proxy").asText()); 
      if (subNode.get("type") != null) result.setType(subNode.get("type").asText()); 
      if (subNode.get("port") != null) result.setPort(subNode.get("port").asText("0000")); 
      if (subNode.get("risk") != null) result.setRisk(subNode.get("risk").asText()); 
      if (subNode.get("last seen human") != null) result.setLastSeenHuman(subNode.get("last seen human").asText()); 
      if (subNode.get("last seen unix") != null) result.setLastSeenUnix(subNode.get("last seen unix").asText());
      
      JsonNode attackHistory = subNode.get("attack history");
      if (attackHistory != null) {
        result.setTotal(attackHistory.get("Total").asText());
        result.setVulnerabilityProbing(attackHistory.get("Vulnerability Probing").asText());
        result.setForumSpam(attackHistory.get("Forum Spam").asText());
        result.setRegistrationAttempt(attackHistory.get("Registration Attempt").asText());
        result.setLoginAttempt(attackHistory.get("Login Attempt").asText());
      }
    }
    catch (IOException ex) {
      ex.printStackTrace();
    } 
    return result;
  }
}
