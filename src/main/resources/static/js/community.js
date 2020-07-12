// 回复功能
function post() {
    var questionId = $("#question-id").val();
    var content = $("#comment-content").val();
    // console.log(questionId)
    // console.log(content)
    comment2target(questionId, 1, content);
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~");
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/comment",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            // console.log(response)
            if (response.code == 200) {
                window.location.reload();
                $("#comment-section").hide();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=b99638cde60f721507e9&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", true)
                    }
                } else {
                    alert(response.message)
                }
            }
        }
    })
}

// 展开二级评论
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    // 获取二级评论展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            // 展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var commentElement = $("<div/>", {
                        html: "<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12 comments\">\n" +
                            "                                    <div class=\"media\">\n" +
                            "                                        <div class=\"media-left\">\n" +
                            "                                            <img src=\"" + comment.user.avatarUrl + "\"" +
                            "                                               class=\"media-object img-rounded\">\n" +
                            "                                        </div>\n" +
                            "                                        <div class=\"media-body\">\n" +
                            "                                            <h5 class=\"media-heading\">\n" +
                            "                                                <span>" + comment.user.name + "</span>\n" +
                            "                                            </h5>\n" +
                            "                                            <div>" + comment.content + "</div>\n" +
                            "                                            <div class=\"menu\">\n" +
                            "                                                <span class=\"pull-right\"\n" +
                            "                                                   >" + moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm:ss') + "</span>\n" +
                            "                                            </div>\n" +
                            "                                        </div>\n" +
                            "                                    </div>\n" +
                            "                                </div>"
                    });
                    subCommentContainer.prepend(commentElement);
                });

                // 展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            })
        }
    }
}