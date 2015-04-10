import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Layoric on 2/04/2015.
 */
public class ServiceStackMetadata {

    @Nullable
    @SerializedName("version")
    public Integer lowerCaseVersion;

    @Nullable
    @SerializedName("Version")
    public Integer upperCaseVersion;

    @Nullable
    @SerializedName("Config")
    public MetadataConfig upperCaseConfig;

    @Nullable
    @SerializedName("config")
    public MetadataConfig lowerCaseConfig;

    public ServiceStackMetadata() {

    }

    public MetadataConfig getConfig() {
        return this.upperCaseConfig == null ?
                this.lowerCaseConfig : this.upperCaseConfig;
    }

    public Integer getVersion() {
        return this.lowerCaseVersion == null ?
                this.upperCaseVersion : this.lowerCaseVersion;
    }

    public class MetadataConfig {
        @SerializedName("baseUrl")
        public String lowerCaseBaseUrl;

        @SerializedName("BaseUrl")
        public String upperCaseBaseUrl;

        public String getBaseUrl() {
            return this.lowerCaseBaseUrl == null ?
                    this.upperCaseBaseUrl : this.lowerCaseBaseUrl;
        }
    }
}
