package cloud.service;

import cloud.entity.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {
    /**
     * 通过usercode 获取User信息
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 根据page获取User列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<User> findUsersByPage(int currentPage, int pageSize);
}
