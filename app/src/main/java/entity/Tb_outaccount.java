package entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ios06 on 17/9/13.
 */

@Entity
public class Tb_outaccount {

    @Id(autoincrement = true)
    private Long _id;
    private double money;
    private String time;
    private String type;
    private String address;
    private String mark;
    public String getMark() {
        return this.mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    @Generated(hash = 2086376323)
    public Tb_outaccount(Long _id, double money, String time, String type,
            String address, String mark) {
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.address = address;
        this.mark = mark;
    }
    @Generated(hash = 1644516401)
    public Tb_outaccount() {
    }

    public Tb_outaccount( double money, String time, String type,
                         String address, String mark) {
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.address = address;
        this.mark = mark;
    }

}
