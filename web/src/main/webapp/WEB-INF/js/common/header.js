/**
 * Created by Airlen on 2018/06/16.
 * 头部处理js
 */
(function (window) {
    window.header = {
        logout:function () {
            $.ajax({
                type:'POST',
                url:getWeb_Root()+'/user/logout.do',
                dataType:'json',
                data:{},
                success:function (result) {
                    if (result!=undefined&&result!=null&&result!=''&&result=='1'){
                        window.location.href = getWeb_Root();
                    }else {
                        alert(result);
                    }
                },
                error:function (result) {
                    alert('退出失败！'+result);
                }
            });
        }
    }
})(window);
