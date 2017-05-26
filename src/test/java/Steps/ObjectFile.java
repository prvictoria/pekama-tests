package Steps;

/**
 * Created by VatslauX on 20-May-17.
 */
public class ObjectFile {
    public Boolean isFolder = null;
    public String folderName = null;
    public Boolean isFolderOpened = null;
    public Boolean haveNestedItems = null;

    public Boolean isFile = null; // if true==file, if false==folder
    public String fileName = null;
    public String fileExtension = null;

    public Boolean isInBox = null;
    public String linkedProjectName = null;

    public Boolean isZoneAll = null;
    public String ownerTeam = null;
    public String[] collaborators = null;

    private ObjectFile(Builder builder) {
        isFolder = builder.isFolder;
        folderName = builder.folderName;
        isFolderOpened = builder.isFolderOpened;
        haveNestedItems = builder.haveNestedItems;
        isFile = builder.isFile;
        fileName = builder.fileName;
        fileExtension = builder.fileExtension;
        isInBox = builder.isInBox;
        linkedProjectName = builder.linkedProjectName;
        isZoneAll = builder.isZoneAll;
        ownerTeam = builder.ownerTeam;
        collaborators = builder.collaborators;
    }

    static public Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Boolean isFolder;
        private String folderName;
        private Boolean isFolderOpened;
        private Boolean haveNestedItems;
        private Boolean isFile;
        private String fileName;
        private String fileExtension;
        private Boolean isInBox;
        private String linkedProjectName;
        private Boolean isZoneAll;
        private String ownerTeam;
        private String[] collaborators;

        private Builder() {
        }

        public Builder isFolder(Boolean isFolder) {
            this.isFolder = isFolder;
            return this;
        }

        public Builder folderName(String folderName) {
            this.folderName = folderName;
            return this;
        }

        public Builder isFolderOpened(Boolean isFolderOpened) {
            this.isFolderOpened = isFolderOpened;
            return this;
        }

        public Builder haveNestedItems(Boolean haveNestedItems) {
            this.haveNestedItems = haveNestedItems;
            return this;
        }

        public Builder isFile(Boolean isFile) {
            this.isFile = isFile;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder fileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
            return this;
        }

        public Builder isInBox(Boolean isInBox) {
            this.isInBox = isInBox;
            return this;
        }

        public Builder linkedProjectName(String linkedProjectName) {
            this.linkedProjectName = linkedProjectName;
            return this;
        }

        public Builder isZoneAll(Boolean isZoneAll) {
            this.isZoneAll = isZoneAll;
            return this;
        }

        public Builder ownerTeam(String ownerTeam) {
            this.ownerTeam = ownerTeam;
            return this;
        }

        public Builder collaborators(String[] collaborators) {
            this.collaborators = collaborators;
            return this;
        }

        public ObjectFile build() {
            return new ObjectFile(this);
        }
    }
}
