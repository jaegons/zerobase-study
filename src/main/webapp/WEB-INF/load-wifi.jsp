<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WifiApp</title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<div id = "info">Wifi 정보를 가져오는 중입니다 . . . .</div>

<a href="/">홈으로 돌아가기</a>
<script>
    window.addEventListener('load', function() {


        axios.post("/wifi")
            .then(function(response) {
                document.getElementById("info").innerText = "총 " + response.data + "개의 wifi정보 저장되어 있음."
            })
            .catch(function(error) {
                console.error(error);
            });
    })

</script>
</body>
</html>
