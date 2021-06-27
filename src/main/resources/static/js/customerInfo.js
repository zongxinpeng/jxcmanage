$(function () {

    toastr.options.positionClass = 'toast-top-center';

    initTable();

    //初始化页面上面的按钮事件
    $("#btn_query").click(function () {
        //console.log("查询点击");
        serachList();
    });

    //重置
    $("#btn_rest").click(function () {
        resetSerach();
        serachList();
    });


    $("#btn_add").click(function () {
        $("#myModalLabel").text("新增");
        $("#txt_type").val("add");
        resetUserInfo();
        $('#myModal').modal();
    });

    //保存提交
    $("#btn_submit").click(function () {
        saveUserInfo();
    });

    $("#btn_update").click(function () {
        $("#myModalLabel").text("更新");
        $("#txt_type").val("edit");

        //取表格的选中行数据
        var arrselections = $("#tb_departments").bootstrapTable('getSelections');
        if (arrselections.length <= 0) {
            toastr.warning('请选择一条数据');
            return;
        }
        var userInfo = arrselections[0];
        $("#txt_id").val(userInfo.id);
        $("#txt_userName").val(userInfo.name);
        $("#txt_mobile").val(userInfo.mobile);
        $("#txt_address").val(userInfo.address);
        $("#txt_userDesc").val(userInfo.note);

        $('#myModal').modal();
    });

    //注册删除按钮的事件
    $("#btn_delete").click(function () {
        //取表格的选中行数据
        var arrselections = $("#tb_departments").bootstrapTable('getSelections');
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');
            return;
        }

        Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
            if (!e) {
                return;
            }
            var id = arrselections[0].id;

            $.ajax({
                type: "post",
                url: "/customer/delete",
                data: JSON.stringify({id:id}),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function(result){
                    if(result.code == "200" && result.data >0 ){
                        toastr.success("操作成功");
                    } else {
                        toastr.warning(result.message);
                    }
                    serachList();
                },
                error: function(error){
                    toastr.error("系统异常！");
                }
            });


        });
    });

});

function initTable() {
    $('#tb_departments').bootstrapTable({
        url: '/customer/page',         //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
        dataType:'json',
        contentType:'application/json',
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        showColumns: false,                  //是否显示所有的列
        sortable: false,                     //是否启用排序
        clickToSelect: true,
        sortOrder: "asc",                   //排序方式
        toolbar: '#toolbar',                //工具按钮用哪个容器
        editable: true, //开启编辑模式
        singleSelect: true,
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        smartDisplay:false,
        undefinedText:'',
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        useCurrentPage: true,
        queryParamsType:'',
        queryParams: queryParams,//传递参数（*）
        responseHandler:function(res){
            return res.data;
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            title: '序号',
            align: 'left', //水平居中
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'name',
            title: '客户名称',
            align: 'left' //水平居中
        }, {
            field: 'mobile',
            title: '联系电话',
            valign: 'middle' //垂直居中
        }, {
            field: 'address',
            title: '地址'
        }, {
            field: 'note',
            title: '备注'
        }, {
            field: 'createdBy',
            title: '创建人'
        }, {
            field: 'createdDate',
            title: '创建时间'
        } ]
    });

    //$("#tb_departments").bootstrapTable('hideColumn','');

    //得到查询的参数
    function queryParams(params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNumber: params.pageNumber,   //页面大小
            pageSize: params.pageSize,  //页码
            name: $("#txt_search_user_name").val()/*,
            userCode: $("#txt_search_user_code").val(),
            createdDateStart: $("#txt_search_created_date_start").val(),
            createdDateEnd: $("#txt_search_created_date_end").val()*/
        };
        return temp;
    };
}

function serachList() {
    $("#tb_departments").bootstrapTable('refresh');
}


//重置查询条件
function resetSerach() {
    $("#txt_search_user_name").val("");
}

//修改框条件重置
function resetUserInfo() {
    $("#txt_id").val("");
    $("#txt_userName").val("");
    $("#txt_mobile").val("");
    $("#txt_address").val("");
    $("#txt_userDesc").val("");
}

function getUserInfoParam(type) {
    var id = $("#txt_id").val();
    var mobile = $("#txt_mobile").val();
    var name = $("#txt_userName").val();
    var address = $("#txt_address").val();
    var userDesc = $("#txt_userDesc").val();
    if(type=="add" && checkIsEmpty(id)){
        id = "";
    }
    if(checkIsEmpty(mobile)){
        toastr.warning('手机号不能为空');
        return;
    }
    if(checkIsEmpty(name)){
        toastr.warning('客户姓名不能为空');
        return;
    }
    if(checkIsEmpty(address)){
        toastr.warning('地址不能为空');
        return;
    }
    var param = {
        id : id,
        mobile : mobile,
        name: name,
        address:address,
        note:userDesc
    }
    return param;
}

//保存用户信息
function saveUserInfo() {
    var type = $("#txt_type").val();
    var param = getUserInfoParam(type);
    if(param==undefined){
        return;
    }
    $.ajax({
        type: "post",
        url: "/customer/addOrUpdateCustomerInfo",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(result){
            if(result.code == "200" && result.data >0 ){
                toastr.success("操作成功");
            } else {
                toastr.warning(result.message);
            }
            serachList();
        },
        error: function(error){
            toastr.error("系统异常！");
        }
    });


}

function checkIsEmpty(value) {
    if(value==null || value=="" || value==undefined || value.trim().length==0){
        return true;
    }
    return false;
}