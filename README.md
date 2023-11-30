# Midterm Java Technology

-Đây là một web site xây dựng cho một website bán đồ công nghê sử dụng java spring boot xây dựng RESTFul API phụ vụ phàn backend và ReactJs phục vụ làm Front-end của website.
-Tất cả các hình ảnh, video demo, mô hình quan hệ của website được chứa trong thư mục image

# Mô tả cấu trúc dự án

-   Tên dự án : SALE DEVICE
-   Công nghệ sử dung: - backend :
    Java Spring Boot
    Spring Security:
    Spring Web:
    Spring Data JPA:
    MySQL Connector/J:
    Lombok:
    JSON Web Token (JWT):
-   frontend : ReactJs
-   Cơ sở dữ liệu : MySql

## Cấu trúc dự án Java spring boot:

![folderStructureBackEnd](./image/folderStructureBackEnd.png)

1. config : Chứa file config của java security, Jwt filter, webconfig(Để test với Front-end bỏ qua CROS)
2. controller : Được chia thành ba loại

-   package authentication : Chỉ xử lý những yêu cầu HTTP từ phía người dùng về xác thực, đăng ký, đăng nhập, resfresh token
-   package include : Chỉ xử lý những yêu cầu HTTP từ phía người dùng có thể là ADMIN có thể là USER
-   package admin : Chỉ xử lý những yêu cầu HTTP từ phía người dùng là ADMIN
-   package user : Chỉ xử lý những yêu cầu HTTP từ phía người dùng là USER
-   package publicCon : Xử lý những yêu cầu HTTP từ mọi người dùng.

3. model :

-   Chứa các đối tượng được quản lý trong website

4. DTO :

-   Chứa những class để nhận dữ liệu từ request của người dùng

5. Response :

-   Chứa những class trả về (Response) cho người dùng.

6. Exception :

-   Chưa một vài exception được dùng chung cho cả trang web

7. repository :

-   Chứa các interface được kế thừa từ JPARepository để dùng tương tác với database

8. Service :

-   Là tầng chưa toàn bộ phương thức xử lý những yêu cầu của người dùng với website

# Mô hình ERD

![Database](./image/ERD.jpg)

# Các bước để chạy ứng dụng

![folderStructureProject](./images/folderStructureProject.png)

1. Chạy backend
   B1. Mở folder backend trong folder dự án
   B2. Mở cửa sở terminal ngay tại folder backend sau đó chạy lệnh docker compose up để thực hiện chạy cơ sở dữ liệu
   B3. Tạo database mới trong cơ sở dư liệu nếu chưa có với tên là 'commerce_midterm'
   hoặc có thể chỉnh sửa file cấu hình cơ sở dữ liệu trong docker trong file "application.yml" trong thư mục của dự án
   B4. Sau khi chạy cơ sở dữ liệu thành công, run và chạy dự án với spring boot để chạy server.
   => server chạy mặc định với port: 8099, đường dẫn http://localhost:8099
2. Chạy front-end
   B1. Cài đặt NPM, Node js - (nếu chưa cài đặt)
   B2. Chạy câu lệnh 'npm install' để thực hiện tại xuống toàn bộ thư viện.
   B3. Chạy lệnh "npm run dev" để chạy ứng dụng front-end
   => ứng dụng chạy mặc định với cổng http://127.0.0.1:3030 sau đó mở trình duyệt với dường dẫn này và xem kết quả.

## Giải thích API trong trang web

1. AUTHENTICATION :

-   Đăng ký tài khoản
    ![sign up account](/images/singUpAccountTestApi.png)
-   Đăng nhập vào hệ thống
    ![sing in ](/images/signInTestApi.png)
-   Refresh lại token khi token hết hạn
    ![refresh token](/refreshTokenTestApi.png)

2. PUBLIC:

-   Lấy tất cả sản phẩm trong hệ thống
    ![Get all product](/images//getAllProductTestApi.png)
-   Xem chi tiết một sản phẩm
    ![View detial product](/images/viewDetialProductTestApi.png)
-   Chọn và lấy ra sản phẩm theo category, price , brand, color
    ![Filter product by brand or category or color or price (above > maxPrice, below < minPrice, minPrice <= between >= maxPrice )](/images/filterProductTestApi.png)
-   Tìm kiếm product theo tên, branc, category
    ![search product by name, branch, category](/images/searchProdcutTestApi.png)

1. ADMIN :
   ##NOTE:
   ##cần đăng nhập với vai trò ADMIN
   ##account admin :
   #username : admin
   #password : admin
   #lấy token để có thể test API

-   Lấy tất cả người dùng trên hệ thống
    ![get all accounts](/images/getAllAccountTestApi.png)
-   Lấy thông tin account của người dùng
    ![View info account](/images/viewInfoAccountTestApi.png)
-   Thêm một người dùng mới
    ![add new account](/images/addNewAccountTestApi.png)
-   Cập nhận một người dùng mới
-   Xóa người dùng mới trên hệ thống
    ![remove new account text](/images/removeNewAccount.png)
-   Thêm một sản phẩm trên hệ thống
    ![add new product](/images/addNewProductTestApi.png)
-   Xóa sản phẩm mới trên hệ thống
    ![remove new product](/images/removeNewProductTestApi.png)
-   Chỉnh sửa sản phẩm trên hệ thống
    ![edit product](/images/editProductTestApi.png)

2. USER:
   ##NOTE:
   ##cần đăng nhập với vai trò USER
   ##account user mặc định:
   #username : user
   #password : user
   #lấy token để có thể test API

-   Thêm sản phẩm vào giỏ hàng
    ![add product into cart](/images/addProductIntoCartTestApi.png)
-   Xem chi tiết shopping cart
    ![view detail shopping cart](/images/viewDetailShoppingCartTestApi.png)
-   Xóa sản phẩm khỏi shopping cart
    ![remove product from cart](/images/removeProductFromCartTestApi.png)
-   Thực hiện đặt đơn hàng
    ![add order](/images/addOrderTestApi.png)
-   Xem chi tiết các đơn hàng của mình trên hệ thống
    ![view order detial by user](/images/viewOrderDetailTestApi.png)

# link file json để import test nhanh API

![test api by postman](/API_midterm_eco.postman_collection.json)

# Video demo ứng dụng trình

![video demo app](/demo.mkv)
# java-midterm-eco
