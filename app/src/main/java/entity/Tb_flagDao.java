package entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TB_FLAG".
*/
public class Tb_flagDao extends AbstractDao<Tb_flag, Long> {

    public static final String TABLENAME = "TB_FLAG";

    /**
     * Properties of entity Tb_flag.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, long.class, "_id", true, "_id");
        public final static Property Flag = new Property(1, String.class, "flag", false, "FLAG");
    }


    public Tb_flagDao(DaoConfig config) {
        super(config);
    }
    
    public Tb_flagDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TB_FLAG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: _id
                "\"FLAG\" TEXT);"); // 1: flag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TB_FLAG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Tb_flag entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.get_id());
 
        String flag = entity.getFlag();
        if (flag != null) {
            stmt.bindString(2, flag);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Tb_flag entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.get_id());
 
        String flag = entity.getFlag();
        if (flag != null) {
            stmt.bindString(2, flag);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Tb_flag readEntity(Cursor cursor, int offset) {
        Tb_flag entity = new Tb_flag( //
            cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // flag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Tb_flag entity, int offset) {
        entity.set_id(cursor.getLong(offset + 0));
        entity.setFlag(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Tb_flag entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Tb_flag entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Tb_flag entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
