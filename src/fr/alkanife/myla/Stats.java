package fr.alkanife.myla;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.discordbots.api.client.DiscordBotListAPI;

public class Stats {

    public static void update() {
        if (!Myla.statistics())
            return;

        try {
            DiscordBotListAPI api = new DiscordBotListAPI.Builder().token(Myla.getTopggToken()).botId(Myla.getJda().getSelfUser().getId()).build();

            api.setStats(Myla.getJda().getGuilds().size());
        } catch (Exception exception) {
            Myla.getLogger().error("Failed to send top.gg stats", exception);
        }

        try {
            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json"), "{\"guildCount\":" + Myla.getJda().getGuilds().size() + "}");

            Request request = new Request.Builder()
                    .url("https://discord.bots.gg/api/v1/bots/658109944859459604/stats")
                    .addHeader("Authorization", Myla.getBotsggToken())
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
