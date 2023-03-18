package neo.mtc.mtcdao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mtc_user")
public class MtcUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mu_id")
    private Long muId;

    @Column(name = "mu_user_id")
    private String muUserId;

    @Column(name = "mu_firstname")
    private String muFirstname;

    @Column(name = "mu_lastname")
    private String muLastname;

    @Column(name = "mu_email")
    private String muEmail;

    @Column(name = "mu_phone")
    private String muPhone;

    @Column(name = "mu_username")
    private String muUsername;

    @Column(name = "mu_password")
    private String muPassword;

    @Column(name = "mu_created_by")
    private String muCreatedBy;

    @Column(name = "mu_update_by")
    private String muUpdatedBy;

    @Column(name = "mu_created_date")
    private LocalDateTime muCreatedDate;

    @Column(name = "mu_updated_date")
    private LocalDateTime muUpdatedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "mb_user_role_mapping",
            joinColumns = @JoinColumn(name = "mu_id"),
            inverseJoinColumns = @JoinColumn(name = "mr_id")
    )
    private Set<MtcRoleEntity> mtcRoles = new HashSet<>();

}
