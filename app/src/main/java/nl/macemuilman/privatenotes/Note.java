package nl.macemuilman.privatenotes;

public class Note {
    protected String title = "";
    protected String content = "";

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}
