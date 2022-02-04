package com.MouanjiFranck.biller.model;

import java.util.Objects;

public class File_uploaded {

    private Course course;
    private String senderMail;
    private String senderName;
    private String filePathInFirebase;

    public File_uploaded() {
    }

    public File_uploaded(Course course, String senderMail, String senderName, String filePathInFirebase) {
        this.course = course;
        this.senderMail = senderMail;
        this.senderName = senderName;
        this.filePathInFirebase = filePathInFirebase;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSenderMail() {
        return senderMail;
    }

    public void setSenderMail(String senderMail) {
        this.senderMail = senderMail;
    }



    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }


    public String getFilePathInFirebase() {
        return filePathInFirebase;
    }

    public void setFilePathInFirebase(String filePathInFirebase) {
        this.filePathInFirebase = filePathInFirebase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File_uploaded)) return false;
        File_uploaded that = (File_uploaded) o;
        return course.equals(that.course) && senderMail.equals(that.senderMail) && senderName.equals(that.senderName) && filePathInFirebase.equals(that.filePathInFirebase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, senderMail, senderName, filePathInFirebase);
    }
}
