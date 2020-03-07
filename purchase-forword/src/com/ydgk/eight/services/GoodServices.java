package com.ydgk.eight.services;

import com.ydgk.eight.entity.Good;
import com.ydgk.eight.util.Page;

import java.util.List;

/**
 * @author 游斌
 * @create 2020-02-29  16:54
 */
public interface GoodServices {
    public boolean addGood(Good good);

    public boolean updateGood(Good good);

    public boolean deleteGood(Good good);

    public List<Good> findGoods(Good good, Page pg);

    public int getTotalCount(Good good);
}
