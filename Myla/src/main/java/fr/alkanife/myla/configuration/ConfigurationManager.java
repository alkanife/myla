package fr.alkanife.myla.configuration;

import com.google.gson.Gson;
import fr.alkanife.myla.Myla;
import fr.alkanife.myla.configuration.json.JSONBotsGG;
import fr.alkanife.myla.configuration.json.JSONConfiguration;
import fr.alkanife.myla.configuration.json.JSONStats;
import fr.alkanife.myla.configuration.json.JSONTopGG;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigurationManager {

    private JSONConfiguration configuration = null;

    public ConfigurationManager()  {}

    public boolean load() throws IOException {
        Myla.getLogger().info("Reading configuration...");

        File configurationFile = new File("configuration.json");

        if (!configurationFile.exists()) {
            Myla.getLogger().error("Configuration file not found");
            return false;
        }

        String configurationRaw = Files.readString(configurationFile.toPath());

        Gson gson = new Gson();
        configuration = gson.fromJson(configurationRaw, JSONConfiguration.class);
        return true;
    }

    /* Returning false means the bot can't start */
    public boolean parse() {
        Myla.getLogger().info("Parsing configuration...");

        if (configuration == null) {
            Myla.getLogger().error("Cannot parse: configuration is null!");
            return false;
        }

        if (Myla.isNull(configuration.getDiscord_token())) {
            Myla.cannotContinue("discord_token");
            return false;
        }

        if (Myla.isNull(configuration.getDatabase_url())) {
            Myla.cannotContinue("database_url");
            return false;
        }

        Myla.getLogger().info("Using " + configuration.getDatabase_url());

        if (Myla.isNull(configuration.getAdmin_id()))
            configuration.setAdmin_id(null);


        if (configuration.getStats() == null)
            configuration.setStats(new JSONStats(new JSONBotsGG(false, null, null), new JSONTopGG(false, null)));

        JSONStats stats = configuration.getStats();

        // Bots gg
        if (stats.getBots_gg() == null)
            stats.setBots_gg(new JSONBotsGG(false, null, null));

        if (stats.getBots_gg().isEnable()) {
            if (Myla.isNull(configuration.getStats().getBots_gg().getToken()) || Myla.isNull(configuration.getStats().getBots_gg().getUrl())) {
                disable("bots.gg", "url/token");
                stats.getBots_gg().setEnable(false);
            } else {
                Myla.getLogger().info("discord.bots.gg stats enabled");
            }
        }

        // Top gg
        if (stats.getTop_gg() == null)
            stats.setTop_gg(new JSONTopGG(false, null));

        if (stats.getTop_gg().isEnable()) {
            if (Myla.isNull(stats.getTop_gg().getToken())) {
                disable("top.gg", "token");
                stats.getTop_gg().setEnable(false);
            } else {
                Myla.getLogger().info("top.gg stats enabled");
            }
        }

        return true;
    }

    public JSONConfiguration getConfiguration() {
        return configuration;
    }

    private void disable(String s, String s1) {
        Myla.getLogger().warn("Disabling " + s + " stats because there is no " + s1);
    }
}
