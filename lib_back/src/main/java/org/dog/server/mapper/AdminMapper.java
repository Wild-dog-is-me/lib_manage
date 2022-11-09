package org.dog.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.common.request.LoginRequest;
import org.dog.server.common.request.PasswordRequest;
import org.dog.server.entity.Admin;
import org.dog.server.entity.User;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/13 00:41
 * @Description:
 */

@Mapper
public interface AdminMapper {

  List<Admin> list();

  List<Admin> listByCondition(BaseRequest baseRequest);

  Admin getById(Integer id);

  void update(Admin admin);

  void deleteById(Integer id);

  void save(Admin admin);

  Admin getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

  int updatePassword(PasswordRequest request);

  Admin getByUsername(String username);
}
