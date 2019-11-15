package in.christuniversity.servicelearning;

import android.os.Parcel;
import android.os.Parcelable;

public class ChapterPart implements Parcelable {
    int part;
    String url;
    String notes;
    String extra;

    public ChapterPart() {
    }

    public ChapterPart(int part, String url, String notes, String extra) {
        this.part = part;
        this.url = url;
        this.notes = notes;
        this.extra = extra;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    private ChapterPart(Parcel in) {
        part = in.readInt();
        url = in.readString();
        notes = in.readString();
        extra = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(part);
        dest.writeString(url);
        dest.writeString(notes);
        dest.writeString(extra);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ChapterPart> CREATOR = new Parcelable.Creator<ChapterPart>() {
        @Override
        public ChapterPart createFromParcel(Parcel in) {
            return new ChapterPart(in);
        }

        @Override
        public ChapterPart[] newArray(int size) {
            return new ChapterPart[size];
        }
    };
}