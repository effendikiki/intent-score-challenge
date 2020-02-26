package id.putraprima.skorbola;

import android.os.Parcel;
import android.os.Parcelable;

public class scorer implements Parcelable {

    private String scorer_name;

    public scorer(String scorer_name) {
        this.scorer_name = scorer_name;
    }

    public String getScorer_name() {
        return scorer_name;
    }

    public void setScorer_name(String scorer_name) {
        this.scorer_name = scorer_name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.scorer_name);
    }

    protected scorer(Parcel in) {
        this.scorer_name = in.readString();
    }

    public static final Parcelable.Creator<scorer> CREATOR = new Parcelable.Creator<scorer>() {
        @Override
        public scorer createFromParcel(Parcel source) {
            return new scorer(source);
        }

        @Override
        public scorer[] newArray(int size) {
            return new scorer[size];
        }
    };
}
