package com.assessment.bl;

import com.assessment.dc.Constants;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfiguration {
    private static Properties props = null;

    public String getProperty(String key) {
        String property;
        try {
            Properties props = this.getProperties();
            property = props.getProperty(key);
        }
        catch (Exception e) {
            property = null;
        }
        return property;
    }

    public Properties getProperties() throws Exception {
        if (props != null) {
            return props;
        }
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(Constants.PROPERTIES_FILENAME)) {
            props = new Properties();
            props.load(inputStream);
        }
        catch (Exception e) {
            throw new Exception("Cannot load properties file" + e);
        }

        return props;
    }
}