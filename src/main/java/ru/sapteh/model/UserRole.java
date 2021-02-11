package ru.sapteh.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "registr_date")
    @NonNull
    private Date registrationDate = new Date();

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @NonNull
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", RegistrationDate=" + registrationDate +
                ", userId=" + getUser().getId() +
                ", roleId=" + getRole().getName() +
                '}';
    }
}