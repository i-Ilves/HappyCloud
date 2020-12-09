package Backend;

import express.Express;
import express.middleware.Middleware;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Express app = new Express();
        Database db = new Database();

        // req = Request, res = Response
        app.get("/hello-world", (req, res) -> {
            res.send("Hello World");
        });

        try {
            app.use(Middleware.statics(Paths.get("src/Frontend").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        app.listen(3000); // defaults to port 80
        System.out.println("Server started on port 3000");

        int current_url_id = 2;
        System.out.println(db.getNotes(current_url_id));
        System.out.println(db.getImages(current_url_id));
        System.out.println(db.getFiles(current_url_id));
    }
}
