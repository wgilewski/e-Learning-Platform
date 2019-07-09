package wg.app.model.user;

public enum Role
{
    TEACHER("ROLE_TEACHER"),
    ADMIN("ROLE_ADMIN"),
    STUDENT("ROLE_STUDENT");

    private String fullName;

    Role(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
