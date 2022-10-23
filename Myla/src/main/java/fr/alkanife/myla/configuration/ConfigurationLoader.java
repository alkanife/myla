package fr.alkanife.myla.configuration;

import com.google.gson.Gson;
import fr.alkanife.myla.Myla;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigurationLoader {

    private Configuration configuration = null;

    public ConfigurationLoader(boolean reload) throws IOException {
        Myla.getLogger().info((reload ? "(RELOAD) " : "") + "Reading " + Myla.absolutePath() + "/configuration.json");

        File configurationFile = new File(Myla.absolutePath() + "/configuration.json");

        if (!configurationFile.exists()) {
            Myla.getLogger().error("Configuration file not found");
            return;
        }

        // Reading configuration file
        String configurationRaw = Files.readString(configurationFile.toPath());
        //Alkabot.debug("RAW CONFIGURATION: " + configurationRaw);

        Gson gson = new Gson();
        configuration = gson.fromJson(configurationRaw, Configuration.class);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
