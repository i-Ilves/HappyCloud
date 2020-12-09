package Backend;

public class Url {
    private String link;
    private int id;

    public Url() {
    }

    public Url(String link, int id) {
        this.link = link;
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
