/**
 * Created by Airlen on 2018/06/12.
 * 首页处理js
 */
(function (window,$) {
    window.index = {
        login:function () {
            /*
             $.post()
             {
             type: method,
             url: url,
             data: data,
             success: callback,
             dataType: type
             }
            * */
            $.ajax({
                type:'POST',
                url:getWeb_Root()+'/user/login.do',
                dataType:'json',
                data:{
                    user:'123456',
                    password:'123456'
                },
                success:function (result) {
                    alert('登录成功！');
                },
                error:function (result) {
                    alert('登录失败！');
                }
            });
        }
    }
})(window,$);
