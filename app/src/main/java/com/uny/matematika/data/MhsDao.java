package com.uny.matematika.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MhsDao {

    @Insert
    void insert(EntityMhs... entityMhs);

    @Query("update entitymhs set nama=:nama, alamat=:alamat, hp=:hp where id=:id")
    void update(String nama, String alamat, String hp, int id);

    @Query("select * from entitymhs order by id desc")
    List<EntityMhs> getMhs();

    @Query("select * from entitymhs where nama like:param or alamat like:param or hp like:param")
    List<EntityMhs> seacrhMhs(String param);

    @Query("delete from entitymhs where id=:id")
    void delete(int id);
}
