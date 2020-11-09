package cn.slipbend.service.impl;

import cn.slipbend.dao.AddressBookDao;
import cn.slipbend.dao.FollowDao;
import cn.slipbend.enums.RelationshipEnum;
import cn.slipbend.model.User;
import cn.slipbend.service.AddressBookService;
import cn.slipbend.util.ServerResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookDao addressBookDao;

    @Autowired
    private FollowDao followDao;

    @Override
    public ServerResponse uptAllowAddressBook(String id, Integer allowAddressBook) {
        try {
            addressBookDao.uptAllowAddressBook(id,allowAddressBook);
            return ServerResponse.getSuccess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("修改失败");
        }
    }

    @Override
    public ServerResponse findIsAllowAddressBook(String id) {
        try {
            return ServerResponse.getSuccess("查找成功",addressBookDao.findIsAllowAddressBook(id));
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("查找失败");
        }
    }

    /**
     * 匹配通讯录
     * @param id
     * @param listAddressBook
     * @return
     */
    @Override
    public ServerResponse matchingAddressBook(Integer id, String listAddressBook) {

        List<Map> list = new ArrayList<>();
        String tel = "phone";
        String name = "name";
        String nameValue = null;
        String userId = "userId";
        String relationship = "relationship";

        try{
            JSONArray jsonArray = JSONArray.parseArray(listAddressBook);
            for(Object map2:jsonArray){
                JSONObject map1 = (JSONObject) map2;
                for (Map.Entry<String, Object> entry : map1.entrySet()) {
                    if("name".equals(entry.getKey())){
                        nameValue = (String) entry.getValue();
                    }

                    if("phones".equals(entry.getKey())){
                        JSONArray value = (JSONArray) entry.getValue();
                        for(Object phone:value){

                            User user = addressBookDao.findUserBy((String) phone);
                            // 是该 APP 用户 且 允许出现在通讯录的用户
                            if(user != null && 1 == user.getAllowAddressBook()){
                                Map<String,Object> map = new HashMap<>();
                                map.put(tel,phone);
                                map.put(name,nameValue);
                                map.put(userId,user.getId());

                                // 查看该用户是不是我关注的
                                Integer myFollow = followDao.findFollow(user.getId(),id);
                                // 查看该用户是不是关注我的
                                Integer myFan = followDao.findFollow(id,user.getId());

                                // 互相关注，为好友
                                if(myFollow == 1 && myFan == 1){
                                    map.put(relationship, RelationshipEnum.FRIEND);
                                }
                                // 此用户是我单方面关注的
                                if(myFollow == 1 && myFan == 0){
                                    map.put(relationship, RelationshipEnum.MY_FOLLOW);
                                }
                                // 此用户单方面关注了我，是我的粉丝
                                if(myFollow == 0 && myFan == 1){
                                    map.put(relationship, RelationshipEnum.MY_FAN);
                                }
                                // 只是该 APP 的用户，互未关注
                                if(myFollow == 0 && myFan == 0){
                                    map.put(relationship, RelationshipEnum.NO_RELATIONSHIP);
                                }
                                list.add(map);
                            }
                        }
                    }
                }
            }
            return ServerResponse.getSuccess("匹配成功",list);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getError("匹配失败");
        }
    }
}
