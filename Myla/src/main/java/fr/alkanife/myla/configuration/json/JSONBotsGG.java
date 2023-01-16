package fr.alkanife.myla.configuration.json;

public class JSONBotsGG {

    private boolean enable;
    private String url, token;

    public JSONBotsGG() {}

    public JSONBotsGG(boolean enable, String url, String token) {
        this.enable = enable;
        this.url = url;
        this.token = token;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
