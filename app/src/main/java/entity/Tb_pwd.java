package entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ios06 on 17/9/13.
 */

@Entity
public class Tb_pwd {

    @Id(autoincrement = true)
    private Long ID;
    private String password;
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    @Generated(hash = 1164401940)
    public Tb_pwd(Long ID, String password) {
        this.ID = ID;
        this.password = password;
    }
    @Generated(hash = 1740597791)
    public Tb_pwd() {
    }
}
