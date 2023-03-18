package neo.mtc.mtcdao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mtc_role")
public class MtcRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mr_id")
    private Long mrId;

    @Column(name = "mr_name")
    private String mrName;

    @Column(name = "mr_description")
    private String mrDescription;

    @Column(name = "mu_created_by")
    private String mrCreatedBy;

    @Column(name = "mu_update_by")
    private String mrUpdatedBy;

    @Column(name = "mu_created_date")
    private LocalDate mrCreatedDate;

    @Column(name = "mu_updated_date")
    private String mrUpdatedDate;

}
