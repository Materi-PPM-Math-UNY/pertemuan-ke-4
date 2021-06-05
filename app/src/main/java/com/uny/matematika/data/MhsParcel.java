package com.uny.matematika.data;


import android.os.Parcel;
import android.os.Parcelable;

public class MhsParcel implements Parcelable {
    public static final Creator<MhsParcel> CREATOR = new Creator<MhsParcel>() {
        @Override
        public MhsParcel createFromParcel(Parcel in) {
            return new MhsParcel(in);
        }

        @Override
        public MhsParcel[] newArray(int size) {
            return new MhsParcel[size];
        }
    };
    private int id;
    private String nama;
    private String alamat;
    private String hp;
    private String photo;

    public MhsParcel() {
    }

    protected MhsParcel(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        alamat = in.readString();
        hp = in.readString();
        photo = in.readString();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(hp);
        dest.writeString(photo);
    }
}
