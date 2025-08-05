# Hướng dẫn cài đặt Car Showroom Management System

## Yêu cầu hệ thống

### Minimum Requirements
- **Java 17** hoặc cao hơn
- **MySQL 8.0** hoặc cao hơn
- **RAM:** 4GB
- **Storage:** 2GB free space
- **OS:** Windows 10+, macOS 10.14+, Linux

### Recommended Requirements
- **Java 17** hoặc cao hơn
- **MySQL 8.0** hoặc cao hơn
- **RAM:** 8GB
- **Storage:** 5GB free space
- **OS:** Windows 11, macOS 12+, Ubuntu 20.04+

## Bước 1: Cài đặt Java

1. Tải Java 17 từ [Oracle](https://www.oracle.com/java/technologies/downloads/) hoặc [OpenJDK](https://adoptium.net/)
2. Cài đặt và thiết lập JAVA_HOME
3. Kiểm tra: `java -version`

## Bước 2: Cài đặt MySQL

1. Tải MySQL 8.0 từ [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
2. Cài đặt MySQL Server
3. Thiết lập root password
4. Tạo database: `CREATE DATABASE car_showroom;`

## Bước 3: Cấu hình Database

1. Import database schema:
   ```bash
   mysql -u root -p < database/car_showroom.sql
   ```

2. Cập nhật file `src/main/resources/properties/database.properties`:
   ```properties
   database.url=jdbc:mysql://localhost:3306/car_showroom
   database.username=root
   database.password=your_password
   database.driver=com.mysql.cj.jdbc.Driver
   ```

## Bước 4: Build và chạy ứng dụng

### Sử dụng Maven
```bash
# Build project
mvn clean install

# Chạy ứng dụng
mvn javafx:run
```

### Sử dụng IDE (IntelliJ IDEA, Eclipse, VS Code)
1. Import project vào IDE
2. Cấu hình Maven dependencies
3. Chạy class `com.showroom.Main`

## Bước 5: Kiểm tra ứng dụng

1. Mở ứng dụng
2. Đăng nhập với tài khoản mẫu:
   - Username: `admin`, Password: `password`
   - Username: `sales1`, Password: `password`
   - Username: `acc1`, Password: `password`

## Troubleshooting

### Lỗi kết nối database
- Kiểm tra MySQL service đang chạy
- Kiểm tra thông tin kết nối trong `database.properties`
- Kiểm tra firewall/port 3306

### Lỗi JavaFX
- Đảm bảo Java 17+ đã cài đặt
- Kiểm tra Maven dependencies
- Chạy với VM options: `--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml`

### Lỗi build Maven
- Xóa thư mục `target/` và build lại
- Kiểm tra internet connection để download dependencies
- Kiểm tra file `pom.xml` có đúng syntax

## Cấu trúc thư mục

```
CarShowroomManagement/
├── src/main/java/com/showroom/      # Source code
│   ├── config/                      # Cấu hình
│   ├── model/                       # Models & Entities
│   ├── dao/                         # Data Access Objects
│   ├── service/                     # Business Logic
│   ├── controller/                  # Controllers
│   ├── util/                        # Utilities
│   └── exception/                   # Exception handling
├── src/main/resources/              # Resources
│   ├── fxml/                        # FXML files
│   ├── css/                         # Stylesheets
│   ├── images/                      # Images & Icons
│   └── properties/                  # Properties files
├── src/test/                        # Test files
├── database/                        # Database files
├── docs/                           # Documentation
├── scripts/                        # Build scripts
├── pom.xml                         # Maven build file
├── README.md                       # Tài liệu hướng dẫn
└── CHANGELOG.md                    # Lịch sử thay đổi
```

## Liên hệ hỗ trợ

Nếu gặp vấn đề, vui lòng:
1. Kiểm tra logs trong console
2. Xem file `CHANGELOG.md` để biết thay đổi mới nhất
3. Tạo issue trên GitHub repository 