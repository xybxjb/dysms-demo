package cn.slipbend.dao;

import cn.slipbend.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookDao {

    /**
     * 更新 allow_address_book 字段
     * @param id
     * @param allowAddressBook
     * @return
     */
    @Update("UPDATE USER SET allow_address_book = #{allowAddressBook} WHERE id =#{id}")
    Integer uptAllowAddressBook(String id, Integer allowAddressBook);

    /**
     * 查找 allow_address_book 的值
     * @param id
     * @return
     */
    @Select("SELECT allow_address_book FROM user WHERE id = #{id}")
    Integer findIsAllowAddressBook(String id);

    /**
     * 通过 phone 查找用户
     * @param phone
     * @return User
     */
    @Results(id = "AreaMapper",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "allowAddressBook",column = "allow_address_book")
    })
    @Select("SELECT id,allow_address_book FROM user WHERE phone = #{phone}")
    User findUserBy(String phone);
}
