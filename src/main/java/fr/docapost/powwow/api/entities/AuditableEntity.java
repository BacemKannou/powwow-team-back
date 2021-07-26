package fr.docapost.powwow.api.entities;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AuditableEntity extends EnableEntity implements IAuditable {
    private static final long serialVersionUID = -5225634530609871236L;
    @Embedded
    private Auditable auditable;

    public AuditableEntity() {
    }

    public AuditableEntity(Auditable auditable) {
        this.auditable = auditable;
    }

    public Auditable getAuditable() {
        return this.auditable;
    }

    public void setAuditable(Auditable auditable) {
        this.auditable = auditable;
    }

    public void updateAudit(User u) {
        if (this.auditable == null) {
            this.auditable = new Auditable(u);
        } else {
            this.auditable.updateWith(u);
        }

    }

    public int compareTo(BaseEntity other) {
        return this.getId() != null && other.getId() != null ? this.getId().compareTo(other.getId()) : this.getAuditable().getCreated().compareTo(((AuditableEntity) other).getAuditable().getCreated());
    }
}
