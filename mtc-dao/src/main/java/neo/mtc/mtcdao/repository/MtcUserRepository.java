package neo.mtc.mtcdao.repository;

import neo.mtc.mtcdao.entity.MtcUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtcUserRepository extends JpaRepository<MtcUserEntity, Long> {

    MtcUserEntity findByMuEmail(String email);

    MtcUserEntity findByMuUsername(String username);

}
