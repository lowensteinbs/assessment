package com.assessment.bl;

import static org.junit.Assert.*;
import java.util.Properties;
import org.junit.Test;

public class ReadConfigurationTest {
	private String devProperty = "http://www.google.com";
    private String prodProperty = "https://www.expapp.com/";
    
	@Test
    public void testGetProperty() {
        ReadConfiguration readConfiguration = new ReadConfiguration();
        String devProp = readConfiguration.getProperty("URL.DEV");
        String prodProp = readConfiguration.getProperty("URL.PROD");
        String nullProp = readConfiguration.getProperty("URL.QA");

        assertEquals(devProp, devProperty);
        assertEquals(prodProp, prodProperty);
        assertEquals(null, nullProp);
    }

    @Test
    public void testGetProperties() {
        ReadConfiguration readConfiguration = new ReadConfiguration();
        Properties props = null;
        
        try {
        	props = readConfiguration.getProperties();
        }
        catch (Exception e) {
        	fail("Cannot read properties file");
        }

        assertEquals(props.getProperty("URL.DEV"), devProperty);
        assertEquals(props.getProperty("URL.PROD"), prodProperty);
    }
}
