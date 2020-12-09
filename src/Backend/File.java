package Backend;

import java.util.Date;

public class File {
    private String title;
    private int url_id;
    private double size;
    private Date date;
    private int id;
    private String link;

    public File() {
    }

    public File(String title, int url_id, double size, Date date, int id, String link) {
        this.title = title;
        this.url_id = url_id;
        this.size = size;
        this.date = date;
        this.id = id;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public int getUrl_id() {
        return url_id;
    }

    public double getSize() {
        return size;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl_id(int url_id) {
        this.url_id = url_id;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "File{" +
                "title='" + title + '\'' +
                ", url_id=" + url_id +
                ", size=" + size +
                ", date=" + date +
                ", id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
