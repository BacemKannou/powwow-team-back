package fr.docapost.powwow.api.entities;


import org.hibernate.annotations.AttributeAccessor;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable, Comparable<BaseEntity>, IEntity, Cloneable {
    private static final long serialVersionUID = -3545803775832286284L;
    @Id
    @GeneratedValue(
            generator = "ID_GENERATOR"
    )
    @Column(
            name = "ID"
    )
    @AttributeAccessor("property")
    private Long id;
    @Version
    @Column(
            name = "VERSION"
    )
    private Integer version;

    public BaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public int hashCode() {
        return this.getId() == null ? super.hashCode() : this.getId().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof BaseEntity)) {
            return false;
        } else {
            BaseEntity other = (BaseEntity)obj;
            if (this.getId() == null) {
                return false;
            } else {
                return this.getId().equals(other.getId());
            }
        }
    }

    public int compareTo(BaseEntity other) {
        return other == null ? 1 : (int)(this.getId() - other.getId());
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
