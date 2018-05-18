var $, form;
layui.config({
    base: '/js/layui/lay.modules/'
}).use(['table', 'form', 'table'], function () {
    $ = layui.jquery;
    form = layui.form;
    table = layui.table;


    initNameSelect();

    $("#customerAdd").click(function () {
        var option = {
            url: "/customer/edit/null",
            area: ["800px", "600px"],
            offset: ["10px", "50px"],
            title: "新增客户",
            data: {
                islook: false,
                fastadd: true
            }
        };
        openWindow(option);
    });


    form.on("select(customerName)", function (data) {
        var id = data.value;
        var customers = layui.sessionData("customer");
        for (var i in customers) {
            if (i === id) {
                $("#tel").val(customers[i].tel);
                $("#address").val(customers[i].address);
                return;
            }
        }
    });

    table.init('productList', {
        height: "full-350"
    });
});


function initNameSelect() {
    $.ajax({//异步请求返回给后台
        url: '/customer/listAll',
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            //这里获取到数据执行显示

            var options = "";
            if (data.rtnCode === "00") {
                var customers = data.data;
                for (var i in data.data) {
                    options += '<option value="' + customers[i].customerId + '">' + customers[i].name + '[' + customers[i].tel + ']</option>';
                    layui.sessionData('customer', {key: customers[i].customerId, value: customers[i]});
                }
            } else {
                layer.alert("获取客户列表失败");
            }

            $("#customerName").empty();
            $("#customerName").append(options);
            $("#customerName").val("");
            form.render('select');
        }
    });
}