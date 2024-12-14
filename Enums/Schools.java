package Enums;

public enum Schools {
    SITE("School of Information Technology and Engineering"),
    KMA("Kazakhstan Maritime Academy"),
    SG("School of Geology"),    
    SAM("School of Chemical Engineering");
    

    private final String fullName;

    Schools(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
