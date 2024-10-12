package org.skyzzin.util;

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
import org.bukkit.plugin.java.JavaPlugin;

public class YMLReader {

    public static Map<String, GeneratorConfig> loadGenerators(JavaPlugin plugin, String fileName) {
        Yaml yaml = new Yaml();
        Map<String, GeneratorConfig> generators = new HashMap<>();

        // Obter o arquivo a partir da pasta de dados do plugin
        File file = new File(plugin.getDataFolder(), fileName);

        try (InputStream inputStream = new FileInputStream(file)) {
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
