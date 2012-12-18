package eu.ibacz.example.simpleshop.backend.entity;

import java.io.Serializable;

/**
 *
 * @author Rastislav Papp
 */
public abstract class BaseEntity<T> implements Serializable {

    public abstract T getId();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if (this.getId() != other.getId() && (this.getId() == null || !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id = " + String.valueOf(getId()) + "]";
    }

}
