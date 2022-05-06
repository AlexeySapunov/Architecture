package ru.gb.patterns.builder;

import java.nio.file.Path;

public class MultimediaDto {

    private String contentType;

    private Path path;

    private byte[] data;

    public MultimediaDto(Multimedia.MultimediaBuilder contentType, Path path) {
        this.contentType = String.valueOf(contentType);
        this.path = path;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
