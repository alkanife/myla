package fr.alkanife.myla;

import fr.alkanife.myla.Myla;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class TranslationsLoader {

    private HashMap<String, Object> translations = new HashMap<>();

    public TranslationsLoader(boolean reload) throws FileNotFoundException {
        Myla.getLogger().info((reload ? "(RELOAD) " : "") + "Reading " + Myla.absolutePath() + "/lang.yml");

        File langFile = new File(Myla.absolutePath() + "/lang.yml");

        if (!langFile.exists()) {
            Myla.getLogger().warn("Translation file not found");
            return;
        }

        InputStream inputStream = new FileInputStream(langFile);
        Yaml yaml = new Yaml();
        translations = yaml.load(inputStream);

        Myla.getLogger().info("Loaded " + translations.size() + " translations");
    }

    public HashMap<String, Object> getTranslations() {
        return translations;
    }

}
