package org.dog.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.User;
import org.dog.server.mapper.UserMapper;
import org.dog.server.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/10 08:46
 * @Description:
 */

@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserMapper userMapper;

  @Override
  public List<User> list() {
    return userMapper.list();
  }

  @Override
  public User getById(Integer id) {
    return userMapper.getById(id);
  }

  @Override
  public PageInfo<User> page(BaseRequest baseRequest) {
    PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
    List<User> users = userMapper.listByCondition(baseRequest);
    return new PageInfo<>(users);
  }

  @Override
  public void update(User user) {
    user.setUpdatetime(new Date());
    userMapper.updateById(user);
  }

  @Override
  public void deleteById(Integer id) {
    userMapper.deleteById(id);
  }

  @Override
  public void save(User user) {
    Date date = new Date();
    user.setUsername(DateUtil.format(date, "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
    userMapper.save(user);
  }

  @Override
  public void handleAccount(User user) {
    Integer score = user.getScore();
    if (score == null) {
      return;
    }
    Integer id = user.getId();
    User dbUser = userMapper.getById(id);
    dbUser.setAccount(dbUser.getAccount() + score);
    userMapper.updateById(dbUser);
  }
}
