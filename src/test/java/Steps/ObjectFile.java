package Steps;

import Steps.Intrefaces.IUpload;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

import static Utils.Utils.randomString;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by VatslauX on 20-May-17.
 */
public class ObjectFile implements IUpload {
    static final Logger rootLogger = LogManager.getRootLogger();
    public Boolean isFolder = null;
    public String folderName = null;
    public Boolean isFolderOpened = null;
    public Boolean haveNestedItems = null;

    public Boolean isFile = null; // if true==file, if false==folder
    public String fileName = null;
    public String fileExtension = null;
    public String fileFullName = fileName+"."+fileExtension;

    public Boolean isInBox = null;
    public String linkedProjectName = null;

    public Boolean isZoneAll = null;
    public String ownerTeamInitials = null;
    public String[] collaborators = null;

    public ObjectFile(Builder builder) {
        isFolder = builder.isFolder;
        folderName = builder.folderName;
        isFolderOpened = builder.isFolderOpened;
        haveNestedItems = builder.haveNestedItems;
        isFile = builder.isFile;
        fileName = builder.fileName;
        fileExtension = builder.fileExtension;
        fileFullName = builder.fileFullName;
        isInBox = builder.isInBox;
        linkedProjectName = builder.linkedProjectName;
        isZoneAll = builder.isZoneAll;
        ownerTeamInitials = builder.ownerTeamInitials;
        collaborators = builder.collaborators;
    }
    static public Builder newBuilder() {
        return new Builder();
    }

    @Override
    public ObjectFile uploadFile(SelenideElement input, String fileFullName) {
        this.isFile = true;
        this.fileFullName = fileFullName;
        String relativePath = "src/test/java/UploadFiles/"+ fileFullName;
        String absolutePath = new File(relativePath).getAbsolutePath();
        rootLogger.info(absolutePath);
        sleep(2000);
        input.waitUntil(exist, 20000).sendKeys(absolutePath);
        sleep(3000);
        return this;
    }
    @Override
    public ObjectFile uploadFile(SelenideElement input) {
        this.isFile = true;
        String relativePath = "src/test/java/UploadFiles/"+ this.fileFullName;
        String absolutePath = new File(relativePath).getAbsolutePath();
        rootLogger.info(absolutePath);
        sleep(2000);
        input.waitUntil(exist, 20000).sendKeys(absolutePath);
        sleep(3000);
        return this;
    }
    @Override
    public ObjectFile uploadFile(String fileFullName) {
        this.isFile = true;
        this.fileFullName = fileFullName;
        try {
            Runtime.getRuntime().exec("src/test/java/ScriptsAutoIt/script_runtime.exe"+" "+fileFullName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
    @Override
    public ObjectFile uploadFile() {
        this.isFile = true;
        try {
            Runtime.getRuntime().exec("src/test/java/ScriptsAutoIt/script_runtime.exe"+" "+this.fileFullName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public static final class Builder {
        private Boolean isFolder;
        private String folderName;
        private Boolean isFolderOpened;
        private Boolean haveNestedItems;
        private Boolean isFile;
        private String fileName;
        private String fileExtension;
        private String fileFullName;
        private Boolean isInBox;
        private String linkedProjectName;
        private Boolean isZoneAll;
        private String ownerTeamInitials;
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

        public Builder fileFullName(String fileFullName) {
            this.fileFullName = fileFullName;
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

        public Builder ownerTeamInitials(String ownerTeamInitials) {
            this.ownerTeamInitials = ownerTeamInitials;
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

    public ObjectFile buildFile(){
       ObjectFile file = newBuilder()
               .isFile(true)
               .isFolder(false)
               .fileName("FILE_"+randomString(12))
               .build();
       logFileFields(file);
       return file;
    }
    public enum FileTypes {JPG, ICO, PNG, SVG, PDF,  WORD, EXCEL, ZIP, GOOGLE}
    public ObjectFile buildFile(FileTypes fileType){
        ObjectFile genericFile = null;
        switch(fileType) {
            case JPG:
                genericFile = new ObjectFile(ObjectFile.newBuilder()).buildFile("jpeg", "jpg");
                break;
            case ICO:
                genericFile = new ObjectFile(ObjectFile.newBuilder()).buildFile("icon", "ico");
                break;
            case PNG:
                genericFile = new ObjectFile(ObjectFile.newBuilder()).buildFile("png", "png");
                break;
            case SVG:
                genericFile = new ObjectFile(ObjectFile.newBuilder()).buildFile("svg", "svg");
                break;
            case PDF:
                genericFile = new ObjectFile(ObjectFile.newBuilder()).buildFile("pdf", "pdf");
                break;
            case WORD:
                genericFile = new ObjectFile(ObjectFile.newBuilder()).buildFile("word", "docx");
                break;
            case EXCEL:
                genericFile = new ObjectFile(ObjectFile.newBuilder()).buildFile("excel", "xlsx");
                break;
            case ZIP:
                genericFile = new ObjectFile(ObjectFile.newBuilder()).buildFile("zip", "zip");
                break;
            case GOOGLE:
                genericFile = new ObjectFile(ObjectFile.newBuilder()).buildFile("googledoc", "gdoc");
                break;
        }
        return genericFile;
    }
    public ObjectFile buildFile(String fileName, String fileExtension){
        ObjectFile file = newBuilder()
                .isFile(true)
                .isFolder(false)
                .fileName(fileName)
                .fileFullName(fileName+"."+fileExtension)
                .fileExtension(fileExtension)
                .build();
        logFileFields(file);
        return file;
    }
    public ObjectFile buildFolder(){
        ObjectFile file = newBuilder()
                .isFile(false)
                .isFolder(true)
                .folderName("FOLDER_"+randomString(10))
                .build();
        logFileFields(file);
        return file;
    }
    public ObjectFile buildFolder(String folderName){
        ObjectFile file = newBuilder()
                .isFile(false)
                .isFolder(true)
                .folderName(folderName)
                .build();
        logFileFields(file);
        return file;
    }

    public void logFileFields(ObjectFile file){
        if (file.isFolder==true) {
            rootLogger.info(file.isFolder);
            rootLogger.info(file.folderName);
            rootLogger.info(file.isFolderOpened);
            rootLogger.info(file.haveNestedItems);
        }
        if (file.isFile==true){
            rootLogger.info(file.isFile);
            rootLogger.info(file.fileName);
            rootLogger.info(file.fileExtension);
            rootLogger.info(file.fileFullName);
        }
        rootLogger.info(file.isInBox);
        rootLogger.info(file.linkedProjectName);
        rootLogger.info(file.isZoneAll);
        rootLogger.info(file.ownerTeamInitials);
        rootLogger.info("=====================");
    }


}
