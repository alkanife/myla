package fr.alkanife.myla;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.discordbots.api.client.DiscordBotListAPI;

public class Stats {

    public static void update() {
        if (!Myla.getConfig().isStats())
            return;

        Myla.debug("Updating stats");

        try {
            DiscordBotListAPI api = new DiscordBotListAPI.Builder().token(Myla.getConfig().getTopgg_token()).botId(Myla.getJDA().getSelfUser().getId()).build();

            api.setStats(Myla.getJDA().getGuilds().size());
        } catch (Exception exception) {
            Myla.getLogger().error("Failed to send top.gg stats", exception);
        }

        try {
            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json"), "{\"guildCount\":" + Myla.getJDA().getGuilds().size() + "}");

            Request request = new Request.Builder()
                    .url(Myla.getConfig().getBotsgg_url())
                    .addHeader("Authorization", Myla.getConfig().getBotsgg_token())
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

}
