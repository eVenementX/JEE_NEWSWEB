package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import util.ConnectionProvider;


public class UserDAOImpl implements UserDAO {

    private static final String CREATE_USER =
            "INSERT INTO user(username, email, password, is_active) VALUES(:username, :email, :password, :is_active);";
    private static final String READ_USER_BY_ID = "SELECT user_id, username, email, password, is_active FROM user where user_id = :id; ";
    private static final String READ_USER_BY_USERNAME ="SELECT user_id, username, email, password, is_active FROM user WHERE username = :username";

    private NamedParameterJdbcTemplate template;

    public UserDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
    }

    @Override
    public User create(User user) {
        User resultUser = user;

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        int update = template.update(CREATE_USER, paramSource, holder);
        if(update > 0) {
            resultUser.setId((Long)holder.getKey());
            setPrivigiles(resultUser);
        }
        return resultUser;
    }

    private void setPrivigiles(User user) {
        final String userRoleQuery = "INSERT INTO user_role(username) VALUES(:username)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        template.update(userRoleQuery, paramSource);
    }

    @Override
    public User read(Long primaryKey) {
        User resultUser;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id",primaryKey);
        resultUser = template.queryForObject(READ_USER_BY_ID,sqlParameterSource,new UserRowMapper());
        return resultUser;
    }

    @Override
    public boolean update(User updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        User resultUser;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("username",username);
        resultUser = template.queryForObject(READ_USER_BY_USERNAME,sqlParameterSource, new UserRowMapper());
        return resultUser;
    }
    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            return user;
        }
    }
}