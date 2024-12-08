package com.fatec.recycleapp.model.collect.attributes;

public class CollectRating {
    private Integer collectId;
    private Integer rating;
    private String comment;

    public CollectRating() {

    }

    public CollectRating(Integer collectId, Integer rating, String comment) {
        this.collectId = collectId;
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
