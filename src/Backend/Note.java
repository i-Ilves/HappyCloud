package Backend;

import java.util.Date;

public class Note {
    private String text;
    private int url_id;
    private long date;
    private int id;
    private String title;
    private String image_url;

    public Note() {
    }

    public Note(String text, int url_id, long date, int id, String title, String image_url) {
        this.text = text;
        this.url_id = url_id;
        this.date = date;
        this.id = id;
        this.title = title;
        this.image_url = image_url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUrl_id() {
        return url_id;
    }

    public void setUrl_id(int url_id) {
        this.url_id = url_id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Note{" +
                "text='" + text + '\'' +
                ", url_id=" + url_id +
                ", date=" + date +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
