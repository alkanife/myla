package fr.alkanife.myla;

import java.text.MessageFormat;
import java.util.HashMap;

public class Lang {

    private static HashMap<String, Object> translations = new HashMap<>();

    public static boolean load() {
        try {
            HashMap<String, Object> data = YmlReader.read("lang");

            if (data == null) {
                Myla.getLogger().error("Data is null");
                return false;
            }

            translations = data;
        } catch (Exception exception) {
            Myla.getLogger().error("Error while reading YML File", exception);
            return false;
        }

        return true;
    }

    public static String t(String key, String... values) {
        if (translations.containsKey(key)) {
            MessageFormat messageFormat = new MessageFormat(String.valueOf(translations.get(key)));
            return messageFormat.format(values);
        } else return "{Missing translation : " + key + "}";
    }

    public static HashMap<String, Object> getTranslations() {
        return translations;
    }
}
