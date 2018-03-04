package com.spider.dao;

import com.spider.dao.datasource.unpooled.UnpooledDataSource;
import com.spider.entity.FollowNexus;
import com.spider.entity.UserBase;
import com.spider.entity.UserInfo;
import com.spider.tool.Config;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/12/5.
 */
public class SaveDaoImp implements SaveDaoInterface {

    private static final Config CONFIG = Config.INSTANCES();
    private static final UnpooledDataSource UNPOOLED_DATA_SOURCE = new UnpooledDataSource("com.mysql.jdbc.Driver", (CONFIG.getDb_url().concat(CONFIG.getDb_name())), CONFIG.getDb_user_name(), CONFIG.getDb_user_pass());
    private static final Object look = new Object();
    private static final String SQL_INIT = "select * from users where isinit=0 and token='%s'";
    private static final String SQL_INSERT_USERINFO = "INSERT INTO users_info(weibo,name,address,education,company,job,headline,user_id,answer,question,article,favorite,agree,thanked,following,followers,topic,columns,sex,token,index_url)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_USERBASE = "insert INTO users (token,index_url,from_id,from_token) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE_USERBASE = "update users set isinit=? where token=?";
    private static final String SQL_INSERT_FOLLOWER = "insert INTO follower (user_token,user_token_follower,user_name,follower_name) VALUES (?,?,?,?)";
    private static final String SQL_SELECT_USERBASE = "SELECT id,token,from_id from_id,from_token FROM users WHERE isinit = '0'  ORDER BY id LIMIT 10000";
    private static final String SQL_ISEXIST_USERBASE = "select * from users where token=?";
    private static final String SQL_UPDATE_USERBASE_PARSER = "UPDATE users set isparser=? WHERE  token=?";
    private static final String SQL_GET_USERBASE_PARSER = "select * from users where isparser='0' limit ".concat(CONFIG.getUser_info_size());
    private static final String IS_INIT_DB = "SELECT w.TABLE_NAME FROM information_schema.TABLES w WHERE w.table_name =? and w.TABLE_SCHEMA=?";
    private Random r = new Random();

    private SaveDaoImp() {
    }

    public static SaveDaoInterface getInstance() {
        return (SaveDaoInterface) Proxy.
                newProxyInstance(SaveDaoImp.class.getClassLoader(),
                        new Class[]{SaveDaoInterface.class}, new Hook(new SaveDaoImp()));
    }

