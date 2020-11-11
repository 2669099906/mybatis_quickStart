package com.zxr.pojo;

/**
 * @author zhaoxiangrui
 * @date 2020/11/10 22:14
 */
public class Order {

    private Integer id;
    private String orderTime;
    private Double total;
    private Integer uid;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTime='" + orderTime + '\'' +
                ", total=" + total +
                ", user=" + user +
                '}';
    }
}
