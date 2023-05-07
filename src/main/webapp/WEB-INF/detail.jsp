<%@ page import="com.example.zerobasestudy.web.bookmark.BookMark" %>
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
    </div>

    <div class="search">
        <p>
            <label for="bookMarks">즐겨찾기 목록</label>

            <select id="bookMarks" name="bookMarks">

                <%
                    List<BookMark> bookMarkList = (List<BookMark>) request.getAttribute("bookMarkList");
                    for (BookMark bookmark : bookMarkList) { %>
                <option value="<%= bookmark.getId() %>"><%= bookmark.getName() %>
                </option>
                <% } %>

            </select>

            <button onclick="addBookMark()">즐겨찾기 추가하기</button>
        </p>
    </div>
</div>

<div class="table">
    <table id="table">
        <thead>

        <tr>
            <th>거리(Km)</th>
            <td> ${wifi.dist}</td>
        </tr>
        <tr>
            <th>관리번호</th>
            <td> ${wifi.mgrNum}</td>
        </tr>
        <tr>
            <th>자치구</th>
            <td>${wifi.wrdofc}</td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td>${wifi.wifiName}</td>
        </tr>
        <tr>
            <th>도로명주소</th>
            <td>${wifi.roadNameAddress}</td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td>${wifi.lotNumberAddress}</td>
        </tr>
        <tr>
            <th>설치위치(층)</th>
            <td>${wifi.installedFloor}</td>
        </tr>
        <tr>
            <th>설치유형</th>
            <td>${wifi.installedType}</td>
        </tr>
        <tr>
            <th>설치기관</th>
            <td>${wifi.installedMby}</td>
        </tr>
        <tr>
            <th>서비스구분<</th>
            <td>${wifi.serviceType}</td>
        </tr>
        <tr>
            <th>망종류</th>
            <td>${wifi.wifiNetworkType}</td>
        </tr>
        <tr>
            <th>설치년도</th>
            <td>${wifi.installedYear}</td>
        </tr>
        <tr>
            <th>실내외구분</th>
            <td>${wifi.inoutDoor}</td>
        </tr>
        <tr>
            <th>WIFI접속환경</th>
            <td>${wifi.networkEnvironment}</td>
        </tr>
        <tr>
            <th>X좌표</th>
            <td>${wifi.longitude}</td>
        </tr>
        <tr>
            <th>Y좌표</th>
            <td>${wifi.latitude}</td>
        </tr>
        <tr>
            <th>작업일자</th>
            <td>${wifi.workDateTime}</td>
        </tr>
        </thead>
    </table>
</div>


<script>

    function addBookMark() {
        var selectElement = document.getElementById('bookMarks');
        var selectedOption = selectElement.options[selectElement.selectedIndex];
        var selectedBookMarkId = selectedOption.value;

        var currentPath = window.location.pathname;
        var currentPageId = currentPath.substring(currentPath.lastIndexOf('/') + 1)


        axios.post('/bookmark-wifi', {
            bookMarkId: selectedBookMarkId,
            wifiId: currentPageId
        }, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        })
            .then(function (response) {
                alert("추가되었습니다");
            })
            .catch(function (error) {
                console.log(error);
            });

    }
</script>


</body>
</html>