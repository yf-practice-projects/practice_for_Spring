$(document).ready(function () {
    $.ajax({
        url: '/api/bulletin/list',   /* リクエストを送信するURLを指定 */
        type: 'GET',        /* HTTPメソッドを指定 */
        // data: '',        /* 送信データを指定 */
        dataType: 'json'    /* レスポンスデータの形式を指定 */
    })
    .done(function(data) {
        // (1) リクエストが成功した場合に行う処理
        const dataListElement = $('#dataList');
        data.forEach(item => {
            dataListElement.append('<td>' + item.title + '</td>');
            dataListElement.append('<td>' + item.content + '</td>');
            dataListElement.append('<td>' + item.createUser + '</td>');
            dataListElement.append('<td>' + item.createDate + '</td>');
            dataListElement.append(
                `<td> 
                    <form action="/show" method="get">
                        <input type="submit" value="詳細">
                        <input type="hidden" name="id" value="${item.id}">
                    </form>
                </td>`
            );
            dataListElement.append(
                `<td>
                    <form action="/edit" method="get">
                        <input type="submit" value="編集">
                        <input type="hidden" name="id" value="${item.id}">
                    </form>
                </td>`

            );
            dataListElement.append(
                `<td>
                    <form action="/delete" method="post" class="deleteButton">
                        <input type="submit" value="削除">
                        <input type="hidden" name="id" value="${item.id}">
                    </form>
                </td>`

            );
        });
    })
    .fail(function(error) {
        // (2) リクエストが成功しなかった場合に行う処理
        console.error('Error:', error);
    })
    .always(function() {
        // (3) リクエストの成功・失敗に関わらず行う処理
    });


    $('.deleteButton').on('click', function () {
        const id = $(this).attr('value');
        $.ajax({
            url: '/api/delete/' + id, // 削除を行うエンドポイントのURL。実際のURLは適宜変更してください。
            type: 'DELETE'
        })
        .done(function (data) {
            
        });

    })
})