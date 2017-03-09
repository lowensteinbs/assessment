package com.assessment.dc;

import static org.junit.Assert.*;
import com.assessment.bl.URLSizeChecker;
import com.assessment.dc.URLResponse;
import org.junit.Test;

public class URLResponseTest {

	@Test
    public void testLength() {
		URLResponse urlResponse = null;
		try {
			urlResponse = URLSizeChecker.execute("http://www.yahoo.com");
		}
		catch (Exception e) {
			fail(e.getMessage());
		}

        assert urlResponse.getSize() > 0;
    }

}
