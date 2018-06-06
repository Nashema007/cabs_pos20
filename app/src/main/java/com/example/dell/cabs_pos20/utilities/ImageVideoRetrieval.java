package com.example.dell.cabs_pos20.utilities;

public class ImageVideoRetrieval {

    private int filename;
    private int description;
    private String errors;


    public ImageVideoRetrieval( int description, int filename, String errors) {
        this.description = description;
        this.filename = filename;
        this.errors = errors;
    }


    public ImageVideoRetrieval() {
    }

    public int getFilename() {
        return filename;
    }

    public int getDescription() {
        return description;
    }

    public String getErrors() {
        return errors;
    }

}
