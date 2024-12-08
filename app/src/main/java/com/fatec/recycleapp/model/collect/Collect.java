package com.fatec.recycleapp.model.collect;

import com.fatec.recycleapp.model.collect.attributes.CancelReason;
import com.fatec.recycleapp.model.collect.attributes.CollectMaterial;
import com.fatec.recycleapp.model.collect.attributes.CollectRating;
import com.fatec.recycleapp.model.collect.attributes.CollectStatus;
import com.fatec.recycleapp.model.user.TrashHandler;
import com.fatec.recycleapp.model.user.TrashProducer;
import com.fatec.recycleapp.model.user.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Collect {
    private Integer id;
    private User producer;
    private User handler;
    private LocalDateTime requestDate;
    private LocalDateTime scheduledDate;
    private CollectStatus status;
    private CollectRating producerRating;
    private CollectRating handlerRating;
    private String instructions;
    private CancelReason cancelReason;
    private List<CollectMaterial> materials;

    public Collect() {

    }

    public Duration remaining() {
        return Duration.between(LocalDateTime.now(), scheduledDate);
    }

    public boolean expired() {
        return LocalDateTime.now().isAfter(scheduledDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getProducer() {
        return producer;
    }

    public void setProducer(User producer) {
        this.producer = producer;
    }

    public User getHandler() {
        return handler;
    }

    public void setHandler(User handler) {
        this.handler = handler;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public List<CollectMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<CollectMaterial> materials) {
        this.materials = materials;
    }

    public CollectStatus getStatus() {
        return status;
    }

    public void setStatus(CollectStatus status) {
        this.status = status;
    }

    public CollectRating getProducerRating() {
        return producerRating;
    }

    public CollectRating getHandlerRating() {
        return handlerRating;
    }

    public void setHandlerRating(CollectRating handlerRating) {
        this.handlerRating = handlerRating;
    }

    public void setProducerRating(CollectRating producerRating) {
        this.producerRating = producerRating;
    }

    public CancelReason getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(CancelReason cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
