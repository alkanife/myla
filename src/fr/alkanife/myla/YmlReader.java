package fr.alkanife.myla;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class YmlReader {

    public static HashMap<String, Object> read(String fileName) {
        File file = new File(Utils.absolutePath() + "/" + fileName + ".yml");

        if (!file.exists())
            return null;

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException ignored) { }

        Yaml yaml = new Yaml();

        return yaml.load(inputStream);
    }

}
