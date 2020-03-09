package com.ydgk.forword.dao;

import com.ydgk.forword.entity.User;
import com.ydgk.forword.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 游斌
 * @create 2020-02-29  10:44
 */
public class UserDao {
    BaseDao bd = new BaseDao();

    public boolean queryUser(User user) {
        boolean a = false;
        Connection con = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) from user where username=? and password=? ";
        try {
            ps = con.prepareStatement(sql);
            rs = bd.exeQuery(con, ps, user.getUsername(), user.getPassword());
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 1) {
                    a = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.closeAll(rs, ps, con);
        }
        return a;
    }

    public boolean registerUser(User user) {
        boolean b = false;
        String sql = "insert into user(username,password,rule,email,qq) values (?,?,?,?,?)";
        Connection con = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            bd.exeUpdate(con, ps, user.getUsername(),user.getPassword(),user.getRule(),user.getEmail(),user.getQq());
            con.commit();
            b = true;
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
        return b;
    }

    public boolean checkUserName(User user) {
        boolean a = false;
        Connection con = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) from user where username=? ";
        try {
            ps = con.prepareStatement(sql);
            rs = bd.exeQuery(con, ps, user.getUsername());
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 1) {
                    a = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.closeAll(rs, ps, con);
        }
        return a;
    }

}
