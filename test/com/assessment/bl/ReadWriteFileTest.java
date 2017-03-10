package com.assessment.bl;

import com.assessment.bl.ReadFile;
import com.assessment.bl.WriteFile;
import com.assessment.dc.Constants;
import com.assessment.dc.URLResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import org.junit.Test;

public class ReadWriteFileTest {

	void setUp() {
        File file = new File(Constants.OUTPUT_FILENAME);
        file.delete();
    }

    @Test
    public void testWriteFile() {
        URLResponse[] urlResponse = new URLResponse[1];
        urlResponse[0] = new URLResponse("http://www.google.com");
        urlResponse[0].setSize(1000);
        
        try {
        	WriteFile.writeFile(urlResponse, false);
        }
        catch (Exception e) {
        	fail(e.getMessage());
        }

        File file = new File(Constants.OUTPUT_FILENAME);
        assert file.exists();
    }

    @Test
    public void testReadFile() {
        testWriteFile();
        String fileContents = null;
        
        try {
        	fileContents = ReadFile.readFile(Constants.OUTPUT_FILENAME);
        }
        catch (Exception e) {
        	fail(e.getMessage());
        }
        assertEquals(fileContents, "Size of http://www.google.com: 1000\n");
    }

    void tearDown() {
        setUp();
    }
}
