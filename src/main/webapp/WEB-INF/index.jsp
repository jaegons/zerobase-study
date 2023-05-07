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
        <p>LAT : <input type="text" id="lat"> LNT : <input type="text" id="lnt">
            <button onclick="getUserLocation()">내 위치 가져오기</button>
            <button onclick="getWifi()">근처 WIFI 정보 보기</button>
        </p>
    </div>
</div>

<div class="table">
    <table id="table">
        <thead>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody id ="contents">
        <tr id = "alert">
            <td colspan="17">위치정보를 입력한 후에 조회해 주세요.</td>
        </tr>

        </tbody>
    </table>
</div>


<script>
    function success({coords, timestamp}) {
        console.log(coords)
        document.getElementById("lat").value = coords.latitude;
        document.getElementById("lnt").value = coords.longitude;
        return coords
    }

    function getUserLocation() {
        if (!navigator.geolocation) {
            throw "위치 정보가 지원되지 않습니다.";
        }
        navigator.geolocation.getCurrentPosition(success);
    }
    function deleteAlertRow() {
        var table = document.getElementById("table");
        var row = document.getElementById("alert");
        if (row) {
            table.deleteRow(row.rowIndex);
        }
    }


    function getWifi() {
        let lat = document.getElementById('lat').value;
        let lnt = document.getElementById('lnt').value;

        url = "/wifi?lat=" + lat + "&lnt=" + lnt

        axios.get(url)
            .then(function (response) {
                const fieldMappings = {
                    "dist": "거리(Km)",
                    "mgrNum": "관리번호",
                    "wrdofc": "자치구",
                    "wifiName": "와이파이명",
                    "roadNameAddress": "도로명주소",
                    "lotNumberAddress": "상세주소",
                    "installedFloor": "설치위치(층)",
                    "installedType": "설치유형",
                    "installedMby": "설치기관",
                    "serviceType": "서비스구분",
                    "wifiNetworkType": "망종류",
                    "installedYear": "설치년도",
                    "inoutDoor": "실내외구분",
                    "networkEnvironment": "WIFI접속환경",
                    "longitude": "X좌표",
                    "latitude": "Y좌표",
                    "workDateTime": "작업일자"
                };
                deleteAlertRow();

                var wifiList = response.data
                console.log(wifiList)
                var table = document.getElementById("table");
                while (table.rows.length > 1) {
                    table.deleteRow(-1);
                }
                wifiList.forEach(function (wifi) {

                    var newRow = table.insertRow();

                    var id = wifi["id"];

                    Object.keys(fieldMappings).forEach(function (key, index) {
                        var fieldName = fieldMappings[key];
                        var fieldValue = wifi[key];

                        if (key=="dist"){
                            fieldValue = fieldValue.toFixed(4)
                        }

                        var newCell = newRow.insertCell(index);
                        newCell.innerHTML = fieldValue;
                        newCell.addEventListener("click", function () {

                            window.location.href = "/wifi/details/" + id;
                        });

                    });
                });
            })
            .catch(function (error) {
                console.log(error);
            });
    }

</script>


</body>
</html>