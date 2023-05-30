package DataAccess;



import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





/**
 * @author sergi
 *
 *clasa generica care are un parametru generic si contine toate metodele pentru insert update si delete in baza de date
 *
 * @param <T>
 */
public class GenericDAO<T> {
    private Connection connection;

    public GenericDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * creez un obiect si il inserez in tabela
     * @param object
     * @throws SQLException
     */
    public void createObject(T object) throws SQLException {
        String tableName = object.getClass().getSimpleName();
        Field[] fields = object.getClass().getDeclaredFields();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO ").append(tableName).append(" (");

        for (Field field : fields) {
            queryBuilder.append(field.getName()).append(", ");
        }

        queryBuilder.setLength(queryBuilder.length() - 2); 
        queryBuilder.append(") VALUES (");

        for (int i = 0; i < fields.length; i++) {
            queryBuilder.append("?, ");
        }

        queryBuilder.setLength(queryBuilder.length() - 2); 
        queryBuilder.append(")");

        String query = queryBuilder.toString();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int parameterIndex = 1;
            for (Field field : fields) {
                String getterMethodName = "get" + capitalize(field.getName());
                Method getterMethod = object.getClass().getMethod(getterMethodName);
                Object value = getterMethod.invoke(object);
                statement.setObject(parameterIndex, value);
                parameterIndex++;
            }
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * editez un anumit field 
     * @param object
     * @throws SQLException
     */
    public void editObject(T object) throws SQLException {
        String tableName = object.getClass().getSimpleName();
        Field[] fields = object.getClass().getDeclaredFields();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE ").append(tableName).append(" SET ");

        for (Field field : fields) {
            queryBuilder.append(field.getName()).append(" = ?, ");
        }

        queryBuilder.setLength(queryBuilder.length() - 2); 
        queryBuilder.append(" WHERE id = ?");

        String query = queryBuilder.toString();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int parameterIndex = 1;
            for (Field field : fields) {
                String getterMethodName = "get" + capitalize(field.getName());
                Method getterMethod = object.getClass().getMethod(getterMethodName);
                Object value = getterMethod.invoke(object);
                statement.setObject(parameterIndex, value);
                parameterIndex++;
            }

            String idGetterMethodName = "getId";
            Method idGetterMethod = object.getClass().getMethod(idGetterMethodName);
            Object idValue = idGetterMethod.invoke(object);
            statement.setObject(parameterIndex, idValue);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sterge un obiect
     * @param object
     * @throws SQLException
     */
    public void deleteObject(T object) throws SQLException {
        String tableName = object.getClass().getSimpleName();
        String idGetterMethodName = "getId";
        try {
            Method idGetterMethod = object.getClass().getMethod(idGetterMethodName);
            Object idValue = idGetterMethod.invoke(object);
            if (idValue != null) {
                String query = "DELETE FROM " + tableName + " WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setObject(1, idValue);
                    statement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * gaseste un obiect in baza de date
     * @param objectType
     * @param id
     * @return
     * @throws SQLException
     */
    public T findObject(Class<T> objectType, int id) throws SQLException {
        String tableName = objectType.getSimpleName();
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    T object = objectType.getDeclaredConstructor().newInstance();
                    Field[] fields = objectType.getDeclaredFields();
                    for (Field field : fields) {
                        String fieldName = field.getName();
                        String setterMethodName = "set" + capitalize(fieldName);
                        Method setterMethod = objectType.getMethod(setterMethodName, field.getType());
                        Object value = resultSet.getObject(fieldName);
                        setterMethod.invoke(object, value);
                    }
                    return object;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * fragmenteaza
     * @param str
     * @return
     */
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

