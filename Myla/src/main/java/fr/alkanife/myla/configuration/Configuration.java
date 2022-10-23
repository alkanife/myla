package fr.alkanife.myla.configuration;

public class Configuration {

    private String token, admin_id, botsgg_url, botsgg_token, topgg_token, base_imageurl;
    private boolean stats;

    public Configuration() {}

    public Configuration(String token, String admin_id, String botsgg_url, String botsgg_token, String topgg_token, String base_imageurl, boolean stats) {
        this.token = token;
        this.admin_id = admin_id;
        this.botsgg_url = botsgg_url;
        this.botsgg_token = botsgg_token;
        this.topgg_token = topgg_token;
        this.base_imageurl = base_imageurl;
        this.stats = stats;
    }

    public String getToken() {
        return token;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getBotsgg_url() {
        return botsgg_url;
    }

    public String getBotsgg_token() {
        return botsgg_token;
    }

    public String getTopgg_token() {
        return topgg_token;
    }

    public String getBase_imageurl() {
        return base_imageurl;
    }

    public boolean isStats() {
        return stats;
    }
}
