package Backend;

import java.util.Date;

public class Image {
    private double size;
    private int url_id;
    private Date date;
    private int id;
    private String link;
    private String title;

    public Image() {
    }

    public Image(double size, int url_id, Date date, int id, String link, String title) {
        this.size = size;
        this.url_id = url_id;
        this.date = date;
        this.id = id;
        this.link = link;
        this.title = title;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getUrl_id() {
        return url_id;
    }

    public void setUrl_id(int url_id) {
        this.url_id = url_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Image{" +
                "size=" + size +
                ", url_id=" + url_id +
                ", date=" + date +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
