function post() {
    var questionId = $("#question-id").val();
    var content = $("#comment-content").val();
    // console.log(questionId)
    // console.log(content)
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/comment",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            // console.log(response)
            if (response.code == 200) {
                $("#comment-section").hide();
            } else {
                alert(response.message)
            }
        }
    })
}