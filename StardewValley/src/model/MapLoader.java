package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapLoader {
    public static Map<String, List<Point>> loadMap(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, List<Map<String, Integer>>> rawMap = mapper.readValue(new File(filePath),
                    new TypeReference<>() {});

            Map<String, List<Point>> finalMap = new HashMap<>();
            for (Map.Entry<String, List<Map<String, Integer>>> entry : rawMap.entrySet())
            {
                List<Map<String, Integer>> rawPoints = entry.getValue();
                List<Point> points = rawPoints.stream()
                        .map(m -> new Point(m.get("y"), m.get("x")))
                        .toList();
                finalMap.put(entry.getKey(), points);
            }
            return finalMap;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
