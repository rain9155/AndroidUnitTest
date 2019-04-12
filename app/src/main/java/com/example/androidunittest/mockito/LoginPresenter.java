package com.example.androidunittest.mockito;

/**
 * Presenter
 * Created by 陈健宇 at 2019/3/31
 */
public class LoginPresenter {

    private LoginModel mModel;

    public LoginPresenter() {
        mModel = new LoginModel();
    }

    /**
     * 为了测试添加这个set方法
     */
    public void setModel(LoginModel model) {
        mModel = model;
    }

    public void login(String name, String id){
        if(name == null || id == null) return;

        //假设这是一个执行耗时的方法，要等待很久，我们mock掉它的行为
        if(mModel.verifyName(name)) return;

        mModel.login(name, id);
    }

}
