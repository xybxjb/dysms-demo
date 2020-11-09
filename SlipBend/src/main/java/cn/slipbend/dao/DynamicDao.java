package cn.slipbend.dao;

import cn.slipbend.model.Dynamic;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tht
 * @version 1.0
 */
@Repository
public interface DynamicDao {
    /**
     * 发布动态
     * @param id
     * @param text
     * @param imgsUrl
     * @param power
     * @param lon
     * @param lat
     */
    @Insert("INSERT INTO DYNAMIC (user_id,`text`,imgs_url,`power`,lon,lat,location_text) VALUES (#{id},#{text},#{imgsUrl},#{power},#{lon},#{lat},#{locationText})")
    void saveReleaseNews(@Param("id") String id, @Param("text") String text,@Param("imgsUrl") String imgsUrl, @Param("power") String power, @Param("lon") double lon, @Param("lat") double lat,@Param("locationText")String locationText);

    /**
     * 动态关注
     * @param id
     * @return
     */
    @Select("SELECT u.`username`,u.`icon`,dd.*,b.fans_num FROM (SELECT * FROM DYNAMIC d WHERE user_id IN(SELECT follow_user_id FROM follow WHERE fans_user_id=#{id}) AND `power` = '公开') AS dd\n" +
            "INNER JOIN USER u ON u.id = dd.user_id\n" +
            "INNER JOIN\n" +
            "(SELECT `follow_user_id`,COUNT(*) fans_num FROM follow GROUP BY follow_user_id)AS b\n" +
            "ON dd.user_id = b.follow_user_id \n" +
            "ORDER BY create_time DESC")
    @Results(id = "DynamicResult",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "userId.username",column = "username"),
            @Result(property = "userId.icon",column = "icon"),
            @Result(property = "userId.id",column = "user_id"),
            @Result(property = "text",column = "text"),
            @Result(property = "imgsUrl",column = "imgs_url"),
            @Result(property = "good",column = "good"),
            @Result(property = "stepOn",column = "step_on"),
            @Result(property = "power",column = "power"),
            @Result(property = "lon",column = "lon"),
            @Result(property = "lat",column = "lat"),
            @Result(property = "views",column = "views"),
            @Result(property = "fans.fansNum",column = "fans_Num"),
            @Result(property = "createDate",column = "create_time"),
    })
    List<Dynamic> getAttentions(String id);

    //动态推荐
//    List<Dynamic> getRecommend(String id);

    /**
     * 获取某条动态
     * @param dynamicId
     * @return
     */
    @Select("select d.*,u.username,u.icon from dynamic d left join user u on d.user_id = u.id where d.id = #{dynamicId}")
    @ResultMap("DynamicResult")
    Dynamic getDynamic(Integer dynamicId);

    /**
     * 获取我所有的动态
     * @param userId
     * @param a
     * @param b
     * @return
     */
    @Select("SELECT * FROM DYNAMIC WHERE user_id = #{userId}\n" +
            "ORDER BY create_time DESC\n" +
            "LIMIT #{a},#{b}")
    @ResultMap("DynamicResult")
    List<Dynamic> getMyAllDynamic(Integer userId,Integer a,Integer b);
}
