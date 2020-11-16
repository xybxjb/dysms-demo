package cn.slipbend.service;

import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import cn.slipbend.util.ServerResponse;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 用户行程记录
 */
public interface RouteService {
    /**
     * 根据modeName查询mode
     * @param modeName
     * @return
     */
    Mode findModeIdByModelName(String modeName);

    /**
     * 插入用户行程记录
     * @param routeRecord
     * @return
     */
    Object insertRouteRecord(RouteRecord routeRecord);


    /**
     * 精选路线
     * @return
     */
    ServerResponse findFeaturedRoute();

    /**
     * 查看路线详情
     * @param routeId
     * @return
     */
    Object findRouteDetailById(Integer routeId);

    /**
     * 修改心情
     * @param routeId
     * @param mood
     * @return
     */
    Object updateMood(Integer routeId, Integer mood);

    /**
     * 添加相册
     * @param routeId
     * @param photo
     * @return
     */
    Object addPhoto(Integer routeId, String photo);

    /**
     * 根据用户id查看该用户全部行程或相应分类下的行程
     * @param param
     * @return
     */
    ServerResponse getRoutes(Map<String, Object> param);

    /**
     *批量删除用户行程
     *
     * @param routeIds
     * @return
     */
    ServerResponse deleteRoutes(Integer[] routeIds);

    /**
     * 增加行程热度 | 取消行程热度
     * @param ops 操作：1增加行程热度；2取消行程热度
     * @param routeId 行程id
     * @param userId 用户id
     * @return 该条行程的热度、当前用户是否为该条行程增添了热度
     */
    ServerResponse hotOperations(Integer ops, Integer routeId, Integer userId);

    /**
     * 获取行程照片
     * @param param
     */
    ServerResponse getPhoto(Map<String,Object> param);
}
