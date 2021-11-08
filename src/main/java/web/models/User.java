package web.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty(message = "Укажите имя")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Укажите фамилию")
    @Column(name = "surname", nullable = false)
    private String surName;

    @Min(value = 1, message ="Укажите возраст от 1 до 127 лет")
    @Max(value = 127, message ="Укажите возраст от 1 до 127 лет")
    @Column(name="age", nullable = false)
    private int age;

    @NotEmpty(message = "Укажите почту")
    @Email(message = "Укажите существующую почту")
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty(message = "Необходимо заполнить поле Пароль")
    @Size(min = 4, message = "Пароль должен быть не менее 4 символов")
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty(message = "Укажите роль")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(String name, String surName, int age, String email, String password, Set<Role> roles) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(Long id, String name, String surName, int age, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", username='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        for (Role role : getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
