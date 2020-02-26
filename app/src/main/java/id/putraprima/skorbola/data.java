package id.putraprima.skorbola;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class data implements Parcelable {
    private String homeName, awayName, scorerName;
    private Uri homeUri, awayUri;
    private int homeScore, awayScore;

    public data(String homeName, String awayName, Uri homeUri, Uri awayUri, int homeScore, int awayScore, String scorerName) {
        this.homeName = homeName;
        this.awayName = awayName;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getScorerName() {
        return scorerName;
    }

    public void setScorerName(String scorerName) {
        this.scorerName = scorerName;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public Uri getHomeUri() {
        return homeUri;
    }

    public void setHomeUri(Uri homeUri) {
        this.homeUri = homeUri;
    }

    public Uri getAwayUri() {
        return awayUri;
    }

    public void setAwayUri(Uri awayUri) {
        this.awayUri = awayUri;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.homeName);
        dest.writeString(this.awayName);
        dest.writeString(this.scorerName);
        dest.writeParcelable(this.homeUri, flags);
        dest.writeParcelable(this.awayUri, flags);
        dest.writeInt(this.homeScore);
        dest.writeInt(this.awayScore);
    }

    protected data(Parcel in) {
        this.homeName = in.readString();
        this.awayName = in.readString();
        this.scorerName = in.readString();
        this.homeUri = in.readParcelable(Uri.class.getClassLoader());
        this.awayUri = in.readParcelable(Uri.class.getClassLoader());
        this.homeScore = in.readInt();
        this.awayScore = in.readInt();
    }

    public static final Creator<data> CREATOR = new Creator<data>() {
        @Override
        public data createFromParcel(Parcel source) {
            return new data(source);
        }

        @Override
        public data[] newArray(int size) {
            return new data[size];
        }
    };
}
