package models;

public class UserModel {
	private int id;
    private String username;
    private String rol;

    public UserModel(int id, String username, String rol) {
        this.id = id;
        this.username = username;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getRol() {
        return rol;
    }

    public int getId() {
        return id;
    }
}
