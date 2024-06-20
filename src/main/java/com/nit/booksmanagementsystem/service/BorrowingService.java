package com.nit.booksmanagementsystem.service;

import com.nit.booksmanagementsystem.dao.BorrowingDao;
import com.nit.booksmanagementsystem.entity.Borrowing;
import com.nit.booksmanagementsystem.utils.CommonUtil;
import com.nit.booksmanagementsystem.vo.BorrowingVo;

import java.util.List;

/**
 * 借阅记录服务类
 * 负责处理与借阅记录相关的业务逻辑
 */
public class BorrowingService {

    /**
     * 借阅记录 DAO 对象
     */
    BorrowingDao borrowingDao = new BorrowingDao();

    /**
     * 插入一条新的借阅记录
     * @param borrowing 待插入的借阅记录实体
     */
    public void insertOne(Borrowing borrowing) {
        borrowingDao.insertOne(borrowing);
    }

    /**
     * 查询所有借阅记录,并转换为 BorrowingVo 视图对象列表
     * @return 所有借阅记录的 BorrowingVo 列表
     */
    public List<BorrowingVo> selectVoAll() {
        return borrowingDao.selectVoAll();
    }

    /**
     * 查询当前用户的所有借阅记录,并转换为 BorrowingVo 视图对象列表
     * @return 当前用户的所有借阅记录的 BorrowingVo 列表
     */
    public List<BorrowingVo> selectVoByCurrentUser() {
        return borrowingDao.selectVoByUserId(CommonUtil.getUserId());
    }

}