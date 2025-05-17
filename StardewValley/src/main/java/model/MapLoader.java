package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapLoader {
    public static Map<String, List<Point>> loadMap(String filePath, int[] dimensionsOut) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> raw = mapper.readValue(new File(filePath), new TypeReference<>() {});

            int width = (int) raw.getOrDefault("width", 70);
            int height = (int) raw.getOrDefault("height", 70);

            dimensionsOut[0] = width;
            dimensionsOut[1] = height;

            Map<String, List<Map<String, Integer>>> rawTiles =
                    (Map<String, List<Map<String, Integer>>>) raw.get("tiles");

            Map<String, List<Point>> finalMap = new HashMap<>();
            for (Map.Entry<String, List<Map<String, Integer>>> entry : rawTiles.entrySet()) {
                List<Point> points = entry.getValue().stream()
                        .map(p -> new Point(p.get("x"), p.get("y")))
                        .toList();
                finalMap.put(entry.getKey(), points);
            }

            return finalMap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
