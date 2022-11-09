package org.dog.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.Borrow;
import org.dog.server.entity.Retur;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/22 01:11
 * @Description:
 */

@Mapper
public interface BorrowMapper {

  List<Borrow> list();

  List<Borrow> listByCondition(BaseRequest baseRequest);

  List<Retur> listReturByCondition(BaseRequest baseRequest);

  void saveRetur(Retur obj);

  void save(Borrow obj);

  Borrow getById(Integer id);

  void updateById(Borrow user);

  void deleteById(Integer id);

  void deleteReturById(Integer id);

  void updateStatus(@Param("status") String status, @Param("id") Integer id);

  void updateByNum(String bookNo);


}
