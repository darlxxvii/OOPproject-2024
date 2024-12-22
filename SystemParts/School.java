package SystemParts;

import java.util.List;

import Enums.EducationalProgram;
import Enums.Schools;

public class School {
    private Schools schoolName;
    private List<EducationalProgram> availablePrograms;

    public School(Schools schoolName, List<EducationalProgram> availablePrograms) {
        this.schoolName = schoolName;
        this.availablePrograms = availablePrograms;
    }

    public Schools getSchoolName() {
        return schoolName;
    }

    public List<EducationalProgram> getAvailablePrograms() {
        return availablePrograms;
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