    @Override
    public long UpdateParserInfo(List<UserBase> info) throws Exception {
        long star = System.currentTimeMillis();
        synchronized (look) {
            Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_USERBASE_PARSER);
            int i = 0;
            for (UserBase token : info) {
                ps.setString(1, "1");
                ps.setString(2, token.getToken());
                ps.addBatch();
                if (i++ % 1000 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                    connection.commit();
                }
            }
            ps.executeBatch();
            ps.clearBatch();
            connection.commit();
            close(ps, connection);
        }
        return (System.currentTimeMillis() - star) / 1000L;
    }

    @Override
    public List<UserBase> getParserInfoUserBase() throws Exception {
        Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQL_GET_USERBASE_PARSER);
        List<UserBase> userBase = getUserBase(ps.executeQuery());
        close(ps, connection);
        return userBase;
    }


    @Override
    public void UpdateBase(List<UserBase> userBases) throws Exception {
        synchronized (look) {
            Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_USERBASE);
            for (UserBase u : userBases) {
                ps.setString(1, "1");
                ps.setString(2, u.getToken());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
            close(ps);
        }
    }

    @Override
    public void SaveForUser(List<UserInfo> info) throws Exception {
        Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USERINFO);
        connection.setAutoCommit(false);
        int i = 1;
        for (UserInfo u : info) {
            ps.setString(i++, u.getWeibo());
            ps.setString(i++, u.getName());
            ps.setString(i++, u.getAddress());
            ps.setString(i++, u.getEducation());
            ps.setString(i++, u.getCompany());
            ps.setString(i++, u.getJob());
            ps.setString(i++, u.getHeadline());
            ps.setString(i++, u.getId());
            ps.setString(i++, u.getAnswer());
            ps.setString(i++, u.getQuestion());
            ps.setString(i++, u.getArticle());
            ps.setString(i++, u.getFavorite());
            ps.setString(i++, u.getAgree());
            ps.setString(i++, u.getThanked());
            ps.setString(i++, u.getFollowing());
            ps.setString(i++, u.getFollowers());
            ps.setString(i++, u.getTopic());
            ps.setString(i++, u.getColumns());
            ps.setString(i++, u.getSex());
            ps.setString(i++, u.getToken());
            ps.setString(i++, u.getIndex_url());
            i = 1;
            ps.addBatch();

        }
        ps.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        close(ps, connection);


    }

    @Override
    public void SaveForFollow(List<FollowNexus> followNexuses) throws Exception {
        Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT_FOLLOWER);
        connection.setAutoCommit(false);
        for (FollowNexus f : followNexuses) {
            ps.setString(1, f.getToken_id());
            ps.setString(2, f.getToken_flower());
            ps.setString(3, f.getName());
            ps.setString(4, f.getFlower_name());
            ps.addBatch();
        }
        ps.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        close(ps, connection);

    }

    @Override
    public List<UserBase> iniTemp(int size) throws Exception {
        Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from USERS limit " + size);
        List<UserBase> objects = new ArrayList<>(size);
        objects.addAll(getUserBase(preparedStatement.executeQuery()));
        close(preparedStatement, connection);
        return objects;

    }

    @Override
    public void SaveForUserBase(List<UserBase> userBases) throws Exception {
        synchronized (look) {
            Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
            //token,index_url,from_id,from_token
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USERBASE);
            for (UserBase f : userBases) {
                ps.setString(1, f.getToken());
                ps.setString(2, "https://www.zhihu.com/people/".concat(f.getToken()));
                ps.setString(3, f.getFrom_id());
                ps.setString(4, f.getFrom_token());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
            close(ps);
        }
    }

    @Override
    public List<UserBase> getNewForUserBase() throws Exception {
        Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
        return getUserBase(connection.prepareStatement(SQL_SELECT_USERBASE).executeQuery());

    }

    @Override
    public List<UserBase> Init(UserBase userBase) throws Exception {
        List<UserBase> userBases = new ArrayList<>();
        Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement(String.format(SQL_INIT, userBase.getToken()));
        ResultSet res = ps.executeQuery();
        userBases.addAll(getUserBase(ps.executeQuery()));
        if (userBases.size() == 0) {
            ps = connection.prepareStatement("select * FROM  users WHERE  token='" + userBase.getToken() + "'");
            if (getUserBase(ps.executeQuery()).size() != 0) {
                userBases.addAll(getNewForUserBase());
                close(ps, connection);
                return userBases;
            }
            ps = connection.prepareStatement(SQL_INSERT_USERBASE);
            ps.setString(1, userBase.getToken());
            ps.setString(2, "https://www.zhihu.com/people/".concat(userBase.getToken()));
            ps.setString(3, "0");
            ps.setString(4, "0");
            ps.execute();
            connection.commit();
            ps = connection.prepareStatement(String.format(SQL_INIT, userBase.getToken()));
            userBases.addAll(getUserBase(ps.executeQuery()));
        }
        connection.setAutoCommit(true);
        close(ps, connection);
        this.UpdateBase(userBases);
        return userBases;
    }


    public boolean isExist(UserBase userBase) throws Exception {

        Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQL_ISEXIST_USERBASE);
        ps.setString(1, userBase.getToken());
        ResultSet resultSet = ps.executeQuery();
        Boolean b = resultSet.next();
        close(resultSet, ps, connection);
        Thread.sleep(500);

        return b;
    }

    private List<UserBase> getUserBase(ResultSet res) throws Exception {
        List<UserBase> userBases = new ArrayList<>();
        UserBase userBase;
        while (res.next()) {
            userBase = new UserBase();
            userBase.setId(res.getString("id"));
            userBase.setToken(res.getString("token"));
            userBase.setFrom_token(res.getString("from_token"));
            userBase.setFrom_token(res.getString("from_token"));
            userBases.add(userBase);
        }
        close(res);
        return userBases;
    }

    @Override
    public void InitDb() throws Exception {
        Connection connection = UNPOOLED_DATA_SOURCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(IS_INIT_DB);
        preparedStatement.setString(1, "users");
        preparedStatement.setString(2, CONFIG.INSTANCES().getDb_name());
        if (preparedStatement.executeQuery().next()) {
            return;
        }

        //TODO Create Table


    }


    private void close(AutoCloseable... con) {
        try {
            for (AutoCloseable a : con) {
                if (a != null) {
                    a.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
