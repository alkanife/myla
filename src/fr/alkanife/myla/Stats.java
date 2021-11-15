package fr.alkanife.myla;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Stats {

    public static void update() {
        if (!Myla.statistics())
            return;

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
