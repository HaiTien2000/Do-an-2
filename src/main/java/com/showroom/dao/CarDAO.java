package com.showroom.dao;

import com.showroom.model.Car;
import com.showroom.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object cho xe ô tô
 */
public class CarDAO {
    
    /**
     * Lấy tất cả xe
     */
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT c.*, b.name as brand_name FROM cars c " +
                    "LEFT JOIN brands b ON c.brand_id = b.id " +
                    "ORDER BY c.created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Car car = mapResultSetToCar(rs);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cars;
    }
    
    /**
     * Lấy xe theo ID
     */
    public Optional<Car> getCarById(int id) {
        String sql = "SELECT c.*, b.name as brand_name FROM cars c " +
                    "LEFT JOIN brands b ON c.brand_id = b.id " +
                    "WHERE c.id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Car car = mapResultSetToCar(rs);
                return Optional.of(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return Optional.empty();
    }
    
    /**
     * Thêm xe mới
     */
    public boolean addCar(Car car) {
        String sql = "INSERT INTO cars (brand_id, model, year, color, engine_type, " +
                    "transmission, fuel_type, mileage, price, status, image_url, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, car.getBrandId());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setString(4, car.getColor());
            stmt.setString(5, car.getEngineType());
            stmt.setString(6, car.getTransmission());
            stmt.setString(7, car.getFuelType());
            stmt.setBigDecimal(8, car.getMileage());
            stmt.setBigDecimal(9, car.getPrice());
            stmt.setString(10, car.getStatus());
            stmt.setString(11, car.getImageUrl());
            stmt.setString(12, car.getDescription());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Cập nhật thông tin xe
     */
    public boolean updateCar(Car car) {
        String sql = "UPDATE cars SET brand_id = ?, model = ?, year = ?, color = ?, " +
                    "engine_type = ?, transmission = ?, fuel_type = ?, mileage = ?, " +
                    "price = ?, status = ?, image_url = ?, description = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, car.getBrandId());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setString(4, car.getColor());
            stmt.setString(5, car.getEngineType());
            stmt.setString(6, car.getTransmission());
            stmt.setString(7, car.getFuelType());
            stmt.setBigDecimal(8, car.getMileage());
            stmt.setBigDecimal(9, car.getPrice());
            stmt.setString(10, car.getStatus());
            stmt.setString(11, car.getImageUrl());
            stmt.setString(12, car.getDescription());
            stmt.setInt(13, car.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Xóa xe
     */
    public boolean deleteCar(int id) {
        String sql = "DELETE FROM cars WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Tìm kiếm xe theo model
     */
    public List<Car> searchCarsByModel(String model) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT c.*, b.name as brand_name FROM cars c " +
                    "LEFT JOIN brands b ON c.brand_id = b.id " +
                    "WHERE c.model LIKE ? ORDER BY c.created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + model + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Car car = mapResultSetToCar(rs);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cars;
    }
    
    /**
     * Lấy xe theo trạng thái
     */
    public List<Car> getCarsByStatus(String status) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT c.*, b.name as brand_name FROM cars c " +
                    "LEFT JOIN brands b ON c.brand_id = b.id " +
                    "WHERE c.status = ? ORDER BY c.created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Car car = mapResultSetToCar(rs);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cars;
    }
    
    /**
     * Map ResultSet thành Car object
     */
    private Car mapResultSetToCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setBrandId(rs.getInt("brand_id"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getInt("year"));
        car.setColor(rs.getString("color"));
        car.setEngineType(rs.getString("engine_type"));
        car.setTransmission(rs.getString("transmission"));
        car.setFuelType(rs.getString("fuel_type"));
        car.setMileage(rs.getBigDecimal("mileage"));
        car.setPrice(rs.getBigDecimal("price"));
        car.setStatus(rs.getString("status"));
        car.setImageUrl(rs.getString("image_url"));
        car.setDescription(rs.getString("description"));
        car.setBrandName(rs.getString("brand_name"));
        
        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            car.setCreatedAt(createdAt.toLocalDateTime());
        }
        
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (updatedAt != null) {
            car.setUpdatedAt(updatedAt.toLocalDateTime());
        }
        
        return car;
    }
} 