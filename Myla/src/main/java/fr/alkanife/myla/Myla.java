package fr.alkanife.myla;

import fr.alkanife.myla.commands.Commands;
import fr.alkanife.myla.commands.CommandHandler;
import fr.alkanife.myla.configuration.json.JSONConfiguration;
import fr.alkanife.myla.configuration.ConfigurationManager;
import fr.alkanife.myla.translation.JSONLang;
import fr.alkanife.myla.translation.TranslationManager;
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

    public static final String VERSION = "1.4.0";
    public static final String WEBSITE = "https://myla.alkanife.fr";

    public static boolean updateCommands = false;

    private static boolean debug = false;
    private static Logger logger;
    private static ConfigurationManager configurationManager;
    private static TranslationManager translationManager;
    private static CommandHandler commandHandler;
    private static JDA JDA;
    private static OkHttpClient okHttpClient;

    public static void main(String[] args) {
        try {
            // Reading arguments
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("help")) {
                    System.out.println("This is Myla version " + VERSION);
                    System.out.println("Usage:\n" +
                            "  java -jar myla.jar help\n" +
                            "  java -jar myla.jar [debug/prod] [commands]");
                    System.out.println("Default args: prod");
                    System.out.println("For more details go to " + WEBSITE);
                    return;
                }

                if (args[0].equalsIgnoreCase("debug"))
                    debug = true;

                if (args.length >= 2) {
                    if (args[1].equalsIgnoreCase("commands"))
                        updateCommands = true;
                }
            }

            // Splash text
            System.out.println("  __  __       _");
            System.out.println(" |  \\/  |     | |");
            System.out.println(" | \\  / |_   _| | __ _");
            System.out.println(" | |\\/| | | | | |/ _` |");
            System.out.println(" | |  | | |_| | | (_| |");
            System.out.println(" |_|  |_|\\__, |_|\\__,_|");
            System.out.println("          __/ |     ______");
            System.out.println("         |___/     |______|");
            System.out.println();
            System.out.println(" " + WEBSITE);
            System.out.println(" Version " + VERSION);
            System.out.println();

            if (isDevBuild())
                System.out.println("***                                                                                ***\n" +
                        "*** THIS VERSION IS A DEV BUILD AND SHOULD NOT BE USED IN A PRODUCTION ENVIRONMENT ***\n" +
                        "***                                                                                ***");

            Thread.sleep(2000);

            // Moving old 'latest.log' file
            String absolutePath = Paths.get("").toAbsolutePath().toString();
            debug("Absolute path: " + absolutePath);

            File latestLogs = new File(absolutePath + "/latest.log");

            if (latestLogs.exists()) {
                debug("latest.log file existing");
                System.out.println("Cleaning logs...");

                File logsFolder = new File(absolutePath + "/logs");

                if (logsFolder.exists()) {
                    debug("logs/ folder already existing");
                    if (!logsFolder.isDirectory()) {
                        System.out.println(absolutePath + "/logs is not a directory");
                        return;
                    }
                } else {
                    System.out.println("No logs/ directory found, creating one");
                    logsFolder.mkdir();
                }

                String date = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
                String newPath = absolutePath + "/logs/before-" + date + ".log";

                debug("Moving latest.log file to " + newPath);
                Files.move(latestLogs.toPath(), Paths.get(newPath));
            } else {
                debug("No latest.log file");
            }

            debug("Creating logger");
            logger = LoggerFactory.getLogger(Myla.class);
            logger.info("Myla v" + VERSION);

            //
            // Initializing configuration
            //
            debug("Initializing configuration");
            configurationManager = new ConfigurationManager();

            if (!configurationManager.load())
                return;

            if (!configurationManager.parse())
                return;

            // Initializing translations
            debug("Reading translations");
            translationManager = new TranslationManager();

            if (!translationManager.load())
                return;

            if (!translationManager.parse())
                return;

            // Initializing commands
            debug("Setting up commands");

            commandHandler = new CommandHandler();
            getCommandHandler().registerCommands(new Commands());

            getLogger().info(commandHandler.getCommands().size() + " commands ready");

            // OKHTTP
            debug("Creating OkHttp client");
            okHttpClient = new OkHttpClient();

            //COUNT
            debug("Getting count");
            if (Gifs.count())
                getLogger().info(Gifs.getTotalCount() + " gifs available");
            else
                return;

            // Building JDA
            getLogger().info("Building JDA...");
            JDABuilder jdaBuilder = JDABuilder.createDefault(getConfig().getDiscord_token());
            jdaBuilder.setRawEventsEnabled(true);
            jdaBuilder.addEventListeners(new Events());
            getLogger().info("Starting");
            JDA = jdaBuilder.build();
        } catch (Exception exception) {
            getLogger().error("Failed to start");
            exception.printStackTrace();
        }
    }

    public static boolean isDebugging() {
        return debug;
    }

    public static void setDebugging(boolean debug) {
        Myla.debug = debug;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static JSONConfiguration getConfig() {
        return configurationManager.getConfiguration();
    }

    public static ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public static void setConfigurationManager(ConfigurationManager configurationManager) {
        Myla.configurationManager = configurationManager;
    }

    public static TranslationManager getTranslationManager() {
        return translationManager;
    }

    public static void setTranslationManager(TranslationManager translationManager) {
        Myla.translationManager = translationManager;
    }

    public static JSONLang getLang() {
        return translationManager.getLang();
    }

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static JDA getJDA() {
        return JDA;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static void debug(String s) {
        if (debug)
            if (getLogger() == null)
                System.out.println("(debug) " + s);
            else
                getLogger().info("(debug) " + s);
    }

    public static boolean isDevBuild() {
        String version = VERSION.toLowerCase();

        return version.contains("beta")
                || version.contains("dev")
                || version.contains("snapshot")
                || version.contains("alpha");
    }

    public static void cannotContinue(String s) {
        Myla.getLogger().error("Cannot continue: invalid '" + s + "'");
    }

    public static boolean isNull(String s) {
        if (s == null)
            return true;

        return s.equalsIgnoreCase("");
    }
}
