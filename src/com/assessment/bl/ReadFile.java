package com.assessment.bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFile {

    public static String readFile(String fileName) throws Exception {
        String result = null;

        File f = new File(fileName);
        if(!f.exists()) {
            throw new Exception("File not found: " + fileName);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = br.readLine();
            }
            result = stringBuilder.toString();
        }
        catch (Exception e) {
            throw new Exception("Error reading file" + e);
        }

        return result;
    }
}
