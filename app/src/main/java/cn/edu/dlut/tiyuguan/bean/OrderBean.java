package cn.edu.dlut.tiyuguan.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ORDER_BEAN".
 */
public class OrderBean {

    /** Not-null value. */
    private String orderId;
    private Long startTime;
    private Long endTime;
    /** Not-null value. */
    private String userId;

    public OrderBean() {
    }

    public OrderBean(String orderId) {
        this.orderId = orderId;
    }

    public OrderBean(String orderId, Long startTime, Long endTime, String userId) {
        this.orderId = orderId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
    }

    /** Not-null value. */
    public String getOrderId() {
        return orderId;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /** Not-null value. */
    public String getUserId() {
        return userId;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUserId(String userId) {
        this.userId = userId;
    }

}