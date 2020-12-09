package Backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import express.utils.Utils;

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

}
