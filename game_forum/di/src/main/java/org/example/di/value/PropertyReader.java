package org.example.di.value;

import org.example.di.ApplicationConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PropertyReader {

    public final String getValue(String paramName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ApplicationConstants.BASE_PROPERTIES_LOCATION))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] nameAndValue = line.split("=");
                if (nameAndValue[0].equals(paramName)) {
                    return nameAndValue[1];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("no value");
    }
}
