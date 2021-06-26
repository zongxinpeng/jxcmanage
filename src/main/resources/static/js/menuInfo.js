$(function () {
    toastr.options.positionClass = 'toast-top-center';

    menuInfoList();
});


//获取菜单信息
function menuInfoList() {
    var param = {

    };

    $.ajax({
        type: "post",
        url: "/menu/menuList",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(result){
            if(result.code == "200" && result.data.length >0 ){
                zNodes = result.data;
                initMenuZTree();
                //toastr.success("操作成功");
            } else {
                //toastr.warning(result.message);
            }
        },
        error: function(error){
            toastr.error("系统异常！");
        }
    });


}



var zTree;
var currentPageIframe;

var setting = {
    view: {
        dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
        showLine: false,//是否显示节点之间的连线
        selectedMulti: false,//设置是否允许同时选中多个节点
        showIcon:false,//设置是否显示图表
        showTitle:false
    },
    data: {
        key: {//返回的数据和默认字段的对应关系
            name: "menuName"
        },
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pid",
            rootPId: ""
        }
    },
    callback: {
        beforeClick: function (treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("tree");
            if (treeNode.isParent) {
                zTree.expandNode(treeNode);
                return false;
            } else {
                currentPageIframe.attr("src", treeNode.menuUrl);
                return true;
            }
        }
    }
};

var zNodes = [
    /*{id: 1, pId: 0, name: "[core] 基本功能 演示", open: true},
    {id: 101, pId: 1, name: "最简单的树-标准JSON", file: "userInfo"},
    {id: 102, pId: 1, name: "最简单的树-简单JSON", file: "menuInfo"},
    {id: 103, pId: 1, name: "不显示 连接线", file: "core/noline"},
    {id: 104, pId: 1, name: "不显示 节点 图标", file: "core/noicon"},
    {id: 105, pId: 1, name: "自定图标-icon属性", file: "core/custom_icon"},
    {id: 106, pId: 1, name: "自定图标-iconSkin属性", file: "core/custom_iconSkin"},


    {id: 2, pId: 0, name: "[excheck]复/单能演示", open: false},
    {id: 201, pId: 2, name: "Checkbox勾选操作", file: "excheck/checkbox"},
    {id: 206, pId: 2, name: "Checkboxnoch演示", file: "excheck/checkbox_nocheck"},
    {id: 207, pId: 2, name: "chkDisabled演示", file: "excheck/checkbox_chkDisabled"},
    {id: 208, pId: 2, name: "Checkboxhal演示", file: "excheck/checkbox_halfCheck"},
    {id: 202, pId: 2, name: "Checkbox勾选统计", file: "excheck/checkbox_count"},


    {id: 3, pId: 0, name: "[exedit] 编辑功能 演示", open: false},
    {id: 301, pId: 3, name: "拖拽 节点 基本控制", file: "exedit/drag"},
    {id: 302, pId: 3, name: "拖拽 节点 高级控制", file: "exedit/drag_super"},
    {id: 303, pId: 3, name: "用 zTree 方法 移动 / 复制 节点", file: "exedit/drag_fun"},
    {id: 304, pId: 3, name: "基本 增 / 删 / 改 节点", file: "exedit/edit"},


    {id: 4, pId: 0, name: "大数据量 演示", open: false},
    {id: 401, pId: 4, name: "一次性加载大数据量", file: "bigdata/common"},
    {id: 402, pId: 4, name: "分批异步加载大数据量", file: "bigdata/diy_async"},
    {id: 403, pId: 4, name: "分批异步加载大数据量", file: "bigdata/page"},

    {id: 5, pId: 0, name: "组合功能 演示", open: false},
    {id: 501, pId: 5, name: "冻结根节点", file: "super/oneroot"},
    {id: 502, pId: 5, name: "单击展开/折叠节点", file: "super/oneclick"},
    {id: 503, pId: 5, name: "保持展开单一路径", file: "super/singlepath"},
    {id: 504, pId: 4, name: "一次性加载大数据量", file: "bigdata/common"},
    {id: 505, pId: 4, name: "分批异步加载大数据量", file: "bigdata/diy_async"},
    {id: 506, pId: 4, name: "分批异步加载大数据量", file: "bigdata/page"},
    {id: 507, pId: 4, name: "一次性加载大数据量", file: "bigdata/common"},
    {id: 508, pId: 4, name: "分批异步加载大数据量", file: "bigdata/diy_async"},
    {id: 509, pId: 4, name: "分批异步加载大数据量", file: "bigdata/page"},


    {id: 6, pId: 0, name: "其他扩展功能 演示", open: false},
    {id: 601, pId: 6, name: "隐藏普通节点", file: "exhide/common"},
    {id: 602, pId: 6, name: "配合 checkbox 的隐藏", file: "exhide/checkbox"},
    {id: 603, pId: 6, name: "配合 radio 的隐藏", file: "exhide/radio"}*/
];

/*$(document).ready(function () {
    var t = $("#tree");
    t = $.fn.zTree.init(t, setting, zNodes);
    currentPageIframe = $("#currentPageIframe");
    //currentPageIframe.bind("load", loadReady);
    var zTree = $.fn.zTree.getZTreeObj("tree");
    zTree.selectNode(zTree.getNodeByParam("id", 101));

});*/


function initMenuZTree() {
    var t = $("#tree");
    t = $.fn.zTree.init(t, setting, zNodes);
    t.expandAll(false);//是否展开或折叠所有节点
    currentPageIframe = $("#currentPageIframe");
    var zTree = $.fn.zTree.getZTreeObj("tree");
    //zTree.selectNode(zTree.getNodeByParam("id", 101));
}

function loadReady() {
    var bodyH = currentPageIframe.contents().find("body").get(0).scrollHeight,//937
        htmlH = currentPageIframe.contents().find("html").get(0).scrollHeight,//1110
        maxH = Math.max(bodyH, htmlH), //1110
        minH = Math.min(bodyH, htmlH),//937
        h = currentPageIframe.height() >= maxH ? maxH : minH;//currentPageIframe.height() 1110
    if (h < 530) {
        h = 530;
    }
    currentPageIframe.height(h);
}