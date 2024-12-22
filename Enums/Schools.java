package Enums;

import java.util.List;

public enum Schools {	
    SITE("School of Information Technology and Engineering"),
    KMA("Kazakhstan Maritime Academy"),
    SG("School of Geology"),    
    SAM("School of Chemical Engineering");
	private Schools schoolName;
    private List<EducationalProgram> availablePrograms;

    

    private final String fullName;

    Schools(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isProgramAvailable(EducationalProgram program) {
        boolean isAvailable = availablePrograms.contains(program);

        if (isAvailable) {
            switch (schoolName) {
                case SITE:
                    return program == EducationalProgram.IS ||
                           program == EducationalProgram.AC ||
                           program == EducationalProgram.CSS ||
                           program == EducationalProgram.ITM ||
                           program == EducationalProgram.RM;
                case SG:
                    return program == EducationalProgram.GESMD ||
                           program == EducationalProgram.GOG;
                case KMA:
                    return program == EducationalProgram.NS ||
                           program == EducationalProgram.ME;
                case SAM:
                    return program == EducationalProgram.CTOS;
                default:
                    return false;
            }
        }
        return false;
    }
}
