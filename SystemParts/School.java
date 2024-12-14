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
        return availablePrograms.contains(program);
    }
}
