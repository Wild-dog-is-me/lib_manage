package org.dog.server.service;

import com.github.pagehelper.PageInfo;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.Borrow;
import org.dog.server.entity.Retur;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/22 01:54
 * @Description:
 */
public interface BorrowService {

  List<Borrow> list();

  PageInfo<Borrow> page(BaseRequest baseRequest);

  PageInfo<Retur> pageRetur(BaseRequest baseRequest);

  void save(Borrow borrow);

  void saveRetur(Retur obj);

  Borrow getById(Integer id);

  void update(Borrow borrow);

  void deleteById(Integer id);

  void deleteReturById(Integer id);
}
