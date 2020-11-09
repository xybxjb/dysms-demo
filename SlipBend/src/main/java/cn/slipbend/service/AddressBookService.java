package cn.slipbend.service;

import cn.slipbend.util.ServerResponse;

public interface AddressBookService {
    /**
     * 更新【是否允许通讯录】
     * @param id
     * @param allowAddressBook
     * @return
     */
    ServerResponse uptAllowAddressBook(String id, Integer allowAddressBook);

    /**
     * 查找【是否允许通讯录】的状态
     * @param id
     * @return
     */
    ServerResponse findIsAllowAddressBook(String id);

    /**
     * 匹配通讯录
     * @param id
     * @param listAddressBook
     * @return
     */
    ServerResponse matchingAddressBook(Integer id, String listAddressBook);

}
