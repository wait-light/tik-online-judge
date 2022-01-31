package top.adxd.tikonlinejudge.executor.api.entity;

public class LanguageRunningStatus {
    public String languageType;
    public boolean running;
    public boolean avaluable;
    public String id;

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isAvaluable() {
        return avaluable;
    }

    public void setAvaluable(boolean avaluable) {
        this.avaluable = avaluable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
