package org.dog.server.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.dog.server.common.request.PasswordRequest;
import org.dog.server.exception.ServiceException;
import org.dog.server.common.dto.LoginDTO;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.common.request.LoginRequest;
import org.dog.server.entity.Admin;
import org.dog.server.mapper.AdminMapper;
import org.dog.server.service.AdminService;
import org.dog.server.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/13 00:41
 * @Description:
 */

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

  @Resource
  private AdminMapper adminMapper;

  private static final String DEFAULT_PASS = "root";

  private static final String PASS_SALT = "Odin";

  public String securityPass(String password) {
    return SecureUtil.md5(password + PASS_SALT);
  }

  @Override
  public List<Admin> list() {
    return adminMapper.list();
  }

  @Override
  public PageInfo<Admin> page(BaseRequest baseRequest) {
    PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
    List<Admin> admins = adminMapper.listByCondition(baseRequest);
    return new PageInfo<>(admins);
  }

  @Override
  public Admin getById(Integer id) {
    return adminMapper.getById(id);
  }

  @Override
  public void update(Admin admin) {
    admin.setUpdatetime(new Date());
    adminMapper.update(admin);
  }

  @Override
  public void deleteById(Integer id) {
    adminMapper.deleteById(id);
  }

  @Override
  public void save(Admin admin) {
    if (StrUtil.isBlank(admin.getPassword())) {
      admin.setPassword(DEFAULT_PASS);
    }

    admin.setPassword(securityPass(admin.getPassword()));

    try {
      adminMapper.save(admin);
    } catch (DuplicateKeyException e) {
      log.error("数据插入失败，username{}", admin.getUsername(), e);
      throw new ServiceException("用户名重复");
    }

  }

  @Override
  public LoginDTO login(LoginRequest request) {
    Admin admin = null;
    try {
      admin = adminMapper.getByUsername(request.getUsername());
    } catch (Exception e) {
      log.error("根据用户名{}查询出错", request.getUsername());
      throw new ServiceException("用户名错误");
    }

    if (admin == null) {
      throw new ServiceException("用户名或密码错误");
    }

    String securityPass = securityPass(request.getPassword());
    if (!securityPass.equals(admin.getPassword())) {
      throw new ServiceException("用户名或密码错误");
    }

    if (!admin.isStatus()) {
      throw new ServiceException("当前用户处于禁用状态，请联系管理员");
    }

    LoginDTO loginDTO = new LoginDTO();
    BeanUtils.copyProperties(admin, loginDTO);

    String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
    loginDTO.setToken(token);
    return loginDTO;
  }

  @Override
  public void changePass(PasswordRequest request) {
    request.setNewPass(securityPass(request.getNewPass()));
    int count = adminMapper.updatePassword(request);
    if (count <= 0) {
      throw new ServiceException("修改密码失败");
    }
  }
}
