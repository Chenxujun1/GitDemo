package cloud.dao;

import cloud.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    /**
     * 根据UserCode获取User信息
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * User统计并分页
     * @return
     */
    Page<User> findUsersByPage();
}
