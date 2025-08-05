package com.showroom.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class để quản lý kết nối database
 */
public class DatabaseConnection {
    private static HikariDataSource dataSource;
    
    static {
        initializeDataSource();
    }
    
    /**
     * Khởi tạo DataSource
     */
    private static void initializeDataSource() {
        try {
            Properties props = loadDatabaseProperties();
            
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("database.url"));
            config.setUsername(props.getProperty("database.username"));
            config.setPassword(props.getProperty("database.password"));
            config.setDriverClassName(props.getProperty("database.driver"));
            
            // Pool configuration
            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("database.pool.size", "10")));
            config.setMinimumIdle(5);
            config.setIdleTimeout(300000); // 5 minutes
            config.setMaxLifetime(Long.parseLong(props.getProperty("database.pool.max.lifetime", "1800000"))); // 30 minutes
            config.setConnectionTestQuery("SELECT 1");
            
            dataSource = new HikariDataSource(config);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể khởi tạo kết nối database", e);
        }
    }
    
    /**
     * Load properties từ file database.properties
     */
    private static Properties loadDatabaseProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream("properties/database.properties")) {
            if (input == null) {
                throw new IOException("Không tìm thấy file database.properties");
            }
            props.load(input);
        }
        return props;
    }
    
    /**
     * Lấy connection từ pool
     */
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            initializeDataSource();
        }
        return dataSource.getConnection();
    }
    
    /**
     * Đóng DataSource
     */
    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
    
    /**
     * Kiểm tra kết nối
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
} 