/**
 * Created by Airlen on 2018/06/12.
 * 首页处理js
 */
(function (window,$) {
    window.loginPage = {
        login:function () {
            $.ajax({
                type:'POST',
                url:getWeb_Root()+'/user/login.do',
                dataType:'json',
                data:{
                    user:'ShowCode',
                    password:'123456'
                },
                success:function (result) {
                    if (result!=undefined&&result!=null&&result!=''&&result=='1'){
                        window.location.href = getWeb_Root()+'/index/indexFrame.do';
                    }else {
                        alert(result);
                    }
                },
                error:function (result) {
                    alert('登录失败！'+result);
                }
            });
        }
    }
})(window,$);
