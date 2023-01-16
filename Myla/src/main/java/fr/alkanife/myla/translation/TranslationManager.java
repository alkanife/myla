package fr.alkanife.myla.translation;

import com.google.gson.Gson;
import fr.alkanife.myla.Myla;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TranslationManager {

    private JSONLang lang = null;

    public TranslationManager()  {}

    public boolean load() throws IOException {
        Myla.getLogger().info("Reading translations...");

        File langFile = new File("lang.json");

        if (!langFile.exists()) {
            Myla.getLogger().error("Lang file not found");
            return false;
        }

        String langRaw = Files.readString(langFile.toPath());

        Gson gson = new Gson();
        lang = gson.fromJson(langRaw, JSONLang.class);
        return true;
    }

    /* Returning false means the bot can't start */
    public boolean parse() {
        Myla.getLogger().info("Parsing translations...");

        if (lang == null) {
            Myla.getLogger().error("Cannot parse: lang is null!");
            return false;
        }

        if (Myla.isNull(lang.getInfo())) {
            Myla.cannotContinue("info");
            return false;
        }

        if (Myla.isNull(lang.getError())) {
            Myla.cannotContinue("error");
            return false;
        }

        return true;
    }

    public JSONLang getLang() {
        return lang;
    }
}
