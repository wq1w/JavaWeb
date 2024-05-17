package com.nit.booksmanagementsystem.service;

import com.nit.booksmanagementsystem.dao.BorrowingDao;
import com.nit.booksmanagementsystem.entity.Borrowing;
import com.nit.booksmanagementsystem.utils.CommonUtil;
import com.nit.booksmanagementsystem.vo.BorrowingVo;

import java.util.List;

public class BorrowingService {
    BorrowingDao borrowingDao = new BorrowingDao();

    public void insertOne(Borrowing borrowing) {
        borrowingDao.insertOne(borrowing);
    }

    public List<BorrowingVo> selectVoAll() {
        return borrowingDao.selectVoAll();
    }

    public List<BorrowingVo> selectVoByUserId(int id) {
        return borrowingDao.selectVoByUserId(id);
    }

    public List<BorrowingVo> selectVoByCurrentUser() {
        return borrowingDao.selectVoByUserId(CommonUtil.getUserId());
    }

}
