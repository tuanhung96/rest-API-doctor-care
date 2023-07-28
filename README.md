# rest-API-doctor-care

Dự án là một rest API hỗ trợ đặt lịch khám bệnh giúp kết nối bệnh nhân với mạng lưới các bác sĩ và cơ sở y tế. 
Nhờ vào đó, bệnh nhân có thể lựa chọn đúng bác sĩ chuyên khoa phù hợp với vấn đề của mình để đặt lịch khám bệnh ở các cơ sở y tế.

Sử dụng: Spring Boot, Spring Security with JWT, Spring Data JPA, SQL server 

Chức năng:

1. Chức năng chung
- Đăng nhập sử dụng JWT, đăng ký, quên mật khẩu

2. Chức năng của user (bệnh nhân - người đặt lịch khám)
- Hiển thị thông tin của các chuyên khoa nổi bật
- Hiển thị thông tin của các cơ sở y tế nổi bật
- Hiển thị thông tin cá nhân
- Tìm kiếm chung: tìm kiếm bác sĩ theo chuyên khoa,...
- Đặt lịch khám

3. Chức năng của bác sĩ
- Hiển thị danh sách bệnh nhân
- Nhận/hủy lịch khám của bệnh nhân
- Gửi thông tin về email cá nhân của bệnh nhân

4. Chức năng của Admin
- Khóa/hủy khóa tài khoản của bệnh nhân
- Thêm tài khoản của bác sĩ
- Khóa/hủy khóa tài khoản của bác sĩ
- Xem thông tin chi tiết lịch khám của từng bệnh nhân
- Xem thông tin chi tiết lịch khám của từng bác sĩ
