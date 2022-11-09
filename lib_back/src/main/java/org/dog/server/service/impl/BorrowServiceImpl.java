package org.dog.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.Book;
import org.dog.server.entity.Borrow;
import org.dog.server.entity.Retur;
import org.dog.server.entity.User;
import org.dog.server.exception.ServiceException;
import org.dog.server.mapper.BookMapper;
import org.dog.server.mapper.BorrowMapper;
import org.dog.server.mapper.UserMapper;
import org.dog.server.service.BorrowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

/**
 * @Author Odin
 * @Date 2022/10/22 01:55
 * @Description:
 */

@Service
public class BorrowServiceImpl implements BorrowService {

  @Resource
  private BorrowMapper borrowMapper;

  @Resource
  private UserMapper userMapper;

  @Resource
  private BookMapper bookMapper;

  @Override
  public List<Borrow> list() {
    return borrowMapper.list();
  }

  @Override
  public PageInfo<Borrow> page(BaseRequest baseRequest) {
    PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
    List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
    for (Borrow borrow : borrows) {
      System.out.println(borrow);
      System.out.println(borrow.getReturnDate());
      LocalDate returnDate = borrow.getReturnDate();
      System.out.println(LocalDate.now().isEqual(returnDate));
      System.out.println(returnDate);
      System.out.println(LocalDate.now().plusDays(1));
      System.out.println(LocalDate.now().plusDays(1).equals(returnDate));
    }
    for (Borrow borrow : borrows) {
      LocalDate returnDate = borrow.getReturnDate();
      System.out.println("returnDate" + "==============>" + returnDate);
      LocalDate now = LocalDate.now();
      System.out.println(now);
      if (now.plusDays(1).equals(returnDate)) {  // 当前日期比归还的日期小一天
        borrow.setNote("即将到期");
      } else if (now.equals(returnDate)) {
        borrow.setNote("已到期");
      } else if ((now.compareTo(returnDate)) > 0) {
        borrow.setNote("已过期");
      } else {
        borrow.setNote("正常");
      }
    }
    return new PageInfo<>(borrows);
  }

  @Override
  public PageInfo<Retur> pageRetur(BaseRequest baseRequest) {
    PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
    return new PageInfo<>(borrowMapper.listReturByCondition(baseRequest));
  }


  @Override
  @Transactional
  public void save(Borrow obj) {
    // 1. 校验用户的积分是否足够
    String userNo = obj.getUserNo();
    User user = userMapper.getByUsername(userNo);
    if (Objects.isNull(user)) {
      throw new ServiceException("用户不存在");
    }
    // 2. 校验图书是否存在
    Book book = bookMapper.getByNo(obj.getBookNo());
    if (Objects.isNull(book)) {
      throw new ServiceException("所借图书不存在");
    }
    // 4. 校验图书数量
    if (book.getNums() < 1) {
      throw new ServiceException("图书数量不足");
    }
    Integer account = user.getAccount();
    Integer score = book.getScore() * obj.getDays();
    // 3. 校验用户账户余额
    if (score > account) {
      throw new ServiceException("用户积分不足");
    }
    // 5. 更新用户余额
    user.setAccount(user.getAccount() - score);
    userMapper.updateById(user);
    // 6. 更新图书的数量
    book.setNums(book.getNums() - 1);
    bookMapper.updateById(book);
    obj.setReturnDate(LocalDate.now().plus(obj.getDays(), ChronoUnit.DAYS));
    obj.setScore(score);
    // 7. 新增借书记录
    borrowMapper.save(obj);
  }

  @Transactional
  @Override
  public void saveRetur(Retur obj) {
    obj.setStatus("已归还");
    borrowMapper.updateStatus("已归还", obj.getId());
    borrowMapper.saveRetur(obj);
    obj.setRealDate(LocalDate.now());
    borrowMapper.updateByNum(obj.getBookNo());

    Book book = bookMapper.getByNo(obj.getBookNo());
    if (book != null) {
      long until = 0;
      if (obj.getRealDate().isBefore(obj.getReturnDate())) {
        until = obj.getRealDate().until(obj.getReturnDate(), ChronoUnit.DAYS);
      } else if (obj.getRealDate().isAfter(obj.getReturnDate())) {  // 逾期归还，要扣额外的积分
        until = -obj.getReturnDate().until(obj.getRealDate(), ChronoUnit.DAYS);
      }
      int score = (int) until * book.getScore();  // 获取待归还的积分
      User user = userMapper.getByUsername(obj.getUserNo());
      int account = user.getAccount() + score;
      user.setAccount(account);
      if (account < 0) {
        // 锁定账号
        user.setStatus(false);
      }
      userMapper.updateById(user);
    }
  }

  @Override
  public Borrow getById(Integer id) {
    return borrowMapper.getById(id);
  }

  @Override
  public void update(Borrow borrow) {
    borrow.setUpdatetime(LocalDate.now());
    borrowMapper.updateById(borrow);
  }

  @Override
  public void deleteById(Integer id) {
    borrowMapper.deleteById(id);
  }

  @Override
  public void deleteReturById(Integer id) {
    borrowMapper.deleteReturById(id);
  }

}
