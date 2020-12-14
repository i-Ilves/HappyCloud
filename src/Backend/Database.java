package Backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import express.utils.Utils;
import org.apache.commons.fileupload.FileItem;

import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class Database {
    private Connection conn;

    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:HappyCloud.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Url> getURLids() {
        List<Url> urlids = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM url");
            ResultSet rs = stmt.executeQuery();

            Url[] urlidsFromRS = (Url[]) Utils.readResultSetToObject(rs, Url[].class);
            urlids = List.of(urlidsFromRS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return urlids;
    }

    public List<File> getFiles(int url_id) {
        List<File> files = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM file WHERE url_id =?");
            stmt.setInt(1, url_id);
            ResultSet rs = stmt.executeQuery();

            File[] filesFromRS = (File[]) Utils.readResultSetToObject(rs, File[].class);
            files = List.of(filesFromRS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return files;
    }

    public List<Image> getImages(int url_id) {
        List<Image> images = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM image WHERE url_id =?");
            stmt.setInt(1, url_id);
            ResultSet rs = stmt.executeQuery();

            Image[] imagesFromRS = (Image[]) Utils.readResultSetToObject(rs, Image[].class);
            images = List.of(imagesFromRS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return images;
    }

    public List<Note> getNotes(int url_id) {
        List<Note> notes = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM note WHERE url_id =?");
            stmt.setInt(1, url_id);
            ResultSet rs = stmt.executeQuery();

            Note[] notesFromRS = (Note[]) Utils.readResultSetToObject(rs, Note[].class);
            notes = List.of(notesFromRS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public List<Note> adminGetNotes() {
        List<Note> notes = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM note");
            ResultSet rs = stmt.executeQuery();

            Note[] notesFromRS = (Note[]) Utils.readResultSetToObject(rs, Note[].class);
            notes = List.of(notesFromRS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public List<Image> adminGetImages() {
        List<Image> images = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM image");
            ResultSet rs = stmt.executeQuery();

            Image[] imagesFromRS = (Image[]) Utils.readResultSetToObject(rs, Image[].class);
            images = List.of(imagesFromRS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return images;
    }

    public List<File> adminGetFiles() {
        List<File> files = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM file");
            ResultSet rs = stmt.executeQuery();

            File[] filesFromRS = (File[]) Utils.readResultSetToObject(rs, File[].class);
            files = List.of(filesFromRS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return files;
    }

    public void createNote(Note note) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO note (text, url_id, date, title, image_url) VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, note.getText());
            stmt.setInt(2, note.getUrl_id());
            stmt.setLong(3, note.getDate());
            stmt.setString(4, note.getTitle());
            stmt.setString(5, note.getImage_url());

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void updateNote(Note note) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE note SET text = ?, url_id = ?, date = ?, title = ? WHERE id = ?");
            stmt.setString(1, note.getText());
            stmt.setInt(2, note.getUrl_id());
            stmt.setLong(3, note.getDate());
            stmt.setString(4, note.getTitle());
            stmt.setInt(5, note.getId());

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public String uploadImage(FileItem image) {

        String imageUrl = "/uploads/" + image.getName();

        try (var os = new FileOutputStream(Paths.get("src/Frontend" + imageUrl).toString())) {
            os.write(image.get());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return imageUrl;
    }
    
    public void deleteNote(Note note) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM note WHERE id = ?");
            stmt.setInt(1, note.getId());

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
