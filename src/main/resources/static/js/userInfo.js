$(function () {

    //将这个属性值设置为不同的值就能让提示信息显示在不同的位置
    // 如toast-bottom-right表示下右、
    // toast-bottom-center表示下中、
    // toast-top-center表示上中等，更过位置信息请查看文档。
    toastr.options.positionClass = 'toast-top-center';
    //toastr.success('提交数据成功');
    //toastr.error('Error');
    //toastr.warning('只能选择一行进行编辑');
    //toastr.info('info');

    $('#txt_search_created_date_start').datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        showMeridian: true,
        pickerPosition: "bottom-left",
        language: 'zh-CN',//中文，需要引用zh-CN.js包
        startView: 2,//月视图
        minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
    });


    $('#txt_search_created_date_end').datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        showMeridian: true,
        pickerPosition: "bottom-left",
        language: 'zh-CN',//中文，需要引用zh-CN.js包
        startView: 2,//月视图
        minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
    });

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
        $("#txt_userCode").val(userInfo.userCode);
        $("#txt_userName").val(userInfo.userName);
        $("#txt_userPassword").val(userInfo.userPassword);
        $("#txt_userDesc").val(userInfo.userDesc);

        $('#myModal').modal();
    });

    /*$("#btn_delete").click(function () {
        $("#myModalLabel").text("删除");
        $('#myModal').modal();
    });*/
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
                url: "/userInfo/deleteUserInfoById",
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

            /*$.ajax({
                type: "post",
                url: "/api/DepartmentApi/Delete",
                data: { "": JSON.stringify(arrselections) },
                success: function (data, status) {
                    if (status == "success") {
                        toastr.success('提交数据成功');
                        $("#tb_departments").bootstrapTable('refresh');
                    }
                },
                error: function () {
                    toastr.error('Error');
                },
                complete: function () {

                }

            });*/
        });
    });

});

function initTable() {
    $('#tb_departments').bootstrapTable({
        url: '/userInfo/queryUserList',         //请求后台的URL（*）
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
            field: 'userCode',
            title: '用户名',
            align: 'left' //水平居中
        }, {
            field: 'userName',
            title: '姓名',
            valign: 'middle' //垂直居中
        }, {
            field: 'userDesc',
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
            userCode: $("#txt_search_user_code").val(),
            userName: $("#txt_search_user_name").val(),
            createdDateStart: $("#txt_search_created_date_start").val(),
            createdDateEnd: $("#txt_search_created_date_end").val()
        };
        return temp;
    };
}

function serachList() {
    if(!cheakCreateDate()){
        return;
    }
    //$("#tb_departments").bootstrapTable('destroy');
    $("#tb_departments").bootstrapTable('refresh');
}

//判断时间
function cheakCreateDate() {
    var createdDateStart = $("#txt_search_created_date_start").val();
    var createdDateEnd = $("#txt_search_created_date_end").val();
    if(createdDateStart!=null && createdDateEnd!='' && createdDateEnd!=null && createdDateEnd!=''){
        var startDate = new Date(createdDateStart);
        var endDate = new Date(createdDateEnd);;
        if (startDate > endDate) {
            toastr.warning('开始时间不能大于结束时间');
            return false;
        }
    } else if( (createdDateStart==null || createdDateStart=='') && (createdDateEnd==null || createdDateEnd=='')){
        return true
    } else {
        toastr.warning('请选择正确的时间段');
        return false;
    }
    return true;
}

//重置查询条件
function resetSerach() {
    $("#txt_search_user_code").val("");
    $("#txt_search_user_name").val("");
    $("#txt_search_created_date_start").val("");
    $("#txt_search_created_date_end").val("");
}

//修改框条件重置
function resetUserInfo() {
    $("#txt_id").val("");
    $("#txt_type").val("");
    $("#txt_userCode").val("");
    $("#txt_userName").val("");
    $("#txt_userPassword").val("");
    $("#txt_userDesc").val("");
}

function getUserInfoParam(type) {
    var id = $("#txt_id").val();
    var userCode = $("#txt_userCode").val();
    var userName = $("#txt_userName").val();
    var userPassword = $("#txt_userPassword").val();
    var userDesc = $("#txt_userDesc").val();
    if(type=="add" && checkIsEmpty(id)){
        id = "";
    }
    if(checkIsEmpty(userCode)){
        toastr.warning('用户名不能为空');
        return;
    }
    if(checkIsEmpty(userName)){
        toastr.warning('姓名不能为空');
        return;
    }
    if(checkIsEmpty(userPassword)){
        toastr.warning('密码不能为空');
        return;
    }
    var param = {
        id : id,
        userCode : userCode,
        userName: userName,
        userPassword:userPassword,
        userDesc:userDesc
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
        url: "/userInfo/addOrUpdateUserInfo",
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