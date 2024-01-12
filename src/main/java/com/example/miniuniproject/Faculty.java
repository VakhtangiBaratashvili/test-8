package com.example.miniuniproject;

public enum Faculty {
    IT("IT"),
    MANAGEMENT("Management"),
    MATH("Math"),
    ARCHITECTURE("Architecture"),
    PHYSICS("Physics"),
    ART("Art"),
    BIOLOGY("Biology"),
    BUSINESS("Business"),
    HISTORY("History"),
    LAW("Law");

    private final String facultyName;

    Faculty(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyName() {
        return facultyName;
    }
}
