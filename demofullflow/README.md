- Bản Demo này config mỗi 1 service khi được request sẽ có 1 reqeust gọi đến service authe để xác thực, sử dụng kiểu này khi chúng ta devlop service ở các network khác nhau, các IP network là public được gọi nhau public.

- Trong demo này, chúng ta sẽ có. <br>
    - Config là service gồm các config để tất cả server khác gọi để để lấy config
    - Registry là service Registry để cho các service discovery đăng ký đến, service này sẽ load config từ config service
    - Auth service sử dụng để xác thực, tại cơ chế của bản demo này, mỗi khi gọi 1 service, từng service sẽ lấy token và xác thực lại với service auth.
        - Sử dụng cơ chế này để đảm bảo nếu các service có thể call với nhau ở ngoài hệ thống mạng, ví vụ các service sẽ được setup ở các network mạng khác nhau thì sẽ đảo bảo được sự bảo mật
    - gateway service sử dụng để điều hướng reqeust đến các service tương ứng
    - gallery và image là 2 service resouce, 2 service này khi đc gọi thì cũng đều gọi lên auth để xác thức.
    

