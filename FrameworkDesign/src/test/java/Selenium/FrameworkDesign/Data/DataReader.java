package Selenium.FrameworkDesign.Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    //Covert JSON format to HashMap
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //Read Json and convert it to string
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        //Now convert Sting value to hashmap
        //To convert to Hashmap from string we need "Jackson Datbiln"

        //now create a mapper object from a ObjectMapper class
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {});
        return data;
        //Return value type - {{map}, {map1}}
    }
}
