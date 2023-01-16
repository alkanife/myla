package fr.alkanife.myla;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.Random;

public class Gifs {

    private static int blushCount = 0;
    private static int cookieCount = 0;
    private static int cryCount = 0;
    private static int headpatCount = 0;
    private static int heheCount = 0;
    private static int hiCount = 0;
    private static int hugCount = 0;
    private static int idkCount = 0;
    private static int kissCount = 0;
    private static int laughCount = 0;
    private static int memeCount = 0;
    private static int notlikethisCount = 0;
    private static int partyCount = 0;
    private static int poutCount = 0;
    private static int punchCount = 0;
    private static int slapCount = 0;
    private static int smileCount = 0;
    private static int winkCount = 0;
    private static int prayCount = 0;
    private static int hideCount = 0;

    public static boolean count() {
        try {
            Request request = new Request.Builder().url(Myla.getConfig().getDatabase_url() + "/image_count").build();

            Response response = Myla.getOkHttpClient().newCall(request).execute();
            ResponseBody body = response.body();

            if (body == null)  {
                Myla.getLogger().error("Failed to count: the request returned no body");
                return false;
            }

            String result = body.string();

            JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();

            blushCount = jsonObject.get("blush").getAsInt();
            cookieCount = jsonObject.get("cookie").getAsInt();
            cryCount = jsonObject.get("cry").getAsInt();
            headpatCount = jsonObject.get("headpat").getAsInt();
            heheCount = jsonObject.get("hehe").getAsInt();
            hiCount = jsonObject.get("hi").getAsInt();
            hugCount = jsonObject.get("hug").getAsInt();
            idkCount = jsonObject.get("idk").getAsInt();
            kissCount = jsonObject.get("kiss").getAsInt();
            laughCount = jsonObject.get("laugh").getAsInt();
            memeCount = jsonObject.get("meme").getAsInt();
            notlikethisCount = jsonObject.get("notlikethis").getAsInt();
            partyCount = jsonObject.get("party").getAsInt();
            poutCount = jsonObject.get("pout").getAsInt();
            punchCount = jsonObject.get("punch").getAsInt();
            slapCount = jsonObject.get("slap").getAsInt();
            smileCount = jsonObject.get("smile").getAsInt();
            winkCount = jsonObject.get("wink").getAsInt();
            prayCount = jsonObject.get("pray").getAsInt();
            hideCount = jsonObject.get("hide").getAsInt();

            response.close();
            return true;
        } catch (Exception exception) {
            Myla.getLogger().error("Failed to count", exception);
            return false;
        }
    }

    public static String get(String type, int count) {
        int random = new Random().nextInt(count);

        return Myla.getConfig().getDatabase_url() + "/images/" + type + "/" + random + ".gif";
    }

    public static int getTotalCount() {
        return blushCount + cookieCount + cryCount + headpatCount + heheCount + hiCount + hugCount + idkCount + kissCount
                + laughCount + memeCount + notlikethisCount + partyCount + poutCount + punchCount + slapCount + smileCount + winkCount
                + prayCount + hideCount;
    }

    public static int getBlushCount() {
        return blushCount;
    }

    public static int getCookieCount() {
        return cookieCount;
    }

    public static int getCryCount() {
        return cryCount;
    }

    public static int getHeadpatCount() {
        return headpatCount;
    }

    public static int getHeheCount() {
        return heheCount;
    }

    public static int getHiCount() {
        return hiCount;
    }

    public static int getHugCount() {
        return hugCount;
    }

    public static int getIdkCount() {
        return idkCount;
    }

    public static int getKissCount() {
        return kissCount;
    }

    public static int getLaughCount() {
        return laughCount;
    }

    public static int getMemeCount() {
        return memeCount;
    }

    public static int getNotlikethisCount() {
        return notlikethisCount;
    }

    public static int getPartyCount() {
        return partyCount;
    }

    public static int getPoutCount() {
        return poutCount;
    }

    public static int getPunchCount() {
        return punchCount;
    }

    public static int getSlapCount() {
        return slapCount;
    }

    public static int getSmileCount() {
        return smileCount;
    }

    public static int getWinkCount() {
        return winkCount;
    }

    public static int getPrayCount() {
        return prayCount;
    }

    public static int getHideCount() {
        return hideCount;
    }
}