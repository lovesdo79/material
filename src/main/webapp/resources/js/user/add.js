/**
 * Created by bgfang on 2017/4/26.
 */
$(function () {
    $("#btnSumbit").click(function () {
        $("#userForm").form('enableValidation');

        if ($("#userForm").form('validate')) {
            submitForm();
        }
    });


    $("#userForm").form('disableValidation');
});

function submitForm() {
    $.ajax({
        type: "POST",
        url: "./save",
        data: $("#userForm").serialize(),
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
            var code = data.rtnCode;

            if ("00" === code) {
                self.close();
                // window.opener.$("#gridList").datagrid('reload');
                parent.$("#gridList").datagrid("reload");
                parent.$("#msgwindow").window("close");
            } else {
                $.messager.alert("错误", data.rtnMsg, "error");
            }

        }
    });
}
