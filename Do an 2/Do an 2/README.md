# 🚗 Car Showroom Management System

Hệ thống quản lý showroom ô tô phát triển bằng JavaFX, kiến trúc MVC.

## 🚀 Tính năng chính

- Quản lý xe (thêm, sửa, xóa, tìm kiếm, hình ảnh, thông tin chi tiết)
- Quản lý khách hàng (thông tin, lịch sử mua xe, lịch lái thử)
- Quản lý nhân viên (phân quyền: Admin, Sales, Kế toán, ...)
- Quản lý hóa đơn bán xe, xuất báo cáo doanh thu
- Quản lý hãng xe, dòng xe
- Quản lý lịch lái thử xe
- Báo cáo, thống kê (doanh thu, xe bán chạy, ...)

## 🛠️ Công nghệ sử dụng

- Java 17
- JavaFX 17
- MySQL 8.0
- Maven
- JUnit 5, Mockito
- Logback, Jackson, Apache POI, iText 7

## 📁 Cấu trúc project

```
CarShowroomManagement/
├── src/main/java/com/showroom/      # Source code chính
│   ├── config/                      # Cấu hình
│   ├── model/                       # Models & Entities (Xe, Khách hàng, Nhân viên, Hóa đơn, ...)
│   ├── dao/                         # Data Access Objects
│   ├── service/                     # Business Logic
│   ├── controller/                  # Controllers
│   ├── util/                        # Utilities
│   └── exception/                   # Exception handling
├── src/main/resources/              # Resources
│   ├── fxml/                        # FXML files (giao diện)
│   ├── css/                         # Stylesheets
│   ├── images/                      # Hình ảnh xe, icon
│   └── properties/                  # Properties files
├── src/test/                        # Test files
├── database/                        # Database files (car_showroom.sql)
├── docs/                            # Documentation
├── scripts/                         # Build scripts
├── pom.xml                          # Maven build file
├── README.md                        # Tài liệu hướng dẫn
└── CHANGELOG.md                     # Lịch sử thay đổi
```

## 🔧 Cấu hình

### Database Configuration

```
# config/database.properties
database.url=jdbc:mysql://localhost:3306/car_showroom
database.username=root
database.password=your_password
database.driver=com.mysql.cj.jdbc.Driver
```

### Application Configuration

```
# config/application.properties
app.name=Car Showroom Management System
app.version=1.0.0
app.language=vi
app.currency=VND
```

## 👥 Tài khoản mẫu

| Username | Password | Role    | Tên           |
|----------|----------|---------|---------------|
| leader   | password | Admin   | Phạm Thanh Phong |
| account  | password | Accountant | Nguyễn Tiến Hải |
| sale1    | password | Sales   | Nguyễn Huy Hưng |
| sale2    | password | Sales   | Nguyễn Quốc Tuấn |

## 🚀 Cài đặt và chạy

1. Clone repository
2. Import database
3. Cấu hình database
4. Build project với Maven
5. Chạy ứng dụng