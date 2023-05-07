<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        #table {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #table td, #table th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #table tr:hover {
            background-color: #ddd;
        }

        #table th {
            width: 30%;
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }

        #alert {
            text-align: center;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <title>WifiApp</title>
</head>
<body>
<h1><%= "와이파이 정보 구하기" %>
</h1>
<br/>
<br class="nav">

<div class="menu">
    <a href="/">홈</a>
    <span class="separator">|</span>
    <a href="/">위치</a>
    <span class="separator">|</span>
    <a href="/history">히스토리 목록</a>
    <span class="separator">|</span>
    <a href="/load-wifi">Open API 정보 가져오기</a>
    <span class="separator">|</span>
    <a href="/bookmark-wifi">즐겨찾기 보기</a>
    <span class="separator">|</span>
    <a href="/bookmark/manage">즐겨찾기 그룹 관리</a>
</div>

</br>
</div>

<div class="table">
    <table id="table">
        <thead>

        <tr>
            <th>북마크 이름</th>
            <td><input class="text" id="name" value=${bookMark.name}></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><input class="text" id="orderNum" value=${bookMark.orderNum}></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <button onclick="updateBookMark(${bookMark.id})">추가</button>
            </td>
        </tr>

        </thead>
    </table>
</div>

<script>

    function updateBookMark(id) {
        var o = document.getElementById('orderNum').value;
        var n = document.getElementById('name').value;

        axios.put('/bookmark/' + id, {
            orderNum: o,
            name: n
        }, {
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            }
        })
            .then(function (response) {
                alert("성공")
                window.location.href = '/bookmark/manage';
            })
            .catch(function (error) {
                console.log(error);
            });

    }
</script>


</body>
</html>