package ru.stqa.pft.soap;


import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.152");
    char c = geoIP.charAt(16);
    char o = geoIP.charAt(17);
    assertEquals((String.format("%c%c", c, o)), "RU");
  }

  @Test
  public void testInvalidIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.xxx");
    char c = geoIP.charAt(16);
    char o = geoIP.charAt(17);
    assertEquals((String.format("%c%c", c, o)), "US");
  }
}
