/**
 * Created by Layoric on 2/04/2015.
 */
public class ServiceStackMetadata {

    public Integer Version;
    public MetadataConfig Config;

    public ServiceStackMetadata() {

    }

    public ServiceStackMetadata(int version) {
        this.Version = version;
    }

    public Integer getVersion() {
        return Version;
    }

    public void setVersion(Integer version) {
        this.Version = version;
    }

    public MetadataConfig getConfig() {
        return Config;
    }

    public void setConfig(MetadataConfig config) {
        this.Config = config;
    }

    public class MetadataConfig {
        public String BaseUrl;

        public MetadataConfig(String baseUrl) {
            this.BaseUrl = baseUrl;
        }

        public String getBaseUrl() {
            return this.BaseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.BaseUrl = baseUrl;
        }
    }
}
