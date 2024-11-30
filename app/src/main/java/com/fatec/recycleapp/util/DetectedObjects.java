package com.fatec.recycleapp.util;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.fatec.recycleapp.model.MaterialCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetectedObjects implements Parcelable {
    private Bitmap source;
    private Bitmap result;
    private final List<MaterialCategory> categories;
    private final List<Double> scores;

    public DetectedObjects() {
        categories = new ArrayList<>();
        scores = new ArrayList<>();
    }

    public DetectedObjects(Bitmap source) {
        this.source = source;

        categories = new ArrayList<>();
        scores = new ArrayList<>();
    }

    public void setSource(Bitmap source) {
        this.source = source;
    }

    public void setResult(Bitmap result) {
        this.result = result;
    }

    public void addDetection(int category, double score) {
        categories.add(MaterialCategory.values()[category]);
        scores.add(score);
    }

    public Bitmap getSource() {
        return source;
    }

    public Bitmap getResult() {
        return result;
    }

    public List<MaterialCategory> getCategories() {
        return categories;
    }

    public List<Double> getScores() {
        return scores;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(source, flags);
        dest.writeParcelable(result, flags);
        dest.writeList(categories);
        dest.writeList(scores);
    }

    protected DetectedObjects(Parcel in) {
        source = in.readParcelable(Bitmap.class.getClassLoader());
        result = in.readParcelable(Bitmap.class.getClassLoader());
        categories = new ArrayList<>();
        scores = new ArrayList<>();
        in.readList(scores, Double.class.getClassLoader());
    }

    public static final Creator<DetectedObjects> CREATOR = new Creator<DetectedObjects>() {
        @Override
        public DetectedObjects createFromParcel(Parcel in) {
            return new DetectedObjects(in);
        }

        @Override
        public DetectedObjects[] newArray(int size) {
            return new DetectedObjects[size];
        }
    };
}
