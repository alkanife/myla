package fr.alkanife.myla.configuration.json;

public class JSONStats {

    private JSONBotsGG bots_gg;
    private JSONTopGG top_gg;

    public JSONStats() {}

    public JSONStats(JSONBotsGG bots_gg, JSONTopGG top_gg) {
        this.bots_gg = bots_gg;
        this.top_gg = top_gg;
    }

    public JSONBotsGG getBots_gg() {
        return bots_gg;
    }

    public void setBots_gg(JSONBotsGG bots_gg) {
        this.bots_gg = bots_gg;
    }

    public JSONTopGG getTop_gg() {
        return top_gg;
    }

    public void setTop_gg(JSONTopGG top_gg) {
        this.top_gg = top_gg;
    }
}
