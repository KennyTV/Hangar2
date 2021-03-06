package me.minidigger.hangar.db.model;


import java.time.OffsetDateTime;

public class ProjectVersionVisibilityChangesTable {

    private long id;
    private OffsetDateTime createdAt;
    private long createdBy;
    private long versionId;
    private String comment;
    private OffsetDateTime resolvedAt;
    private long resolvedBy;
    private long visibility;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }


    public long getVersionId() {
        return versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public OffsetDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(OffsetDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }


    public long getResolvedBy() {
        return resolvedBy;
    }

    public void setResolvedBy(long resolvedBy) {
        this.resolvedBy = resolvedBy;
    }


    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

}
