package example.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import example.exception.TestContextException;
import lombok.var;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static final ThreadLocal<Map<String, Object>> sharedData =
            ThreadLocal.withInitial(HashMap::new);

    private static final String GET_ERROR_MESSAGE =
            "Can't get data value with key %s. Current shared data %s";

    private ScenarioContext() {
    }

    public static ScenarioContext getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final ScenarioContext INSTANCE = new ScenarioContext();
    }

    public Boolean isContains(String key) {
        return sharedData.get().containsKey(key);
    }

    public void setContext(String key, Object value) {
        getInstance().setData(key, value);
    }

    public void setData(String key, Object value) {
        sharedData.get().put(key, value);
    }

    public <T> T getData(String key) {
        if (sharedData.get().containsKey(key)) return (T) sharedData.get().get(key);
        String exceptionMsg = String.format(GET_ERROR_MESSAGE, key, getInstance());
        throw new IllegalAccessError(exceptionMsg);
    }

    public <T> T getContext(String key, boolean allowNullData) {
        Boolean contained = getInstance().isContains(key);
        if (Boolean.TRUE.equals(contained)) return getInstance().getData(key);
        if (allowNullData) return null;
        String exceptionMsg =
                String.format(GET_ERROR_MESSAGE, key, getInstance().getAllData().toString());
        throw new IllegalAccessError(exceptionMsg);
    }

    public <T> T getContext(String key) {
        return getInstance().getData(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getContext(String key, Object defaultValue) {
        try {
            return getInstance().getData(key);
        } catch (IllegalAccessError e) {
            return (T) defaultValue;
        }
    }

    public Map<String, Object> getAllData() {
        Map<String, Object> cloneData = new HashMap<>();
        var gson = getGsonInstance();
        sharedData
                .get()
                .forEach(
                        (key, value) -> {
                            try {
                                if (value == null) {
                                    cloneData.put(key, "");
                                } else {
                                    cloneData.put(
                                            key,
                                            gson.fromJson(
                                                    gson.toJson(value),
                                                    new TypeToken<Object>() {
                                                    }.getType()));
                                }
                            } catch (Exception e) {
                                throw new TestContextException(
                                        String.format("Error in clone data with key %s", key), e);
                            }
                        });
        return cloneData;
    }

    public static Gson getGsonInstance() {
        return (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").disableHtmlEscaping().create();
    }


}
