package com.example.finallibrary;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    public int id;
    public String name;
    public String author;
    public String image;
    public String text;

    public Book(Integer Id, String Name, String Author, String Image, String Text){
        id = Id;
        name = Name;
        author = Author;
        image = Image;
        text = Text;
    }
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[0];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.author);
        dest.writeString(this.image);
        dest.writeString(this.text);
    }

    public Book(Parcel parcel){
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.author = parcel.readString();
        this.image = parcel.readString();
        this.text = parcel.readString();
    }
}
