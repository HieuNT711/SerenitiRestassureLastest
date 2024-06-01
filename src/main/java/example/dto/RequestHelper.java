package example.dto;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.*;

final class RequestHelper {
    public static Map<String, Object> getDefaultRequestParams(Request instance) {
        try {
            Map<String, Object> requestParams = new HashMap<>();
            for (Field field : getAllFieldsHaveValue(instance)) {
                SerializedName serializedName = field.getAnnotation(SerializedName.class);
                requestParams.put(serializedName.value(), field.get(instance));
            }
            return requestParams;
        } catch (IllegalArgumentException | IllegalAccessException e) {

        }
        return null;
    }

    public static List<Field> getAllFieldsHaveValue(Request instance)
            throws IllegalArgumentException, IllegalAccessException {
        List<Class<?>> classes = getAllClasses(instance);
        List<Field> allFields = getAllFields(classes);
        List<Field> allFieldsWithAccessible = new ArrayList<>();
        for (Field field : allFields) {
            field.setAccessible(true);
            SerializedName serializedName = field.getAnnotation(SerializedName.class);
            if (field.get(instance) != null && serializedName != null)
                allFieldsWithAccessible.add(field);
        }
        return allFieldsWithAccessible;
    }

    public static List<Class<?>> getAllClasses(Object clazz) {
        List<Class<?>> classList = new ArrayList();
        Class<?> currentClazz = clazz.getClass();
        Class<?> superclass = currentClazz.getSuperclass();
        classList.add(currentClazz);
        if (isObjectClass(superclass)) {
            return classList;
        } else {
            classList.add(superclass);
            addParentClass(superclass, classList);
            return classList;
        }
    }

    private static void addParentClass(Class<?> superclass, List<Class<?>> classList) {
        while(true) {
            if (Boolean.FALSE.equals(isObjectClass(superclass))) {
                superclass = superclass.getSuperclass();
                if (!isObjectClass(superclass)) {
                    classList.add(superclass);
                    continue;
                }
            }

            return;
        }
    }

    private static boolean isObjectClass(Class<?> classType) {
        return classType.getSimpleName().equalsIgnoreCase("Object");
    }



    public static List<Field> getAllFields(List<Class<?>> classes) {
        List<Field> fields = new ArrayList();
        Iterator var2 = classes.iterator();

        while(var2.hasNext()) {
            Class<?> classType = (Class)var2.next();
            Field[] allFields = classType.getDeclaredFields();
            Field[] var5 = allFields;
            int var6 = allFields.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Field field = var5[var7];
                SerializedName serializedName = (SerializedName)field.getAnnotation(SerializedName.class);
                if (serializedName != null) {
                    field.setAccessible(true);
                    fields.add(field);
                    fields.addAll(Arrays.asList(allFields));
                }
            }
        }
        return fields;


    }

}
