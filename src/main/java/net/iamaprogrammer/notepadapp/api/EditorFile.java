package net.iamaprogrammer.notepadapp.api;

public class EditorFile {
    private String title;
    private String header;
    private String body;
    private String footer;

    public EditorFile(String header, String body, String footer) {
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    public EditorFile(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public EditorFile(String body) {
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
