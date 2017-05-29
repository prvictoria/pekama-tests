package Steps;

/**
 * Created by VatslauX on 29-May-17.
 */
public class ObjectZone implements ISelectZone{
    public Boolean zoneIsAll = false;
    public String zoneOwner = null;
    public String[] zoneCollaborators = null;
    public String zoneCollaborator = null;
    public final String zoneAll = "All";

    public ObjectZone(Builder builder) {
        zoneIsAll = builder.zoneIsAll;
        zoneOwner = builder.zoneOwner;
        zoneCollaborators = builder.zoneCollaborators;
        zoneCollaborator = builder.zoneCollaborator;
    }
    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public ObjectZone createZone(Boolean isAllZone) {

        return null;
    }

    @Override
    public ObjectZone editAllZone() {
        return null;
    }

    @Override
    public ObjectZone validateZone() {
        return null;
    }

    @Override
    public ObjectZone validateTeamsQtyInZone() {
        return null;
    }

    public static final class Builder {
        private Boolean zoneIsAll;
        private String zoneOwner;
        private String[] zoneCollaborators;
        private String zoneCollaborator;

        private Builder() {
        }

        public Builder zoneIsAll(Boolean zoneIsAll) {
            this.zoneIsAll = zoneIsAll;
            return this;
        }

        public Builder zoneOwner(String zoneOwner) {
            this.zoneOwner = zoneOwner;
            return this;
        }

        public Builder zoneCollaborators(String[] zoneCollaborators) {
            this.zoneCollaborators = zoneCollaborators;
            return this;
        }

        public Builder zoneCollaborator(String zoneCollaborator) {
            this.zoneCollaborator = zoneCollaborator;
            return this;
        }

        public ObjectZone build() {
            return new ObjectZone(this);
        }
    }
}
