package com.atypon.lit.domain.model;

import java.util.Objects;

public class JournalEntity {
    private int id;
    private int adminId;
    private String jcode;
    private String publisherName;
    private String source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getJcode() {
        return jcode;
    }

    public void setJcode(String jcode) {
        this.jcode = jcode;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalEntity that = (JournalEntity) o;
        return id == that.id &&
                Objects.equals(jcode, that.jcode) &&
                Objects.equals(publisherName, that.publisherName) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jcode, publisherName, source);
    }
}
