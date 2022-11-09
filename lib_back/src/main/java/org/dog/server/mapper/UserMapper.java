package org.dog.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.User;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/10 08:42
 * @Description:
 */

@Mapper
public interface UserMapper {

  List<User> list();

  List<User> listByCondition(BaseRequest baseRequest);

  User getById(Integer id);

  void updateById(User user);

  void deleteById(Integer id);

  void save(User user);

  User getByUsername(String userNo);
}
