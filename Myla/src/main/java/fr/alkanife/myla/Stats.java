package fr.alkanife.myla;

import fr.alkanife.myla.configuration.json.JSONBotsGG;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.discordbots.api.client.DiscordBotListAPI;

public class Stats {

    public static void update() {
        updateBotsGG();
        updateTopGG();
    }

    public static void updateBotsGG() {
        if (!Myla.getConfig().getStats().getBots_gg().isEnable())
            return;

        Myla.debug("Updating discord.bots.gg");

        try {
            JSONBotsGG jsonBotsGG = Myla.getConfig().getStats().getBots_gg();
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), "{\"guildCount\":" + Myla.getJDA().getGuilds().size() + "}");

            Request request = new Request.Builder()
                    .url(jsonBotsGG.getUrl())
                    .addHeader("Authorization", jsonBotsGG.getToken())
                    .post(body)
                    .build();

            Response response = Myla.getOkHttpClient().newCall(request).execute();

            if (response.body() == null)
                Myla.getLogger().warn("discord.bots.gg replied with no body");

            response.close();
        } catch (Exception exception) {
            Myla.getLogger().error("Failed to send discord.bots.gg stats", exception);
        }
    }

    public static void updateTopGG() {
        if (!Myla.getConfig().getStats().getTop_gg().isEnable())
            return;

        Myla.debug("Updating top.gg");

        try {
            DiscordBotListAPI api = new DiscordBotListAPI.Builder().token(Myla.getConfig().getStats().getTop_gg().getToken()).botId(Myla.getJDA().getSelfUser().getId()).build();

            api.setStats(Myla.getJDA().getGuilds().size());
        } catch (Exception exception) {
            Myla.getLogger().error("Failed to send top.gg stats", exception);
        }
    }

}
