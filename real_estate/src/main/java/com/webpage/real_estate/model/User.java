package com.webpage.real_estate.model;

public class User {

        private String username;
        private String password;
        private String email;
        private String phone;

        public User() {}

        public User(String username, String email, String password, String phone) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.phone = phone;
        }
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }

        public void setUsername(String username) { this.username = username; }
        public void setPassword(String password) { this.password = password; }
        public void setEmail(String email) { this.email = email; }
        public void setPhone(String phone) { this.phone = phone; }

}
