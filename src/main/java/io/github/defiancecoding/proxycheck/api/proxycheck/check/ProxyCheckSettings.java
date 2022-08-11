package io.github.defiancecoding.proxycheck.api.proxycheck.check;

public class ProxyCheckSettings
{
  private String apiKey = "";
  private boolean checkVpn = true;
  private boolean checkAsn = true;
  private boolean checkNode = true;
  private boolean checkTime = true;
  private int checkRisk = 1;
  private boolean checkPort = true;
  private boolean checkSeen = true;
  private int maxDetectionDays = 7;
  private String ver;
  private String tag = "Defiant-Proxy-Detection-System";


  /**
   * Sets the APIKey for use within queries with https://proxycheck.io
   * @return apiKey String - APIKey for https://proxycheck.io
   */
  public String getApiKey() {
    return this.apiKey;
  }

  /**
   * @param api_key String - APIKey for https://proxycheck.io
   */
  public void setApiKey(String api_key) {
    this.apiKey = api_key;
  }

  /**
   * @return boolean - should VPNChecks run?
   */
  public boolean isCheckVpn() {
    return this.checkVpn;
  }

  /**
   * When the vpn flag is supplied we will perform a VPN check on the IP Address and present the result to you.
   * @param checkVpn boolean - Should VPNChecks run?
   */
  public void setCheckVpn(boolean checkVpn) {
    this.checkVpn = checkVpn;
  }

  /**
   * @return boolean - should ASN be queried?
   */
  public boolean isCheckAsn() {
    return this.checkAsn;
  }

  /**
   * When the asn flag is supplied we will perform an ASN check on the IP Address and present you with the provider name, ASN, Continent, Country, Country ISOCode, Region, Region ISOCode, City (if it's in a city) and Lat/Long for the IP Address.
   *
   * Provider, Country, Region and City names will be encoded using utf8 for maximum compatibility.
   * @param checkAsn boolean - should ASN be queried?
   */
  public void setCheckAsn(boolean checkAsn) {
    this.checkAsn = checkAsn;
  }

  /**
   * @return boolean - should node be queried?
   */
  public boolean isCheckNode() {
    return this.checkNode;
  }

  /**
   * When the node flag is supplied we will display which node within our cluster answered your API call.
   * This is only really needed for diagnosing problems with our support staff.
   * @param checkNode boolean - Should node be queried?
   */
  public void setCheckNode(boolean checkNode) {
    this.checkNode = checkNode;
  }

  /**
   * Get if the API should tell you the time it took to query the address
   * @return boolean should the time flag be enabled?
   */
  public boolean isCheckTime() {
    return this.checkTime;
  }

  /**
   * When the time flag is supplied we will display how long this query took to be answered by our API excluding network overhead.
   * @param checkTime boolean - should the time flag be enabled
   */
  public void setCheckTime(boolean checkTime) {
    this.checkTime = checkTime;
  }

  /**
   * Get the risk/strictness level || ACCEPTABLE VALUES [0,1,2]
   * @return the risk/strictness level
   */
  public int getRiskLevel() {
    return this.checkRisk;
  }

  /**
   * When the risk flag is set to 1 we will provide you with a risk score for this IP Address ranging from 0 to 100.
   * Scores below 33 can be considered low risk while scores between 34 and 66 can be considered high risk.
   * Addresses with scores above 66 are considered very dangerous.
   *
   *
   * When the risk flag is set to 2 we will still provide you with the above risk score,
   * but you'll also receive individual counts for each type of attack we witnessed this IP performing across our customer
   * network and our own honeypots within the days you specify by the days flag.
   * @param checkRisk the risk/strictness level
   */
  public void setRiskLevel(int checkRisk) {
    this.checkRisk = checkRisk;
  }

  /**
   * Get if the API should tell you the active port the proxy is using
   * @return boolean - should the port flag be enabled?
   */
  public boolean isCheckPort() {
    return this.checkPort;
  }

  /**
   * When the port flag is supplied we will display to you the port number we saw this IP Address operating a proxy server on.
   * @param checkPort boolean - should the port flag be enabled?
   */
  public void setCheckPort(boolean checkPort) {
    this.checkPort = checkPort;
  }

  /**
   * Get if the API should return the last time there was activity on the address
   * @return should the seen flag be enabled?
   */
  public boolean isCheckSeen() {
    return this.checkSeen;
  }

  /**
   * When the seen flag is supplied we will display to you the most recent time we saw this IP Address operating as a proxy server.
   * @param checkSeen boolean - should the seen flag be enabled?
   */
  public void setCheckSeen(boolean checkSeen) {
    this.checkSeen = checkSeen;
  }


  /**
   * When the days flag is supplied we will restrict our proxy results to between now and the amount of days you specify.
   * For example if you supplied days=2 we would only check our database for Proxies that we saw within the past 48 hours.
   * By default, without this flag supplied we search within the past 7 days.
   * @return int - days to restrict results to?
   */
  public int getMaxDetectionDays() {
    return this.maxDetectionDays;
  }

  /**
   * @param maxDetectionDays int - days to restrict results to?
   */
  public void setMaxDetectionDays(int maxDetectionDays) {
    this.maxDetectionDays = maxDetectionDays;
  }

  /**
   * @return String - tag to add along queries for easier identification.
   */
  public String getTag() {
    return this.tag;
  }

  /**
   * When the tag flag is supplied we will tag your query with the message you supply. You can supply your tag using the POST method, and we recommend you do so.
   * @param setTag String - tag to add along queries for easier identification.
   */
  public void setTag(String setTag) {
    this.tag = setTag;
  }

  /**
   * Override the API version selection you have set in the customer dashboard with a different version.
   *
   * You can view the version dates supported by ver from within the dashboard.
   * Format examples: ver=16-August-2020 or ver=17-November-2020
   * @param ver apiVersion to use?
   */
  public void setVer(String ver) {
    this.ver = ver;
  }

  /**
   * @return apiVersion to use?
   */
  public String getVer() {
    return this.ver;
  }
}
