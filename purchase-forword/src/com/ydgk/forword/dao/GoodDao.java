package com.ydgk.forword.dao;

import com.ydgk.forword.entity.Good;
import com.ydgk.forword.util.JdbcUtil;
import com.ydgk.forword.util.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 游斌
 * @create 2020-02-29  16:54
 */
public class GoodDao {
    BaseDao bd = new BaseDao();

    /**
     * 添加商品
     * @param good
     * @return
     */
    public boolean addGood(Good good) {
        boolean a = false;
        String sql = "insert into good(goodname,goodtype,price,pic) value(?,?,?,?) ";
        Connection con = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            bd.exeUpdate(con, ps, good.getGoodname(), good.getGoodtype(), good.getPrice(), good.getPic());
            con.commit();
            a = true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            bd.closeAll(null, ps, con);
        }
        return a;

    }

    /**
     * 删除商品
     * @param good
     * @return
     */
    public boolean deleteGood(Good good) {
        boolean a = false;
        String sql = "delete from  good where id=?";
        Connection con = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            bd.exeUpdate(con, ps, good.getId());
            con.commit();
            a = true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            bd.closeAll(null, ps, con);
        }
        return a;

    }

    /**
     * 修改商品
     * @param good
     * @return
     */
    public boolean updateGood(Good good) {
        boolean a = false;
        String sql = "update good set goodname=? ,goodtype=? , price=? WHERE id=? ";
        Connection con = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            bd.exeUpdate(con, ps, good.getGoodname(), good.getGoodtype(), good.getPrice(), good.getId());
            con.commit();
            a = true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            bd.closeAll(null, ps, con);
        }
        return a;

    }
    public List<Good> listGoods(Good good, Page pg) {
        List<Good> goodList = new ArrayList<>();
        Connection con = JdbcUtil.getConnection();
        String condition = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from good ";
        condition = getCondition(good);
        sql = sql + condition + " group by id limit  ?,?";
        try {
            ps = con.prepareStatement(sql);
            //rs=ps.executeQuery();
            rs = bd.exeQuery(con, ps, (pg.getPageNow() - 1) * pg.getPageSize(), pg.getPageSize());
            while (rs.next()) {
                Good goodBean = new Good();
                goodBean.setId(rs.getInt(1));
                goodBean.setGoodname(rs.getString(2));
                goodBean.setGoodtype(rs.getString(3));
                goodBean.setPrice(rs.getDouble(4));
                goodBean.setPic(rs.getString(5));
                goodList.add(goodBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.closeAll(rs, ps, con);
        }
        return goodList;
    }


    /**
     *
     * @return   返回查询中记录数
     */
    public int getTotalCount(Good good) {
        int TotalCount = 0;
        String condition="";
        String sql = "select count(*) from good";
        Connection con = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        condition = getCondition(good);
        sql = sql + condition;
        try {
            ps = con.prepareStatement(sql);
            rs = bd.exeQuery(con, ps);
            if (rs.next()) {
                TotalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.closeAll(rs, ps, con);
        }
        return TotalCount;
    }
    //用来拼串判断
    private static String getCondition(Good good) {
        String condition = "";
        if (good.getId() != -1) {
            condition = condition + " where id= " + good.getId();
            if (!good.getGoodname().equals("")) {//name为空
                condition = condition + " and goodname like '%" + good.getGoodname() + "%'";
                if (!good.getGoodtype().equals("")) {
                    condition = condition + "' and goodtype like '%" + good.getGoodtype() + "%'";
                }
            } else {//name不为空
                if (!good.getGoodtype().equals("")) {
                    condition = condition + "' and goodtype like '%" + good.getGoodtype() + "%'";
                }
            }
        } else {
            if (!good.getGoodname().equals("")) {//name为空
                condition = condition + " where goodname like '%" + good.getGoodname() + "%'";
                if (!good.getGoodtype().equals("")) {
                    condition = condition + "' and goodtype like '%" + good.getGoodtype() + "%'";
                }
            } else {
                if (!good.getGoodtype().equals("")) {
                    condition = condition + " where goodtype='" + good.getGoodtype() + "'";
                }
            }
        }
        return condition;
    }
}
