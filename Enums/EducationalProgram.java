package Enums;

public enum EducationalProgram {
    IS("Information Systems"),
    AC("Automation and Control"),
    CSS("Computer Systems and Software"),
    ITM("IT Management"),
    RM("Robotization and Mechatronics"),//SITE
	
	GESMD("Geology and exploration of solid mineral deposits"),
	GOG("Geology of oil and gas"),//SG
	
	NS("Nautical sciences"),
	ME("Marine engineering"),//KMA

	CTOS("Chemical Technology of Organic Substances"); //SCE
	
	
	
    private final String fullName;

    EducationalProgram(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
