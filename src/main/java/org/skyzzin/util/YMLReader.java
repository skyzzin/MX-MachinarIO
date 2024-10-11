package org.skyzzin.util;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;

public class YMLReader {

    public static Map<String, GeneratorConfig> loadGenerators(String fileName) {
        Yaml yaml = new Yaml();
        Map<String, GeneratorConfig> generators = new HashMap<>();

        try (InputStream inputStream = YMLReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Arquivo YML não encontrado: " + fileName);
            }

            Map<String, Object> data = yaml.load(inputStream);
            Map<String, Map<String, Object>> generatorsData = (Map<String, Map<String, Object>>) data.get("Generators");

            for (Map.Entry<String, Map<String, Object>> entry : generatorsData.entrySet()) {
                String key = entry.getKey();
                Map<String, Object> values = entry.getValue();

                GeneratorConfig config = new GeneratorConfig();
                config.setType((String) values.get("type"));
                config.setTitle((String) values.get("title"));
                config.setTime((Integer) values.get("time"));

                generators.put(key, config);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return generators;
    }
}
