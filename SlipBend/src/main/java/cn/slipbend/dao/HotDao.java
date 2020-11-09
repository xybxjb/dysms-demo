package cn.slipbend.dao;

import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface HotDao {

    /**
     * 增加赛道模式的热度
     * @param modeName
     */
    @Update("UPDATE MODE SET hot = hot+1 WHERE mode_name =#{modeName}")
    void addModeHot(String modeName);
}
