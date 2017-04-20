/**
 * Created by bgfang on 2017/4/16.
 */
var isLook = false;
$(function () {

    isLook = $("#isLook").val() == "true" ? true : false;


    lookUI(isLook);

    $("#btnSumbit").click(function () {
        // $.messager.confirm('确认', '确认提交', function (r) {
        //     if (r) {
        $("#customerForm").form('enableValidation');

        if ($("#customerForm").form('validate')) {
            submitForm();
        }
        //     }
        // });
    });


    $("#customerForm").form('disableValidation');
});

function submitForm() {
    $.ajax({
        type: "POST",
        url: "./save.do",
        data: $("#customerForm").serialize(),
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

function lookUI(isLook) {
    if (isLook) {
        $("#yxsuccessForm input").attr("disabled", true);
        $('.easyui-textbox').textbox("disable");
        $('.easyui-combobox').combobox("disable");
        $('.easyui-datebox').datebox("disable");

        $("#btnSumbit").css("display", "none");
        $('.easyui-textbox').textbox("textbox").addClass("gainsboroBack");
    }
}