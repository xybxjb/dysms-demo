package cn.slipbend.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPositionDao {
    @Insert("INSERT INTO user_position ")
    public void updateUserPosition();
}
