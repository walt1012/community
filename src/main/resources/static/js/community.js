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
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=b99638cde60f721507e9&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closable",true)
                    }
                } else {
                    alert(response.message)
                }
            }
        }
    })
}