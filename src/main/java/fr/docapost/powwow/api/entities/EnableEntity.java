package fr.docapost.powwow.api.entities;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EnableEntity extends BaseEntity implements IEnable {
    private static final long serialVersionUID = 4874677036141567695L;
    @Column(
            name = "DISABLED",
            nullable = false
    )
    private boolean disabled;

    public EnableEntity() {
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isActive() {
        return !this.disabled;
    }
}
