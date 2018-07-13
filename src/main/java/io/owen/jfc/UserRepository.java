package io.owen.jfc;

import io.owen.jfc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by owen_q on 2018. 7. 9..
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
