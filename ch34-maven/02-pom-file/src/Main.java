public class Main {
    // The coordinates Maven uses to identify this project. They match the groupId, artifactId, and version in pom.xml.
    static final String GROUP_ID = "com.example.training";
    static final String ARTIFACT_ID = "maven-demo";
    static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        System.out.println("Project: " + GROUP_ID + ":" + ARTIFACT_ID + ":" + VERSION);
    }
}
