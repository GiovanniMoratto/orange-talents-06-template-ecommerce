package br.com.zupacademy.giovannimoratto.ecommerce.new_user;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

/**
 * @Author giovanni.moratto
 */

@Entity
@Table(name = "tb_usuarios")
public class UserModel {

    /* Attributes */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "LOGIN", nullable = false)
    private String login;
    @Column(name = "SENHA", nullable = false)
    private String password;
    @CreationTimestamp
    @PastOrPresent
    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime createdAt;

    /* Constructors */
    // Default - JPA
    @Deprecated
    public UserModel() {
    }

    // Set UserRequest.class values in UserModel.class
    public UserModel(String requestLogin, String requestPassword) {
        this.login = requestLogin;
        this.password = UserRepository.userPasswordEncoder(requestPassword);
    }

    // Check for Hash in UserControllerTest
    public String getPassword() {
        return password;
    }

    // Check for date Before or equal to Present in UserControllerTest
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}