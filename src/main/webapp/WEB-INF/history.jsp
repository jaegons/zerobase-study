<%@ page import="com.example.zerobasestudy.web.history.SearchHistory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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
<div class="nav">

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

</div>
</br>
<div class="table">
    <table id="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody id="contents">
        <%
            List<SearchHistory> historyList = (List<SearchHistory>) request.getAttribute("historyList");
        %>

        <%
            for (SearchHistory history : historyList) {
        %>
        <tr>
            <td><%= history.getId() %>
            </td>
            <td><%= history.getLongitude() %>
            </td>
            <td><%= history.getLatitude() %>
            </td>
            <td><%= history.getSearchedDateTime() %>
            </td>
            <td>
                <button onclick='deleteHistory(<%= history.getId() %>)'>삭제</button>
            </td>
        </tr>
        <%
            }
        %>


        </tbody>
    </table>
</div>

<script>
    function deleteHistory(id) {
        console.log(id)
        axios.delete('/history/' + id)
            .then(response => {

                window.location.href = '/history';
            })
            .catch(error => {
                alert('삭제 실패!');
            });
    }


</script>
</body>
</html>