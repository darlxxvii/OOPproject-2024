package Activities;

public class SportsActivity implements ExtracurricularActivity {
    @Override
    public void addActivity(String activity) {
        System.out.println("Added Sports activity: " + activity);
    }
}
