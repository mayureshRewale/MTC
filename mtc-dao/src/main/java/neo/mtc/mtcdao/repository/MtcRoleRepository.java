package neo.mtc.mtcdao.repository;

import neo.mtc.mtcdao.entity.MtcRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtcRoleRepository extends JpaRepository<MtcRoleEntity, Long> {

    MtcRoleEntity findByMrName(String name);

}
