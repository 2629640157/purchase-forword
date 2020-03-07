package com.ydgk.eight.services.impl;

import com.ydgk.eight.entity.Good;
import com.ydgk.eight.services.GoodServices;
import com.ydgk.eight.util.Page;

import java.util.List;

/**
 * @author 游斌
 * @create 2020-02-29  16:54
 */
public class GoodServicesImpl implements GoodServices {


    GoodDao gd = new GoodDao();

    @Override
    public boolean addGood(Good good) {
        return gd.addGood(good);
    }

    @Override
    public boolean updateGood(Good good) {
        return gd.updateGood(good);
    }

    @Override
    public boolean deleteGood(Good good) {
        return gd.deleteGood(good);
    }

    @Override
    public List<Good> findGoods(Good good, Page pg) {
        return gd.listGoods(good, pg);
    }

    @Override
    public int getTotalCount(Good good) {
        return gd.getTotalCount(good);
    }
}
