package fr.alkanife.myla;

import fr.alkanife.botcommons.Lang;
import fr.alkanife.botcommons.Utils;
import fr.alkanife.botcommons.YMLReader;
import fr.alkanife.myla.commands.Handler;
import fr.alkanife.myla.commands.Commands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Myla {

    private static String version = "1.2.3";

    private static Logger logger;

    private static HashMap<String, Object> configurationValues;

    private static String creatorID;
    private static boolean statistics;
    private static String botsggToken;
    private static String topggToken;

    private static OkHttpClient okHttpClient;
    private static Handler handler;
    private static JDA jda;

    public static void main(String[] args) {
        Utils.clearTerminal();

        logger = LoggerFactory.getLogger(Myla.class);
        handler = new Handler();
        okHttpClient = new OkHttpClient();

        logger.info("––––––––––––––––––––––––––––––––––––––––––––");
        logger.info("Starting Myla " + version);

        try {
            configurationValues = YMLReader.read("configuration");

            if (configurationValues == null) {
                logger.error("Configuration file not found!");
                return;
            } else
                logger.info("Reading configuration.yml file");


            Object token = configurationValues.get("token");

            if (token == null) {
                logger.error("Incomplete configuration: missing token value");
                return;
            }


            Object cID = configurationValues.get("creator-id");

            if (cID == null)
                logger.warn("Creator ID not found, admin commands disabled");
            else
                creatorID = String.valueOf(cID);


            Object stats = configurationValues.get("statistics");

            if (stats == null)
                logger.warn("Statistics boolean not found, statistics are disabled");
            else {
                statistics = Boolean.parseBoolean(String.valueOf(stats));

                if (statistics) {
                    Object botsToken = configurationValues.get("botsgg-token");
                    Object topToken = configurationValues.get("topgg-token");

                    if (botsToken == null || topToken == null) {
                        logger.error("Incomplete configuration: missing botsgg-token or topgg-token value");
                        System.exit(0);
                        return;
                    } else {
                        botsggToken = String.valueOf(botsToken);
                        topggToken = String.valueOf(topToken);
                    }
                } else {
                    logger.info("Statistics disabled by configuration");
                }
            }


            Myla.getLogger().info("Loading translations");
            Lang.load();
            Myla.getLogger().info(Lang.getTranslations().size() + " loaded translations");


            Myla.getLogger().info("Setting up commands");
            handler.registerCommands(new Commands());
            Myla.getLogger().info(handler.getCommands().size() + " loaded commands");


            Myla.getLogger().info("Getting count...");
            if (Gifs.count()) {
                Myla.getLogger().info(Gifs.getTotalCount() + " gifs available");
            } else {
                System.exit(0);
                return;
            }

            logger.info("Ready to connect!");
            logger.info("Building JDA");
            JDABuilder jdaBuilder = JDABuilder.createDefault(String.valueOf(token));
            jdaBuilder.setRawEventsEnabled(true);
            jdaBuilder.setStatus(OnlineStatus.ONLINE);
            jdaBuilder.addEventListeners(new Events());
            jda = jdaBuilder.build();

        } catch (Exception exception) {
            logger.error("Failed to start", exception);
        }
    }

    public static Handler getHandler() {
        return handler;
    }

    public static String getVersion() {
        return version;
    }

    public static HashMap<String, Object> getConfigurationValues() {
        return configurationValues;
    }

    public static String getCreatorID() {
        return creatorID;
    }

    public static boolean statistics() {
        return statistics;
    }

    public static String getBotsggToken() {
        return botsggToken;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static JDA getJda() {
        return jda;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static String getTopggToken() {
        return topggToken;
    }
}
