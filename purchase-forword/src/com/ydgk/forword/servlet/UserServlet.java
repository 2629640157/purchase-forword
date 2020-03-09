package com.ydgk.forword.servlet;

import com.ydgk.forword.entity.User;
import com.ydgk.forword.services.UserServices;
import com.ydgk.forword.services.impl.UserServicesImpl;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author 游斌
 * @create 2020-03-08  17:23
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    UserServices userServices = new UserServicesImpl();
    private static String codeChars = "1234567890abcdefghijklmnopqrstuvwxyz";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        if (type.equals("register")) {
            registerUser(request, response);
        } else if (type.equals("checkUser")) {
            checkUser(request, response);
        } else if (type.equals("login")) {
            login(request, response);
        } else if (type.equals("getValidate")) {
            getValidate(request, response);
        } else {
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 注册用户
     *
     * @param request
     * @param response
     */
    private void registerUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        String qqcode = request.getParameter("qqcode");
        //User user = new User(username, password, 1, email, qqcode);
        User user = new User(username, password, 1, email, qqcode);
        HttpSession session = request.getSession();
        boolean b = userServices.registerUser(user);
        if (b) {
            session.setAttribute("user", user);
            System.out.println("注册成功！");
            try {
                response.sendRedirect("qiantai/index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("注册失败！");
        }
    }

    /**
     * 验证用户名是否重复
     *
     * @param request
     * @param response
     */
    private void checkUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        User user = new User(username);
        boolean b = userServices.checkUserName(user);
        try {
            response.getWriter().print(b);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void getValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获得验证码集合的长度
        int charsLength = codeChars.length();
        //下面3条是关闭客户端浏览器的缓冲区
        response.setHeader("ragma", "No-cache");
        response.setHeader("Cach-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //设置图形验证码的长宽
        int width = 90, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();//获得输出文字的graphics对象
        Random random = new Random();
        g.setColor(getRandomColor(180, 250));//背景颜色
        g.fillRect(0, 0, width, height);
        //设置初始字体
        g.setFont(new Font("Times New Roman", Font.ITALIC, height));
        g.setColor(getRandomColor(120, 180));//字体颜色
        StringBuilder validationCode = new StringBuilder();
        //验证码的随机字体
        String[] fontNames = {"Times New Roman", "Book antiqua", "Arial"};
        //随机生成3-5个验证码
        for (int i = 0; i < 3 + random.nextInt(3); i++) {
            //随机设置当前验证码的字符的字体
            g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC, height));
            //随机获得当前验证码的字符
            char codeChar = codeChars.charAt(random.nextInt(charsLength));
            validationCode.append(codeChar);
            //随机设置当前验证码字符的颜色
            g.setColor(getRandomColor(10, 100));
            //在图形上输出验证码字符，x y随机生成
            g.drawString(String.valueOf(codeChar), 16 * i + random.nextInt(7), height - random.nextInt(6));
        }
        //获得session对象
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(5 * 60);
        //将验证码保存在session对象中，key为validation_code
        session.setAttribute("validation_code", validationCode.toString());
        g.dispose();
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "JPEG", os);//以JPEG格式向客户端发送图形验证码
    }

    /**
     * 登陆
     *
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //验证用户
        String info="";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verification = request.getParameter("verification");
        User user = new User(username, password);
        HttpSession session = request.getSession();
        if (verification!=null&&verification.equalsIgnoreCase((String) session.getAttribute("validation_code"))){
            boolean b = userServices.checkUser(user);
            if (b){
                System.out.println("成功！");
                session.setAttribute("user",user);
                response.sendRedirect("qiantai/index.jsp");
            }else {
                System.out.println("失败！");
                /*info="密码或用户名错误！";
                session.setAttribute("info",info);*/
                response.getWriter().print("<script>window.alert('用户名或密码错误');window.history.back();</script>");
            }
        }else {
            info="验证码错误！";
            session.setAttribute("info",info);
           // request.getRequestDispatcher("qiantai/login.jsp").forward(request,response);
           response.sendRedirect("qiantai/login.jsp");

        }

    }

    /**
     * 随机颜色
     *
     * @param minColor
     * @param maxColor
     * @return
     */
    private static Color getRandomColor(int minColor, int maxColor) {
        Random random = new Random();
        if (minColor > 255)
            minColor = 255;
        if (maxColor > 255)
            maxColor = 255;
        int red = minColor + random.nextInt(maxColor - minColor);
        int green = minColor + random.nextInt(maxColor - minColor);
        int blue = minColor + random.nextInt(maxColor - minColor);
        return new Color(red, green, blue);
    }
}
