package Activities;

public class AcademicActivity implements ExtracurricularActivity {
    @Override
    public void addActivity(String activity) {
        System.out.println("Added Academic activity: " + activity);
    }
}