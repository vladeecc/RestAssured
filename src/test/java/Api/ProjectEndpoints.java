package Api;

public final class ProjectEndpoints {
    private ProjectEndpoints() {
    }
    public static final String PROJECTS = "/api/admin/projects";

    public static String projectById(String id) {
        return PROJECTS + "/" + id;
    }
}
