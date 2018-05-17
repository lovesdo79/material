var $, form;
layui.config({
    base: '/js/layui/lay.modules/'
}).use(['table', 'form'], function () {
    $ = layui.jquery;
    form = layui.form;


    var islook = $('#islook').val().toLowerCase() === "true";
    var fastadd = $('#fastadd').val().toLowerCase() === "true";

    lookView(islook);

    $("#editBtn").click(function () {
        lookView(false);
    });

    form.on('submit(save)', function (data) {
        var index = parent.layer.getFrameIndex(window.name);
        $.ajax({//异步请求返回给后台
            url: '/customer/save',
            type: 'POST',
            data: data.field,
            dataType: 'json',
            success: function (data) {
                //这里获取到数据执行显示
                if (data.rtnCode === "00") {
                    lookView(true);
                    if (fastadd) {
                        parent.initNameSelect();
                        parent.layer.close(index);
                    } else {
                        layer.confirm("客户信息保存成功,是否回到列表", function () {
                            parent.reloadList();
                            parent.layer.close(index);
                        });
                    }
                } else {
                    layer.alert(data.rtnMsg);
                }
            }
        });
        return false;
    });

});


function lookView(islook) {
    if (islook) {
        $('.layui-input').attr("disabled", "disabled");
        $('.layui-input').addClass("disable-input");
        $('.layui-textarea').attr("disabled", "disabled");
        $('.layui-textarea').addClass("disable-input");
        $('#submitBtn').attr("disabled", "disabled");
        $('#submitBtn').addClass("display layui-btn-disabled");
        $('#editBtn').removeAttr('disabled');
        $('#editBtn').removeClass("layui-btn-disabled");
    } else {
        $('.layui-input').removeAttr("disabled");
        $('.layui-input').removeClass("disable-input");
        $('.layui-textarea').removeAttr("disabled");
        $('.layui-textarea').removeClass("disable-input");
        $('#submitBtn').removeAttr('disabled');
        $('#submitBtn').removeClass("display layui-btn-disabled");
        $('#editBtn').attr('disabled', 'disabled');
        $('#editBtn').addClass("layui-btn-disabled");
    }
}