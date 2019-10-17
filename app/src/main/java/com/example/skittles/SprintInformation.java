package com.example.skittles;

public class SprintInformation {
    private String startDate;
    private String endDate;
    private String sprintName;
    private String sprintReview;
    private String sprintObjective;
    private String teamName;

    public SprintInformation(){


    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSprintName() {
        return sprintName;
    }

    public String getSprintReview() {
        return sprintReview;
    }

    public String getSprintObjective() {
        return sprintObjective;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public void setSprintReview(String sprintReview) {
        this.sprintReview = sprintReview;
    }

    public void setSprintObjective(String sprintObjective) {
        this.sprintObjective = sprintObjective;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
