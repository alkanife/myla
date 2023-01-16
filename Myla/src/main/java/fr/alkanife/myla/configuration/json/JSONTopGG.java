package fr.alkanife.myla.configuration.json;

public class JSONTopGG {

    private boolean enable;
    private String token;

    public JSONTopGG() {}

    public JSONTopGG(boolean enable, String token) {
        this.enable = enable;
        this.token = token;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
