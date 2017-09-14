package entity;

import android.app.Activity;
import android.content.Context;

/**
 * Created by ios06 on 17/9/14.
 */

public class DAOManager extends Activity{

    private  final  static  String  dbname = "accountms.db";

    public static DaoSession getDaoSession(Context context) {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, dbname, null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        return daoMaster.newSession();

    }
}


