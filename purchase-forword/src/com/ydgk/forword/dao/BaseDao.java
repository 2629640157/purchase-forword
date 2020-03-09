package com.ydgk.forword.dao;

import com.ydgk.forword.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 游斌
 * @create 2020-02-28  22:48
 */
public class BaseDao {
    //公共查询
    public ResultSet exeQuery(Connection con, PreparedStatement ps, Object... params) {
        ResultSet rs = null;
        try {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    //公共修改（增删改）
    public void exeUpdate(Connection con, PreparedStatement ps, Object... params) throws SQLException {
        if (params != null) {
            //给占位符赋值
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        ps.executeUpdate();
    }

    //释放资源
    public void closeAll(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        BaseDao bd = new BaseDao();
        Connection con = JdbcUtil.getConnection();
        String sql = "insert into user values(null,?,?,?,null,null)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            bd.exeUpdate(con, ps, "xxxx", "1111", 3);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.closeAll(null, ps, con);
        }

    }
}
