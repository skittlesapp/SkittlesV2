package com.example.skittles;

public class Project {
    private String projectName;
    private String projectDescr;
    private Integer maxNoteams;

    public Project(){


    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescr() {
        return projectDescr;
    }

    public Integer getMaxNoteams() {
        return maxNoteams;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectDescr(String projectDescr) {
        this.projectDescr = projectDescr;
    }

    public void setMaxNoteams(Integer maxNoteams) {
        this.maxNoteams = maxNoteams;
    }
}
