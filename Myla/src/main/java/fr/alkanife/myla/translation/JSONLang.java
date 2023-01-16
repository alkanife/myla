package fr.alkanife.myla.translation;

public class JSONLang {

    private String error, info;

    public JSONLang() {}

    public JSONLang(String error, String info) {
        this.error = error;
        this.info = info;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
