<%@ page import="com.example.zerobasestudy.web.jdbc.dto.BookMarkWifiDto" %>
<%@ page import="java.util.List" %>
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
    </br>
</div>

</br>
<div class="table">
    <table id="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>와이파이명</th>
            <th>등록일자</th>
            <th>비고</th>

        </tr>
        </thead>
        <tbody id="contents">
        <%
            List<BookMarkWifiDto> list = (List<BookMarkWifiDto>) request.getAttribute("list");
        %>

        <%
            for (BookMarkWifiDto bookMarkWifiDto : list) {
        %>
        <tr>
            <td><%= bookMarkWifiDto.getId() %>
            </td>
            <td><%= bookMarkWifiDto.getBookMarkName() %>
            </td>
            <td><%= bookMarkWifiDto.getWifiName() %>
            </td>
            <td><%= bookMarkWifiDto.getCreatedDateTime() %>
            </td>
            <td>
                <button onclick='del(<%= bookMarkWifiDto.getId() %>)'>삭제</button>
            </td>
        </tr>
        <%
            }
        %>
        <%
            if (list.isEmpty()){
        %>
        <tr id="alert">
            <td colspan="17">등록된 내용이 없습니다.</td>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>
</div>

<script>
    function del(id) {
        console.log(id)

        axios.delete('/bookmark-wifi/' + id)
            .then(function (response) {
                alert("삭제되었습니다")
                location.href = '/bookmark-wifi'
                console.log(response)
            })
            .catch(function (error) {
                console.log(error)
            })
    }
</script>
</body>
</html>