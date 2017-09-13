package entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ios06 on 17/9/13.
 */

@Entity
public class Tb_inaccount {

    @Id(autoincrement = true)
    private Long _id;
    private double money;
    private String time;
    private String type;
    private String handler;
    private String mark;
    public String getMark() {
        return this.mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getHandler() {
        return this.handler;
    }
    public void setHandler(String handler) {
        this.handler = handler;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public double getMoney() {
        return this.money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    @Generated(hash = 1968652941)
    public Tb_inaccount(Long _id, double money, String time, String type,
            String handler, String mark) {
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.handler = handler;
        this.mark = mark;
    }
    @Generated(hash = 766497487)
    public Tb_inaccount() {
    }

    public Tb_inaccount(double money, String time, String type,
             String handler, String mark) {
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.handler = handler;
        this.mark = mark;
    }

}
