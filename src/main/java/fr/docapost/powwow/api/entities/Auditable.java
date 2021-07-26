package fr.docapost.powwow.api.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class Auditable implements Serializable {
    private static final long serialVersionUID = 5322674309593123145L;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "CREATED",
            nullable = false,
            updatable = false
    )
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "UPDATED"
    )
    private Date updated;
    @ManyToOne(
            optional = false,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "CREATOR_ID",
            updatable = false
    )
    private User creator;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "UPDATER_ID"
    )
    private User updater;

    public Auditable() {
    }

    public Auditable(User creator) {
        this.creator = creator;
        this.created = new Date();
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public User getCreator() {
        return this.creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getUpdater() {
        return this.updater;
    }

    public void setUpdater(User updater) {
        this.updater = updater;
    }

    public Date getLastModified() {
        return this.updated != null ? this.updated : this.created;
    }

    public User getLastUser() {
        return this.updater != null ? this.updater : this.creator;
    }

    public void updateWith(User currentUser) {
        this.updated = new Date();
        this.updater = currentUser;
    }
}
