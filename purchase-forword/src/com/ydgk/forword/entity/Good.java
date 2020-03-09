package com.ydgk.forword.entity;

/**
 * @author 游斌
 * @create 2020-02-29  16:13
 */
public class Good {
    public int id;
    public String goodname;
    public String goodtype;
    public Double price;
    public String pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Good() {

    }

    public Good(int id) {
        this.id = id;
    }
    public Good(int id, String goodname, String goodtype) {
        this.id = id;
        this.goodname = goodname;
        this.goodtype = goodtype;
    }

    public Good(int id, String goodname, String goodtype, Double price) {
        this.id = id;
        this.goodname = goodname;
        this.goodtype = goodtype;
        this.price = price;
    }

    public Good(int id, String goodname, String goodtype, Double price, String pic) {

        this.id = id;
        this.goodname = goodname;
        this.goodtype = goodtype;
        this.price = price;
        this.pic = pic;
    }
}
