package example;

public class DataCommonAppRestService {
    private DataCommonAppRestService() {
        throw new IllegalStateException("DataCommonAppRestService");
    }

    public static final String BASE_URL_SERVICE_APP = PropertiesManager.getEnvironmentSpecFromProperty("service.appUrl");


    public static class Module {
        public static final String CUSTOMER_MANAGEMENT_MS = "/customermanagementms/";
        public static final String COMMON = "/common/";
    }


}
