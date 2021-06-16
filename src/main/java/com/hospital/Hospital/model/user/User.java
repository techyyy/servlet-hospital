package com.hospital.Hospital.model.user;

/**
 *
 * User entity
 *
 */
public class User {

    private final int id;
    private final String username;
    private final String password;
    private final Role role;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
    }

    public int getId() {
        return id;
    }

    public Role getRole() { return role; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return username+password+role;
    }

    public static class UserBuilder {
        private int id;
        private final String username;
        private final String password;
        private final Role role;

        public UserBuilder(String username, String password, Role role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
