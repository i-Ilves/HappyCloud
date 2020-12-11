package Backend;

import express.Express;
import express.middleware.Middleware;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Express app = new Express();
        Database db = new Database();

        app.get("/rest/notes/:id", (req, res) -> {
            int url_id = Integer.parseInt(req.getParam("id"));

            List<Note> notes = db.getNotes(url_id);
            res.json(notes);

        });

        app.get("/rest/notes/", (req, res) -> {
            List<Note> notes = db.adminGetNotes();
            res.json(notes);
        });

        app.get("/rest/images/", (req, res) -> {
            List<Image> images = db.adminGetImages();
            res.json(images);
        });

        app.get("/rest/files/", (req, res) -> {
            List<File> files = db.adminGetFiles();
            res.json(files);
        });

        app.get("/rest/images/:id", (req, res) -> {
            int url_id = Integer.parseInt(req.getParam("id"));

            List<Image> images = db.getImages(url_id);

            res.json(images);

        });

        app.get("/rest/files/:id", (req, res) -> {
            int url_id = Integer.parseInt(req.getParam("id"));

            List<File> files = db.getFiles(url_id);

            res.json(files);

        });

        try {
            app.use(Middleware.statics(Paths.get("src/Frontend").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        app.listen(3000); // defaults to port 80
        System.out.println("Server started on port 3000");


    }
}
