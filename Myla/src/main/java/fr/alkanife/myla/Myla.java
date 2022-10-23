package fr.alkanife.myla;

import fr.alkanife.myla.commands.Commands;
import fr.alkanife.myla.commands.CommandHandler;
import fr.alkanife.myla.configuration.Configuration;
import fr.alkanife.myla.configuration.ConfigurationLoader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Myla {

    private static boolean DEBUG = false;
    private static String ABSOLUTE_PATH = "";
    private static Logger LOGGER;
    private static Configuration CONFIGURATION;
    private static CommandHandler COMMAND_HANDLER;
    private static HashMap<String, Object> TRANSLATIONS = new HashMap<>();
    private static JDA JDA;
    private static OkHttpClient OK_HTTP_CLIENT;

    public static void main(String[] args) {
        try {
            //
            // Checking advancedDebug argument
            //
            if (args.length > 0)
                if (args[0].equalsIgnoreCase("debug"))
                    DEBUG = true;

            //
            // Moving old 'latest.log' file to the logs/ folder
            //
            ABSOLUTE_PATH = Paths.get("").toAbsolutePath().toString();
            debug("ABSOLUTE_PATH: " + ABSOLUTE_PATH);

            File latestLogs = new File(ABSOLUTE_PATH + "/latest.log");

            if (latestLogs.exists()) {
                debug("latest.log file existing");
                System.out.println("Cleaning logs...");

                File logsFolder = new File(ABSOLUTE_PATH + "/logs");

                if (logsFolder.exists()) {
                    debug("logs/ folder already existing");
                    if (!logsFolder.isDirectory()) {
                        System.out.println(ABSOLUTE_PATH + "/logs is not a directory");
                        return;
                    }
                } else {
                    System.out.println("No logs/ directory found, creating one");
                    logsFolder.mkdir();
                }

                String date = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
                String newPath = ABSOLUTE_PATH + "/logs/before-" + date + ".log";

                debug("Moving latest.log file to " + newPath);
                Files.move(latestLogs.toPath(), Paths.get(newPath));
            } else {
                debug("No latest.log file");
            }

            debug("Creating logger");
            LOGGER = LoggerFactory.getLogger(Myla.class);

            //
            // Initializing configuration
            //
            debug("Reading configuration");
            ConfigurationLoader configurationLoader = new ConfigurationLoader(false);

            if (configurationLoader.getConfiguration() == null)
                return;

            CONFIGURATION = configurationLoader.getConfiguration();

            if (configurationLoader.getConfiguration().isStats())
                getLogger().info("Stats enabled");

            //
            // Initializing commands
            //
            debug("Setting up commands");

            COMMAND_HANDLER = new CommandHandler();
            getCommandHandler().registerCommands(new Commands());

            getLogger().info(COMMAND_HANDLER.getCommands().size() + " commands ready");

            //
            // Initializing translations
            //
            debug("Reading translations");

            TranslationsLoader translationsLoader = new TranslationsLoader(false);

            if (translationsLoader.getTranslations() == null)
                return;

            TRANSLATIONS = translationsLoader.getTranslations();

            // OKHTTP
            debug("Creating OkHttp client");
            OK_HTTP_CLIENT = new OkHttpClient();

            //COUNT
            debug("Getting count");
            if (Gifs.count()) {
                getLogger().info(Gifs.getTotalCount() + " gifs available");
            } else {
                System.exit(0);
                return;
            }

            //
            // Building JDA
            //
            getLogger().info("Building JDA...");

            JDABuilder jdaBuilder = JDABuilder.createDefault(getConfig().getToken());
            jdaBuilder.setRawEventsEnabled(true);
            jdaBuilder.addEventListeners(new Events());

            getLogger().info("Starting JDA");
            JDA = jdaBuilder.build();
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public static boolean isDebugging() {
        return DEBUG;
    }

    public static void setDebugging(boolean debug) {
        DEBUG = debug;
    }

    public static String absolutePath() {
        return ABSOLUTE_PATH;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static Configuration getConfig() {
        return CONFIGURATION;
    }

    public static void setConfig(Configuration CONFIGURATION) {
        Myla.CONFIGURATION = CONFIGURATION;
    }

    public static CommandHandler getCommandHandler() {
        return COMMAND_HANDLER;
    }

    public static HashMap<String, Object> getTranslations() {
        return TRANSLATIONS;
    }

    public static void setTranslations(HashMap<String, Object> TRANSLATIONS) {
        Myla.TRANSLATIONS = TRANSLATIONS;
    }

    public static JDA getJDA() {
        return JDA;
    }

    public static OkHttpClient getOkHttpClient() {
        return OK_HTTP_CLIENT;
    }

    public static void debug(String s) {
        if (DEBUG)
            if (getLogger() == null)
                System.out.println("(debug) " + s);
            else
                getLogger().info("(debug) " + s);
    }

    public static String t(String key, String... values) {
        if (TRANSLATIONS.containsKey(key)) {
            MessageFormat messageFormat = new MessageFormat(String.valueOf(TRANSLATIONS.get(key)));
            return messageFormat.format(values);
        } else return "{" + key + "}";
    }
}
