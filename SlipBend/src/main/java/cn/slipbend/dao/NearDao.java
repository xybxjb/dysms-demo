package cn.slipbend.dao;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface NearDao {

    @Insert("insert into left_slider (user_id,cover_user_id) VALUES (#{userId},#{slideLeftUserId})")
    void saveSlideToLeft(Integer userId, Integer slideLeftUserId);

    @Insert("insert into right_slider (user_id,cover_user_id) VALUES (#{userId},#{slideRightUserId})")
    void saveSlideToRight(Integer userId, Integer slideRightUserId);
}
