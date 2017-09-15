package entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ios06 on 17/9/14.
 */

@Entity
public class Tb_flag {

    @Id(autoincrement=true)
    private  long _id;
    private String flag;
    public String getFlag() {
        return this.flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public long get_id() {
        return this._id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
    @Generated(hash = 811132423)
    public Tb_flag(long _id, String flag) {
        this._id = _id;
        this.flag = flag;
    }
    @Generated(hash = 1347222124)
    public Tb_flag() {
    }
}
