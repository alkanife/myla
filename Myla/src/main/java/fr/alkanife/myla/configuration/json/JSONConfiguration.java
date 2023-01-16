package fr.alkanife.myla.configuration.json;

public class JSONConfiguration {

    private String discord_token, admin_id, database_url;
    private JSONStats stats;

    public JSONConfiguration() {}

    public JSONConfiguration(String discord_token, String admin_id, String database_url, JSONStats stats) {
        this.discord_token = discord_token;
        this.admin_id = admin_id;
        this.database_url = database_url;
        this.stats = stats;
    }

    public String getDiscord_token() {
        return discord_token;
    }

    public void setDiscord_token(String discord_token) {
        this.discord_token = discord_token;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getDatabase_url() {
        return database_url;
    }

    public void setDatabase_url(String database_url) {
        this.database_url = database_url;
    }

    public JSONStats getStats() {
        return stats;
    }

    public void setStats(JSONStats stats) {
        this.stats = stats;
    }
}
